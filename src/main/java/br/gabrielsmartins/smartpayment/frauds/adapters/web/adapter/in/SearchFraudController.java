package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudWebMapper;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SearchFraudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/frauds-v1/frauds")
@RequiredArgsConstructor
public class SearchFraudController {

    private final SearchFraudUseCase useCase;
    private final FraudWebMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FraudDTO> findAll(){
        return this.useCase.findAll().map(mapper::mapToDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FraudDTO> findById(@PathVariable("id") String id){
        return this.useCase.findById(id).map(mapper::mapToDTO);
    }

    @GetMapping(params = {"order_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FraudDTO> findByOrderId(@RequestParam("order_id") Long orderId){
        return this.useCase.findByOrderId(orderId).map(mapper::mapToDTO);
    }

    @GetMapping(params = {"customer_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FraudDTO> findCustomerId(@RequestParam("customer_id") UUID customerId,
                                         @PageableDefault Pageable pageable){
        return this.useCase.findByCustomerId(customerId, pageable).map(mapper::mapToDTO);
    }

    @GetMapping(params = {"product_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FraudDTO> findByProductId(@RequestParam("product_id") UUID productId,
                                         @PageableDefault Pageable pageable){
        return this.useCase.findByProductId(productId, pageable).map(mapper::mapToDTO);
    }

    @GetMapping(params = {"start_date_time", "end_date_time"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FraudDTO> findByInterval(@RequestParam("start_date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDatetime,
                                         @RequestParam("end_date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDatetime,
                                         @PageableDefault Pageable pageable){
        return this.useCase.findByInterval(startDatetime, endDatetime, pageable).map(mapper::mapToDTO);
    }


}
