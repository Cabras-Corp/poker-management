package com.squeezecorp.pokermgm.dtos.player.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String password;

}
