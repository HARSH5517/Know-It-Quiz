package com.knowit.quiz.managers;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREF_NAME = "QuizPreferences";
    private static final String KEY_HIGH_SCORE = "high_score";
    private static final String KEY_TOTAL_QUESTIONS = "total_questions";
    private static final String KEY_CORRECT_ANSWERS = "correct_answers";

    private SharedPreferences prefs;

    public PreferencesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveHighScore(int score) {
        int currentHigh = getHighScore();
        if (score > currentHigh) {
            prefs.edit().putInt(KEY_HIGH_SCORE, score).apply();
        }
    }

    public int getHighScore() {
        return prefs.getInt(KEY_HIGH_SCORE, 0);
    }

    public void updateStatistics(int questionsAnswered, int correctAnswers) {
        int totalQuestions = prefs.getInt(KEY_TOTAL_QUESTIONS, 0);
        int totalCorrect = prefs.getInt(KEY_CORRECT_ANSWERS, 0);

        prefs.edit()
                .putInt(KEY_TOTAL_QUESTIONS, totalQuestions + questionsAnswered)
                .putInt(KEY_CORRECT_ANSWERS, totalCorrect + correctAnswers)
                .apply();
    }

    public int getTotalQuestions() {
        return prefs.getInt(KEY_TOTAL_QUESTIONS, 0);
    }

    public int getCorrectAnswers() {
        return prefs.getInt(KEY_CORRECT_ANSWERS, 0);
    }
}