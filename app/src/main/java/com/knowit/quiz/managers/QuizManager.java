package com.knowit.quiz.managers;

import com.knowit.quiz.database.QuestionDatabase;
import com.knowit.quiz.models.Question;
import com.knowit.quiz.models.Question.Difficulty;
import java.util.List;

public class QuizManager {

    private List<Question> questions;
    private int currentQuestionIndex;
    private Difficulty difficulty;

    public QuizManager(Difficulty difficulty) {
        this.difficulty = difficulty;
        QuestionDatabase database = QuestionDatabase.getInstance();
        this.questions = database.getQuestionsByDifficulty(difficulty);
        this.currentQuestionIndex = 0;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}