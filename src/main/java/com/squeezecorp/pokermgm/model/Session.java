package com.squeezecorp.pokermgm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "local", length = 50, nullable = false)
    private String local;

    @Column(name = "data_hora", nullable = false, unique = true)
    private LocalDateTime data_hora;

    @Column(name = "numero_sessao", nullable = false, unique = true)
    private Integer numero_sessao;

    public Session (String local, LocalDateTime data_hora, Integer numero_sessao){
        this.local = local;
        this.data_hora = data_hora;
        this.numero_sessao = numero_sessao;
    }

    public Session() {

    }

}
