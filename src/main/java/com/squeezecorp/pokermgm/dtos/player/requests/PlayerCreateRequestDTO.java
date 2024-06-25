package com.squeezecorp.pokermgm.dtos.player.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCreateRequestDTO {

    private String username;
    private String email;
    private String password;

}
