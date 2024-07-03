package com.squeezecorp.pokermgm.dtos.player.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerRequestDTO {

    private Long id;
    private String username;
    private String email;
    private String password;

}
