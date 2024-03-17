package com.squeezecorp.pokermgm.model;

import com.squeezecorp.pokermgm.dto.session.CreateSessionRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    @Column(name = "startDateTime", nullable = false, unique = true)
    private LocalDateTime startDateTime;

    @Column(name = "numberSession", nullable = false, unique = true)
    private Integer numberSession;

    @Column(name= "endDateTime", nullable = false, unique = true)
    private LocalDateTime endDateTime;

    @Column(name = "Balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Game> games;

    public Session(CreateSessionRequestDTO dto) {
        this.local=dto.getLocal();
        this.startDateTime=dto.getStartDateTime();
        this.numberSession =dto.getNumberSession();
        this.endDateTime=dto.getEndDateTime();
        this.balance=dto.getBalance();
        this.games=new ArrayList<>();
    }
}
