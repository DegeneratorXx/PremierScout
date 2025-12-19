package com.pl.premiere_scout.Service;

import com.pl.premiere_scout.Repository.PlayerRepository;
import com.pl.premiere_scout.Entity.Player;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    public final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }

    public List<Player> getPlayer(){
        return playerRepository.findAll();
    }

    public Page<Player> getPlayersPaginated(Pageable pageable){
        return playerRepository.findAll(pageable);
    }
    public List<Player> getPlayerByTeam(String teamName) {
        if (teamName == null) return List.of(); // optional: handle null input
        return playerRepository.findAll().stream()
                .filter(player -> player.getTeam() != null && teamName.equalsIgnoreCase(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByName(String pName){
        if (pName == null) return List.of();
        return playerRepository.findAll().stream()
                .filter(player -> player.getName() != null &&
                        player.getName().toLowerCase().contains(pName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByPosition(String position){
        if (position == null) return List.of();
        return playerRepository.findAll().stream()
                .filter(player -> player.getPos() != null &&
                        player.getPos().toLowerCase().contains(position.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByNation(String nation){
        if (nation == null) return List.of();
        return playerRepository.findAll().stream()
                .filter(player -> player.getNation() != null &&
                        player.getNation().toLowerCase().contains(nation.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByTeamAnPosition(String team, String position){
        if (team == null || position == null) return List.of();
        return playerRepository.findAll().stream()
                .filter(player -> player.getTeam() != null &&
                        player.getPos() != null &&
                        team.equalsIgnoreCase(player.getTeam()) &&
                        position.equalsIgnoreCase(player.getPos()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer=playerRepository.findByName(updatedPlayer.getName());

        if(existingPlayer.isPresent()){
            Player playerToUpdate=existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setNation(updatedPlayer.getNation());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setTeam(updatedPlayer.getTeam());

            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return  null;
    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByName(playerName);
    }

}
