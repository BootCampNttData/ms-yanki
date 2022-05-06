package com.bootcamp.retailclient.service;

import com.bootcamp.retailclient.model.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiService {
    Flux<Yanki> findAll();
    Flux<Yanki> getByDocumentId(String num);
    Mono<Yanki> create(Yanki yanki);
    Mono<Yanki> update(Yanki yanki);
    Mono<Yanki> deleteById(String id);
    Mono delete(Yanki yanki);
}
