package com.squeezecorp.pokermgm.service.player;

import com.squeezecorp.pokermgm.dtos.player.requests.LoginRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerDeleteRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerRequestDTO;
import com.squeezecorp.pokermgm.model.PlayerModel;
import com.squeezecorp.pokermgm.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerModel createPlayer(PlayerModel player) {

        Optional<PlayerModel> existingPlayer = playerRepository.findByEmail(player.getEmail());

        if (existingPlayer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Usuário já cadastrado.");
        }
//        PlayerModel newPlayer = new PlayerModel(player);

        return playerRepository.save(player);

    }

    public List<PlayerModel> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<PlayerModel> findPlayer(Long id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public Optional<PlayerModel> updatePlayer(Long id, PlayerRequestDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID e DTO não podem ser nulos");
        }

        Optional<PlayerModel> optionalPlayerModel = playerRepository.findById(id);
        if (optionalPlayerModel.isPresent()) {
            PlayerModel existingPlayer = optionalPlayerModel.get();
            existingPlayer.setUsername(dto.getUsername());
            existingPlayer.setEmail(dto.getEmail());

            try {
                PlayerModel updatedPlayer = playerRepository.save(existingPlayer);
                return Optional.of(updatedPlayer);
            } catch (DataAccessException e) {
                throw new RuntimeException("Erro ao atualizar o jogador: " + e.getMessage(), e);
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<PlayerModel> authenticatePlayer(LoginRequestDTO loginDTO) {
        Optional<PlayerModel> customer = playerRepository.findByEmail(loginDTO.getEmail());
        if (customer.isPresent() && customer.get().getPassword().equals(loginDTO.getPassword())) {
            return customer;
        }
        return Optional.empty();
    }

    @Transactional
    public void deletePlayer(PlayerDeleteRequestDTO dto) {
        playerRepository.deleteById(dto.getId());
    }

}