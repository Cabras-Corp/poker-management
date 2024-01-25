package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("player")
public class PlayerController {

    // Ponto de injeção
    @Autowired
    private PlayerRepository playerRepository; // Instanciando

    public ResponseEntity<String> getTest() {
        return new ResponseEntity<java.lang.String>("Teste", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<PlayerModel> createPlayer() {
        playerRepository.save(new PlayerModel());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerModel> findPlayerById(@PathVariable Long id) {
        Optional<PlayerModel> response = playerRepository.findById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

}
