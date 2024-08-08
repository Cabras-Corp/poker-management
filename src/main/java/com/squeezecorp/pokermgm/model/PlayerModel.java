package com.squeezecorp.pokermgm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squeezecorp.pokermgm.dtos.player.requests.PlayerCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "player_tb")
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false, columnDefinition = "VARCHAR(50) COLLATE utf8_bin")
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true, columnDefinition = "VARCHAR(50) COLLATE utf8_bin")
    private String username;

    @Column(name = "email", length = 50, nullable = false, unique = true, columnDefinition = "VARCHAR(50) COLLATE utf8_bin")
    private String email;

    @Column(name = "password", length = 50, nullable = false, columnDefinition = "VARCHAR(50) COLLATE utf8_bin")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sessions", referencedColumnName = "id")
    private Session session;

    public PlayerModel(PlayerCreateRequestDTO dto) {
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}
