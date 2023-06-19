package com.example.application.data.service;

import com.example.application.data.entity.Users;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsersService {
    private EntityManager entityManager;
    private final UsersRepository repository;


    @Autowired
    public UsersService(EntityManager entityManager, UsersRepository repository) {
        this.entityManager = entityManager;
        this.repository = repository;
    }

    @Transactional
    public void updateUser(Users user) {
        entityManager.merge(user);
    }

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public Optional<Users> get(Long id) {
        return repository.findById(id);
    }

    public Users update(Users entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Users> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Users> list(Pageable pageable, Specification<Users> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
