package com.squeezecorp.pokermgm.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateSessionRequestDTO {

    private String local;
    private LocalDateTime date_time;
    private Integer number_session;
    private LocalDateTime end_date_time;
}
