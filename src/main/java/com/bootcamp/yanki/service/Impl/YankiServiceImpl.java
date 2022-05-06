package com.bootcamp.retailclient.service.Impl;

import com.bootcamp.retailclient.model.Yanki;
import com.bootcamp.retailclient.repository.YankiRepository;
import com.bootcamp.retailclient.service.YankiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class YankiServiceImpl implements YankiService {
    public final YankiRepository repository;

    @Override
    public Mono<Yanki> create(Yanki yanki) {
        return repository.save(yanki);
    }

    @Override
    public Mono<Yanki> update(Yanki yanki) {
        return repository.save(yanki);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(Yanki yanki) {
        return repository.delete(yanki);
    }

    @Override
    public Flux<Yanki> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Yanki> getByDocumentId(String num) {
        return repository.findByDocumentId(num);
    }

}
