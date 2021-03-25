package br.gabrielsmartins.smartpayment.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.mapper.FraudWebMapper;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.mapper.FraudItemWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveFraudUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SearchFraudUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.adapters.web.support.FraudDTOSupport.defaultFraudDTO;
import static br.gabrielsmartins.smartpayment.adapters.web.support.FraudItemDTOSupport.defaultFraudItemDTO;
import static br.gabrielsmartins.smartpayment.adapters.web.support.PaymentMethodDTOSupport.defaultPaymentMethodDTO;
import static br.gabrielsmartins.smartpayment.application.support.FraudItemSupport.defaultFraudItem;
import static br.gabrielsmartins.smartpayment.application.support.FraudSupport.defaultFraud;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SaveFraudController.class)
@Import({FraudWebMapper.class, FraudItemWebMapper.class})
public class SaveFraudAnalysisControllerTest {

    @MockBean
    private SaveFraudUseCase useCase;

    @MockBean
    private SearchFraudUseCase searchFraudUseCase;

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud() throws JsonProcessingException {

        FraudDTO fraudDTO = defaultFraudDTO().build();
        fraudDTO.addItem(defaultFraudItemDTO().build());
        fraudDTO.addPaymentMethod(defaultPaymentMethodDTO().build());

        String body = mapper.writeValueAsString(fraudDTO);

        FraudAnalysis fraudAnalysisMock = defaultFraud().build();
        fraudAnalysisMock.addItem(defaultFraudItem().build());
        fraudAnalysisMock.addPaymentMethod(PaymentMethod.CASH, BigDecimal.valueOf(1500));

        when(useCase.save(any(FraudAnalysis.class))).thenReturn(Mono.just(fraudAnalysisMock));

        webClient.post()
                 .uri("/frauds-v1/frauds")
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(body))
                 .exchange()
                 .expectStatus().isCreated()
                 .expectBody()
                 .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).save(any(FraudAnalysis.class));
    }

    @Test
    @DisplayName("Given Fraud When Exists Then Return Updated Fraud")
    public void givenFraudWhenExistsThenReturnUpdatedFraud() throws JsonProcessingException {

        FraudDTO fraudDTO = defaultFraudDTO().build();
        fraudDTO.addItem(defaultFraudItemDTO().build());
        fraudDTO.addPaymentMethod(defaultPaymentMethodDTO().build());

        String body = mapper.writeValueAsString(fraudDTO);

        FraudAnalysis fraudAnalysis = defaultFraud().build();
        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();
        fraudAnalysis.addItem(fraudAnalysisItem);

        when(searchFraudUseCase.findById(anyString())).thenReturn(Mono.just(fraudAnalysis));
        when(useCase.save(any(FraudAnalysis.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        webClient.put()
                 .uri("/frauds-v1/frauds/{id}", fraudDTO.getId())
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(body))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id").isEqualTo(fraudAnalysis.getId().toString());

        verify(this.searchFraudUseCase, times(1)).findById(anyString());
        verify(this.useCase, times(1)).save(any(FraudAnalysis.class));
    }


}
