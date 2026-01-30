package com.knowit.quiz.utils;

import com.knowit.quiz.models.Question;
import java.util.List;

public class AnswerValidator {

    public static boolean validateAnswer(Question question, List<Integer> selectedAnswers) {
        if (selectedAnswers == null || selectedAnswers.isEmpty()) {
            return false;
        }

        int[] correctAnswers = question.getCorrectAnswers();

        if (selectedAnswers.size() != correctAnswers.length) {
            return false;
        }

        for (int correctIndex : correctAnswers) {
            if (!selectedAnswers.contains(correctIndex)) {
                return false;
            }
        }

        return true;
    }
}
