package com.squeezecorp.pokermgm.model;

import com.squeezecorp.pokermgm.dto.CreateSessionRequestDTO;
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

    @Column(name = "data_hora", nullable = false, unique = true)
    private LocalDateTime data_hora;

    @Column(name = "numero_sessao", nullable = false, unique = true)
    private Integer numero_sessao;

    @Column(name= "data_hora_fim", nullable = false, unique = true)
    private LocalDateTime data_hora_fim;

    public Session(CreateSessionRequestDTO dto) {
        this.local=dto.getLocal();
        this.data_hora=dto.getData_hora();
        this.numero_sessao=dto.getNumero_sessao();
        this.data_hora_fim=dto.getData_hora_fim();
    }
}
