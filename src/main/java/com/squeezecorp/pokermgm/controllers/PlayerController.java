package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.repository.PlayerRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Operation(summary = "Find All Players")
    @GetMapping
    public ResponseEntity<List<PlayerModel>> findAllPlayers() {
        List<PlayerModel> all = playerRepository.findAll();

        if (isEmpty(all)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @Operation(summary = "Create Player")
    @PostMapping("create")
    public ResponseEntity<Void> createPlayer(@RequestParam @NotBlank String username,
                                             @RequestParam @NotBlank String email,
                                             @RequestParam @NotBlank String password) {
        playerRepository.save(new PlayerModel(username, email, password));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Find Player by Id")
    @GetMapping("{id}")
    public ResponseEntity<PlayerModel> findPlayerById(@PathVariable Long id) {
        Optional<PlayerModel> response = playerRepository.findById(id);
        return response.map(playerModel -> new ResponseEntity<>(playerModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Operation(summary = "Delete Player by Id")
    @DeleteMapping({"delete/{id}"})
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        Optional<PlayerModel> response = playerRepository.findById(id);

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        playerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update Player by Id")
    @PutMapping("update/{id}")
    @Transactional
    public ResponseEntity<Void> updatePlayer(@PathVariable("id") Long id,
                                             @RequestParam @NotBlank String username,
                                             @RequestParam @NotBlank String email,
                                             @RequestParam @NotBlank String password) {

        Optional<PlayerModel> response = playerRepository.findById(id);
        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        PlayerModel existingPlayer = response.get();
        existingPlayer.setUsername(username);
        existingPlayer.setEmail(email);
        existingPlayer.setPassword(password);

        playerRepository.save(existingPlayer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}