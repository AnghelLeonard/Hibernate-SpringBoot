package com.jpa.controller;

import com.jpa.domain.Player;
import com.jpa.service.PlayerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/{id}/{limit}")
    public List<Player> fetchPlayersByIdAndSizeLimit(@PathVariable long id, @PathVariable int limit) {

        return playerService.fetchNextSlice(id, limit);
    }

}
