package com.squeezecorp.pokermgm.mapper;

import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.responses.PlayerResponseDTO;
import com.squeezecorp.pokermgm.model.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    public static PlayerModel toPlayerCreateRequest(PlayerCreateRequestDTO playerRequest) {

        PlayerModel request = new PlayerModel();

        request.setUsername(playerRequest.getUsername());
        request.setEmail(playerRequest.getEmail());
        request.setPassword(playerRequest.getPassword());

        return request;

    }

    public static PlayerModel toPlayerRequest(PlayerRequestDTO playerRequest) {

        PlayerModel request = new PlayerModel();

        request.setUsername(playerRequest.getUsername());
        request.setEmail(playerRequest.getEmail());
        request.setPassword(playerRequest.getPassword());

        return request;

    }

    public static PlayerResponseDTO toPlayerResponse(PlayerModel playerResponse) {

        PlayerResponseDTO response = new PlayerResponseDTO();

        response.setId(playerResponse.getId());
        response.setUsername(playerResponse.getUsername());
        response.setEmail(playerResponse.getEmail());
        response.setPassword(playerResponse.getPassword());

        return response;

    }

    public static List<PlayerResponseDTO> toPacienteResponseList(List<PlayerModel> players) {
        List<PlayerResponseDTO> responses = new ArrayList<>();
        for (PlayerModel player : players) {
            responses.add(toPlayerResponse(player));
        }
        return responses;
    }

}
