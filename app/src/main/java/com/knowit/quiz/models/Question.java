package com.knowit.quiz.models;

import java.io.Serializable;

public class Question implements Serializable {

    private int id;
    private String questionText;
    private String[] options;
    private int[] correctAnswers;
    private QuestionType type;
    private String explanation;
    private Difficulty difficulty;

    public enum QuestionType {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE
    }

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public Question(int id, String questionText, String[] options,
                    int[] correctAnswers, QuestionType type,
                    String explanation, Difficulty difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.type = type;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    // Getters
    public int getId() { return id; }
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int[] getCorrectAnswers() { return correctAnswers; }
    public QuestionType getType() { return type; }
    public String getExplanation() { return explanation; }
    public Difficulty getDifficulty() { return difficulty; }
}