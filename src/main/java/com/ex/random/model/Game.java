package com.ex.random.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int[] answer;  // 정답 숫자 배열
    private List<String> history = new ArrayList<>();  // 시도 내역 저장\
    private int attemptCount;

    public Game() {
        this.answer = generateAnswer();
        this.attemptCount = 0;
    }

    private int[] generateAnswer() {
        int[] result = new int[4];
        boolean[] used = new boolean[10];  // 숫자 사용 여부 기록

        for (int i = 0; i < 4; i++) {
            int num;
            do {
                num = (int) (Math.random() * 10);  // 0~9 범위의 숫자
            } while (used[num]);  // 이미 사용된 숫자일 경우 반복
            used[num] = true;  // 사용 여부 업데이트
            result[i] = num;
        }
        return result;
    }

    public int[] getAnswer() {
        return answer;
    }

    public List<String> getHistory() {
        return history;
    }

    public void addHistory(String attempt) {
        history.add(attempt);
        attemptCount++;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}