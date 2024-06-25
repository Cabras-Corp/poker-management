package com.squeezecorp.pokermgm.controllers;

import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerDeleteRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.responses.PlayerResponseDTO;
import com.squeezecorp.pokermgm.mapper.PlayerMapper;
import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.service.player.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @Operation(summary = "Create Player")
    @PostMapping("create")
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody PlayerCreateRequestDTO dto) {

        PlayerModel player = PlayerMapper.toPlayerCreateRequest(dto);

        PlayerModel createPlayer = playerService.createPlayer(player);

        PlayerResponseDTO playerResponse = PlayerMapper.toPlayerResponse(createPlayer);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerResponse);
    }

    @Operation(summary = "Find All Players")
    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> findAllPlayers() {

        List<PlayerModel> allPlayers = playerService.findAllPlayers();
        List<PlayerResponseDTO> playersResponse = PlayerMapper.toPacienteResponseList(allPlayers);

        if (isEmpty(playersResponse)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.status(HttpStatus.OK).body(playersResponse);
    }

    @Operation(summary = "Find Player by Id")
    @GetMapping("{id}")
    public ResponseEntity<PlayerModel> findPlayerById(@PathVariable Long id) {

        Optional<PlayerModel> playerIdResponse = playerService.findPlayer(id);

        if (playerIdResponse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PlayerModel playerFound = playerIdResponse.get();

        return ResponseEntity.status(HttpStatus.OK).body(playerFound);
    }

    @Operation(summary = "Update Player by Id")
    @PutMapping("update/{id}")
    @Transactional
    public ResponseEntity<PlayerModel> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDTO dto) {

        if (id == null || dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<PlayerModel> updatedPlayer = playerService.updatePlayer(id, dto);
        if (updatedPlayer.isPresent()) {
            return ResponseEntity.ok(updatedPlayer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete Player by Id")
    @DeleteMapping({"delete/{id}"})
    public ResponseEntity<Void> deletePlayer(@RequestBody PlayerDeleteRequestDTO dto) {

        Optional<PlayerModel> deleteResponse = playerService.findPlayer(dto.getId());

        if (deleteResponse.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        playerService.deletePlayer(dto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}