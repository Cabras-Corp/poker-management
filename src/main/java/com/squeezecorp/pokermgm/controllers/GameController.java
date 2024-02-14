package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.Game;
import com.squeezecorp.pokermgm.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("Game")
public class GameController {

    @Autowired
    private GameRepository GameRepository;


    @PostMapping("create")
    public ResponseEntity<Game> createGame(LocalDateTime timeinit, Integer bigblind, Integer buyin, Integer rebuy, Double saldo) {
        GameRepository.save(new Game(timeinit, bigblind, buyin, rebuy, saldo));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> findGameById(@PathVariable Long id) {
        Optional<Game> response = GameRepository.findById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame (@PathVariable Long id) {
        Optional<Game> response = GameRepository.findById(id);
        if (response.isPresent()) {
            GameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateGame(@PathVariable Long id, LocalDateTime timeinit, Integer bigblind, Integer buyin, Integer rebuy, Double saldo) {
        Optional<Game> optionalGame = GameRepository.findById(id);

        if (optionalGame.isPresent()) {
            Game existingGame = optionalGame.get();
            existingGame.setTimeinit(timeinit);
            existingGame.setBigblind(bigblind);
            existingGame.setBuyin(buyin);
            existingGame.setRebuy(rebuy);
            existingGame.setSaldo(saldo);
            GameRepository.save(existingGame);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}