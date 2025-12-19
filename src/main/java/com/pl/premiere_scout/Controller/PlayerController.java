package com.pl.premiere_scout.Controller;

import com.pl.premiere_scout.Entity.Player;
import com.pl.premiere_scout.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    @GetMapping(path = "page")
    public Page<Player> getPlayersInPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page,size);
        return playerService.getPlayersPaginated(pageable);
    }

    @GetMapping
    public List<Player> getPlayers(

            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation){


        if(team!=null && position!=null)
            return playerService.getPlayerByTeamAnPosition(team,position);

        else if(team!=null)
            return playerService.getPlayerByTeam(team);

        else if (name !=null)
            return playerService.getPlayerByName(name);

        else if (position!=null)
            return playerService.getPlayerByPosition(position);

        else if(nation!=null)
            return playerService.getPlayerByNation(nation);

        else
            return playerService.getPlayer();
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player){
        try {
            Player createdPlayer = playerService.addPlayer(player);
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player updatedPlayer = playerService.updatePlayer(player);
        if(updatedPlayer !=null)
            return new ResponseEntity<>(updatedPlayer,HttpStatus.OK);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("'Player deleted successfully",HttpStatus.OK);
    }
}
