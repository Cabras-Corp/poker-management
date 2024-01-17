package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.Game;
import com.squeezecorp.pokermgm.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;


    @GetMapping("teste")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("Teste", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Game> createGame() {
        gameRepository.save(new Game());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Game>> findAllGames() {
        List<Game> all = gameRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> findGameById(@PathVariable Long id) {
        Optional<Game> response = gameRepository.findById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        gameRepository.findById(id).ifPresent(game -> gameRepository.delete(game));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PUT - ATUALIZAR


}