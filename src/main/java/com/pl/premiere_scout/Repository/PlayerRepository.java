package com.pl.premiere_scout.Repository;

import com.pl.premiere_scout.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,String> {
    void deleteByName(String playerName);

    Optional<Player> findByName(String name);

    List<Player> findByNameContainingIgnoreCase(String name);

}
