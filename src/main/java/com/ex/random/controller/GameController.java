package com.ex.random.controller;

import com.ex.random.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/guess")
    public String makeGuess(@RequestBody int[] attempt) {
        if (attempt.length != 4) {
            return "Please provide exactly 4 numbers!";
        }
        return gameService.guess(attempt);
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return gameService.getGame().getHistory();
    }

    @PostMapping("/reset")
    public String resetGame() {
        gameService.resetGame();
        return "Game has been reset. A new answer has been generated!";
    }

}