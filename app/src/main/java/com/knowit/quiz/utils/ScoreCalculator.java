package com.knowit.quiz.utils;

import com.knowit.quiz.models.Question;

public class ScoreCalculator {

    private static final int EASY_POINTS = 10;
    private static final int MEDIUM_POINTS = 20;
    private static final int HARD_POINTS = 30;

    public static int calculatePoints(Question.Difficulty difficulty, boolean isCorrect) {
        if (!isCorrect) return 0;

        switch (difficulty) {
            case EASY: return EASY_POINTS;
            case MEDIUM: return MEDIUM_POINTS;
            case HARD: return HARD_POINTS;
            default: return 0;
        }
    }
}