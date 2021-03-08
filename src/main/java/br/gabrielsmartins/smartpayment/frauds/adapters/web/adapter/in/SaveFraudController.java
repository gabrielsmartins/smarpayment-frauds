package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudWebMapper;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.exception.FraudNotFoundException;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SaveFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SearchFraudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/frauds-v1/frauds")
@RequiredArgsConstructor
@Validated
public class SaveFraudController {

    private final SaveFraudUseCase useCase;
    private final SearchFraudUseCase searchFraudUseCase;
    private final FraudWebMapper mapper;

    /**
     * Could be used for manually process
     * @param fraudDTO is received payload
     * @return saved fraud
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FraudDTO> save(@RequestBody @Valid FraudDTO fraudDTO){
        Fraud fraud = this.mapper.mapToDomain(fraudDTO);
        return this.useCase.save(fraud).map(mapper::mapToDTO);
    }

    /**
     * Could be used for manually process
     * @param id of existing fraud
     * @param fraudDTO is received payload
     * @return saved fraud
     */
    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<FraudDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid FraudDTO fraudDTO){
        return searchFraudUseCase.findById(id)
                                  .flatMap(existingFraud -> {
                                      Fraud fraud = this.mapper.mapToDomain(fraudDTO);
                                      fraud.setId(existingFraud.getId());
                                      return useCase.save(fraud)
                                                     .map(mapper::mapToDTO);
                                  })
                                 .switchIfEmpty(Mono.error(new FraudNotFoundException("Fraud not found for id " + id)));
    }


}
