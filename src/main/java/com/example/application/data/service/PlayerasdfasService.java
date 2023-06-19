//package com.example.application.data.service;
//import com.example.application.data.entity.Player;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PlayerSssservice {
//
//    private final PlayerRepository playerRepository;
//
//    public PlayerService(PlayerRepository playerRepository) {
//        this.playerRepository = playerRepository;
//    }
//
//    public Optional<Player> get(Long id) {
//        return playerRepository.findById(id);
//    }
//
//    public Player create(Player player) {
//        return playerRepository.save(player);
//    }
//
//    public Player update(Player player) {
//        return playerRepository.save(player);
//    }
//
//    public void delete(Long id) {
//        playerRepository.deleteById(id);
//    }
//
//    public List<Player> getAllPlayers() {
//        return playerRepository.findAll();
//    }
//}