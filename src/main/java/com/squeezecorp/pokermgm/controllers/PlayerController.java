package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("Teste", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createPlayer(String username, String email, String password) {
        playerRepository.save(new PlayerModel(username, email, password));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerModel> findPlayerById(@PathVariable Long id) {
        Optional<PlayerModel> response = playerRepository.findById(id);
        return response.map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping({"{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        Optional<PlayerModel> response = playerRepository.findById(id);
        if (response.isPresent()) {
            playerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Void> updatePlayer(@PathVariable("id") Long id, @RequestBody PlayerModel updatedPlayer) {
        Optional<PlayerModel> response = playerRepository.findById(id);

        if (response.isPresent()) {
            PlayerModel existingPlayer = response.get();

            if (updatedPlayer.getUsername() != null) {
                existingPlayer.setUsername(updatedPlayer.getUsername());
            }

            if (updatedPlayer.getEmail() != null) {
                existingPlayer.setEmail(updatedPlayer.getEmail());
            }

            if (updatedPlayer.getPassword() != null) {
                existingPlayer.setPassword(updatedPlayer.getPassword());
            }

            playerRepository.save(existingPlayer);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}