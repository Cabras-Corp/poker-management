package com.squeezecorp.pokermgm.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "timeinit")
    private LocalDateTime timeinit;

    @Column(name = "bigblind", length = 10)
    private Integer bigblind;

    @Column(name = "buyin", nullable = false)
    private Integer buyin;

    @Column(name = "rebuy")
    private Integer rebuy;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    public Game (LocalDateTime timeinit, Integer bigblind, Integer buyin, Integer rebuy, Double saldo) {
        this.timeinit = timeinit;
        this.bigblind = bigblind;
        this.buyin = buyin;
        this.rebuy = rebuy;
        this.saldo = saldo;
    }


}

