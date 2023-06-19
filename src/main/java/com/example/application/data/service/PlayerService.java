package com.example.application.data.service;

import com.example.application.data.entity.Players;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Optional<Players> get(Long id) {
        return repository.findById(id);
    }

    public Players update(Players entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Players> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Players> list(Pageable pageable, Specification<Players> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }
}
