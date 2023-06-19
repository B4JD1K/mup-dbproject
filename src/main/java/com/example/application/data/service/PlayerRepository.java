package com.example.application.data.service;

import com.example.application.data.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository
        extends
            JpaRepository<Players, Long>,
            JpaSpecificationExecutor<Players> {

}
