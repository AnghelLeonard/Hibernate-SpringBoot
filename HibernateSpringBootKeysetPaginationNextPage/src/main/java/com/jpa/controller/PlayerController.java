package com.jpa.controller;

import com.jpa.domain.Player;
import com.jpa.service.PlayerService;
import java.util.List;
import java.util.Map;
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
    public Map<List<Player>, Boolean> fetchPlayersByIdAndLimit(@PathVariable long id, @PathVariable int limit) {

        return playerService.fetchNextSlice(id, limit);
    }

}
