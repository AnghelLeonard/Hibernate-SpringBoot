package com.jpa.controller;

import com.jpa.domain.Player;
import com.jpa.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/{page}/{size}")
    public Page<Player> fetchPlayersByPageAndSizePath(@PathVariable int page, @PathVariable int size) {

        return playerService.fetchNextSlice(page, size);
    }

}
