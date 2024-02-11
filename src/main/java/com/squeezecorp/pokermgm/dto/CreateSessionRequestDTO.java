package com.squeezecorp.pokermgm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateSessionRequestDTO {

    private String local;
    private LocalDateTime data_hora;
    private Integer numero_sessao;
    private LocalDateTime data_hora_fim;
}
