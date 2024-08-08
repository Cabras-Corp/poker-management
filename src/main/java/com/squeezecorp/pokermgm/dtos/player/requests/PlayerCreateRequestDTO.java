package com.squeezecorp.pokermgm.dtos.player.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerCreateRequestDTO {

    @NotBlank(message = "O username não pode estar em branco!")
    @Size(min = 3, max = 20, message = "O username deve ter entre 3 e 20 caracteres!")
    private String username;

    @NotBlank(message = "O email não pode estar em branco!")
    @Email(message = "O email deve ser válido!")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
    private String password;

}