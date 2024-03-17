package com.squeezecorp.pokermgm.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateSessionRequestDTO {

    private String local;
    private LocalDateTime startDateTime;
    private Integer numberSession;
    private LocalDateTime endDateTime;
    private Double balance;
}
