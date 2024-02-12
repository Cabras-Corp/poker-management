package com.squeezecorp.pokermgm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateSessionRequestDTO {
    private String local;
    private LocalDateTime date_time;
    private Integer number_session;
    private LocalDateTime end_date_time;
}
