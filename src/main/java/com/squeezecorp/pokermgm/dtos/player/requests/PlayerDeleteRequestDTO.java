package com.squeezecorp.pokermgm.dtos.player.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDeleteRequestDTO {

    @NotNull(message = "O ID não pode estar em branco!")
    private Long id;

}
