package com.ex.random.service;

import com.ex.random.model.Game;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GameService {
    private Game game = new Game();

    public String guess(int[] attempt) {
        if (!isValidAttempt(attempt)) {
            return "Invalid input: Please provide 4 unique numbers between 0 and 9.";
        }

        int strike = 0;
        int ball = 0;

        int[] answer = game.getAnswer();
        for (int i = 0; i < 4; i++) {
            if (attempt[i] == answer[i]) {
                strike++;
            } else if (contains(answer, attempt[i])) {
                ball++;
            }
        }

        String result = String.format("Attempt: %d%d%d%d - %d Strike(s), %d Ball(s)",
                attempt[0], attempt[1], attempt[2], attempt[3], strike, ball);

        game.addHistory(result);

        if (strike == 4) {  // 정답을 맞췄을 때
            result += String.format(" 🎉 You guessed it in %d attempts!", game.getAttemptCount());
        }

        return result;
    }

    public void resetGame() {
        game = new Game();  // 새로운 게임 객체 생성
    }

    private boolean isValidAttempt(int[] attempt) {
        if (attempt.length != 4) {
            return false;
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int num : attempt) {
            if (num < 0 || num > 9 || !uniqueNumbers.add(num)) {
                return false;  // 숫자가 범위를 벗어나거나 중복된 경우
            }
        }
        return true;
    }

    private boolean contains(int[] arr, int num) {
        for (int n : arr) {
            if (n == num) return true;
        }
        return false;
    }

    public Game getGame() {
        return game;
    }
}