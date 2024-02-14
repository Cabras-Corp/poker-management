package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.Session;
import com.squeezecorp.pokermgm.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("session")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;


    @GetMapping
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("Teste", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Session> createGame(String local, LocalDateTime data_hora, Integer numero_sessao) {
        sessionRepository.save(new Session(local, data_hora, numero_sessao));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Session> findGameById(@PathVariable Long id) {
        Optional<Session> response = sessionRepository.findById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteGame (@PathVariable Long id) {
        Optional<Session> response = sessionRepository.findById(id);
        if (response.isPresent()) {
            sessionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateGame(@PathVariable Long id, String local, LocalDateTime data_hora, Integer numero_sessao) {
        Optional<Session> optionalSession = sessionRepository.findById(id);

        if (optionalSession.isPresent()) {
            Session existingSession = optionalSession.get();
            existingSession.setLocal(local);
            existingSession.setData_hora(data_hora);
            existingSession.setNumero_sessao(numero_sessao);
            sessionRepository.save(existingSession);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}