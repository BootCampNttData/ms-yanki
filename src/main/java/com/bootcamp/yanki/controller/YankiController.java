package com.bootcamp.retailclient.controller;

import com.bootcamp.retailclient.model.Yanki;
import com.bootcamp.retailclient.service.YankiService;
import com.bootcamp.retailclient.util.Constants;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/retailclient")
@RequiredArgsConstructor
public class YankiController {
    private final Constants constants;
    public final YankiService service;
    Logger logger = LoggerFactory.getLogger(YankiController.class);

    @GetMapping
    public Flux<Yanki> getAll(){
        logger.info("***********  Test *************");
        return service.findAll();
    }

    @GetMapping("/find/{document}")
    public Flux<Yanki> findByDocumentId(@PathVariable("document") String document){
        return service.getByDocumentId(document);
    }

    @PostMapping
    public Mono<Yanki> create(@RequestBody Yanki yanki){
        return service.create(yanki);
    }

    @PostMapping("/update")
    public Mono<Yanki> update(@RequestBody Yanki yanki){
        return service.create(yanki);
    }

    @DeleteMapping
    public Mono<Yanki> delete(@RequestBody Yanki yanki){
        return service.delete(yanki);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<Yanki> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

    @GetMapping("/getProducts/{idClient}")
    public Flux<ProductsReport> getProducts(@PathVariable("idClient") String idClient){
        WebClient  webClient = WebClient.create(constants.getGatewayUrl());
        logger.info("Saving Accounts");
        List<Integer> savAccLst=new ArrayList<>();

        var savingAccounts = webClient.get()
                .uri("/savingaccount/findAcountByClientId/{id}",idClient)
                .retrieve().bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Saving Account"));

        var currentAccounts = webClient.get()
                .uri("/currentaccount/findAcountsByClientRuc/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Current Accounts"));

        var fixedDepositAccounts = webClient.get()
                .uri("/fixeddepositaccount/findAcountsByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Fixed Deposit Account"));

        var creditCards = webClient.get()
                .uri("/creditcard/findCreditCardByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Credit Cards"));

        var credits = webClient.get()
                .uri("/credit/findCreditByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Credits"));

        return Flux.merge(savingAccounts,currentAccounts,fixedDepositAccounts,creditCards,credits);
    }

}
