package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.model.Session;
import com.squeezecorp.pokermgm.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Session> createSession() {
        sessionRepository.save(new Session());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Session> createId(@PathVariable Long id) {
        Optional<Session> response = sessionRepository.findById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

}
