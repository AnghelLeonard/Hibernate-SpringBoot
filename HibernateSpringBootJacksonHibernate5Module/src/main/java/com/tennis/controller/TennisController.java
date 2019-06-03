package com.tennis.controller;

import com.tennis.service.TennisService;
import com.tennis.entity.Tournament;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    private final TennisService tennisService;

    public TennisController(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    @GetMapping("/tournament")
    public Tournament fetchTournament() {
        return tennisService.fetchTournament();
    }
}
