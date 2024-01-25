package com.squeezecorp.pokermgm.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_PLAYER")
public class PlayerModel {
    public static final String TB_PLAYER = "Player";

    @Id // mapeia o id
    @GeneratedValue(strategy = GenerationType.AUTO) // Vai gerar um id automaticamente

    // Colunas da tabela TB_PLAYER
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    // Constructors
    public PlayerModel() {
    }

    public PlayerModel(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
