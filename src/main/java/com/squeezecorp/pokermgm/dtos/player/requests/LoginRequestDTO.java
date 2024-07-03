package com.squeezecorp.pokermgm.dtos.player.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {

    @NotBlank(message = "O email não pode estar em branco!")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco!")
    private String password;
}