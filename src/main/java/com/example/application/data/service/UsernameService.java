//package com.example.application.data.service;
//
//import com.example.application.data.entity.Username;
//import java.util.Optional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsernameService {
//
//    private final UsernameRepository repository;
//
//    public UsernameService(UsernameRepository repository) {
//        this.repository = repository;
//    }
//
//    public Optional<Username> get(Long id) {
//        return repository.findById(id);
//    }
//
//    public Username update(Username entity) {
//        return repository.save(entity);
//    }
//
//
//    public Page<Username> list(Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    public Page<Username> list(Pageable pageable, Specification<Username> filter) {
//        return repository.findAll(filter, pageable);
//    }
//}
