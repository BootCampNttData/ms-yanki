package com.bootcamp.retailclient.repository;

import com.bootcamp.retailclient.model.Yanki;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface YankiRepository extends ReactiveCrudRepository<Yanki, String> {

    Flux<Yanki> findByDocumentId(String num);

}
