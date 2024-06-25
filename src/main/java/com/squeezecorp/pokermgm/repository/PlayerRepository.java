package com.squeezecorp.pokermgm.repository;

import com.squeezecorp.pokermgm.model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {

    Optional<PlayerModel> findByEmail(String email);
}
