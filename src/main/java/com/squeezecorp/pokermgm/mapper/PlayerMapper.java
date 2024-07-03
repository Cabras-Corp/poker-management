package com.squeezecorp.pokermgm.mapper;

import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerRequestDTO;
import com.squeezecorp.pokermgm.dtos.player.responses.PlayerResponseDTO;
import com.squeezecorp.pokermgm.model.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerModel toPlayerModelFromCreateRequestDTO(PlayerCreateRequestDTO playerRequest) {
        PlayerModel model = new PlayerModel();

        model.setUsername(playerRequest.getUsername());
        model.setEmail(playerRequest.getEmail());
        model.setPassword(playerRequest.getPassword());

        return model;
    }

    public static PlayerModel toPlayerModelFromRequestDTO(PlayerRequestDTO playerRequest) {
        PlayerModel model = new PlayerModel();

        model.setUsername(playerRequest.getUsername());
        model.setEmail(playerRequest.getEmail());
        model.setPassword(playerRequest.getPassword());

        return model;
    }

    public static PlayerResponseDTO toPlayerResponseDTO(PlayerModel playerModel) {
        PlayerResponseDTO dto = new PlayerResponseDTO();

        dto.setId(playerModel.getId());
        dto.setUsername(playerModel.getUsername());
        dto.setEmail(playerModel.getEmail());

        return dto;
    }

    public static List<PlayerResponseDTO> toPlayerResponseDTOList(List<PlayerModel> players) {
        return players.stream()
                .map(PlayerMapper::toPlayerResponseDTO)
                .collect(Collectors.toList());
    }

}
