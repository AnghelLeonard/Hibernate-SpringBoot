package com.http.requests;

import com.services.PlayerService;
import com.jpa.model.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/fetch")
    public Player fetchPlayer() {

        return playerService.findFirstPlayer();
    }

    @PostMapping("/new")
    public Player newPlayer() {

        return playerService.newPlayerWithIdOne();
    }

}
