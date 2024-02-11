package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.dto.CreateSessionRequestDTO;
import com.squeezecorp.pokermgm.model.Session;
import com.squeezecorp.pokermgm.repository.SessionRepository;
import com.squeezecorp.pokermgm.service.session.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(summary = "Find All Sessions")
    @GetMapping
    public ResponseEntity<List<Session>> findAllSessions() {
        List<Session> all = sessionService.findAllSessions();

        if (isEmpty(all)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    @Operation(summary = "Create Session")
    @PostMapping("create")
    public ResponseEntity<Void> createSession(@RequestBody CreateSessionRequestDTO dto) {
        sessionService.createSession(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Find Session by Id")
    @GetMapping("{id}")
    public ResponseEntity<Session> findSessionById(@PathVariable Long id) {
        Optional<Session> response = sessionService.findSessionById(id);
        return response.map(session -> new ResponseEntity<>(session, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Operation(summary = "Delete Session by Id")
    @DeleteMapping({"delete/{id}"})
    public ResponseEntity<Session> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Update Session by Id")
    @PutMapping("update/{id}")
    @Transactional
    public ResponseEntity<Void> updateSession(@PathVariable("id") Long id, @RequestBody CreateSessionRequestDTO dto) {
        Optional<Session> response = sessionService.updateSession(id, dto);
        if (response.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
