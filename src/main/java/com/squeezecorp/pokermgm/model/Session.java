package com.squeezecorp.pokermgm.model;

import com.squeezecorp.pokermgm.dtos.session.CreateSessionRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "session_tb")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local", length = 50, nullable = false)
    private String local;

    @Column(name = "date_time", nullable = false, unique = true)
    private LocalDateTime date_time;

    @Column(name = "number_session", nullable = false, unique = true)
    private Integer number_session;

    @Column(name= "end_date_time", nullable = false, unique = true)
    private LocalDateTime end_date_time;

    public Session(CreateSessionRequestDTO dto) {
        this.local=dto.getLocal();
        this.date_time=dto.getDate_time();
        this.number_session=dto.getNumber_session();
        this.end_date_time=dto.getEnd_date_time();
    }
}
