package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.dtos.player.requests.LoginRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.service.player.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final PlayerService customerService;

    @Operation(summary = "Login Player")
    @PostMapping("login")
    public ResponseEntity<PlayerModel> login(@RequestBody LoginRequestDTO loginDTO) {
        Optional<PlayerModel> customer = customerService.authenticatePlayer(loginDTO);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }
}
