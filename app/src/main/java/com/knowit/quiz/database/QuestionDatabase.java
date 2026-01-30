package com.knowit.quiz.database;

import com.knowit.quiz.models.Question;
import com.knowit.quiz.models.Question.Difficulty;
import com.knowit.quiz.models.Question.QuestionType;
import java.util.ArrayList;
import java.util.List;

public class QuestionDatabase {

    private static QuestionDatabase instance;
    private List<Question> easyQuestions;
    private List<Question> mediumQuestions;
    private List<Question> hardQuestions;

    private QuestionDatabase() {
        initializeQuestions();
    }

    public static synchronized QuestionDatabase getInstance() {
        if (instance == null) {
            instance = new QuestionDatabase();
        }
        return instance;
    }

    private void initializeQuestions() {
        easyQuestions = new ArrayList<>();
        easyQuestions.add(new Question(
                1,
                "What does HTML stand for?",
                new String[]{
                        "Hyper Text Markup Language",
                        "High Tech Modern Language",
                        "Home Tool Markup Language",
                        "Hyperlinks and Text Markup Language"
                },
                new int[]{0},
                QuestionType.SINGLE_CHOICE,
                "HTML stands for Hyper Text Markup Language, the standard markup language for creating web pages.",
                Difficulty.EASY
        ));

        easyQuestions.add(new Question(
                2,
                "Which of these is a programming language?",
                new String[]{"Python", "HTML", "CSS", "JSON"},
                new int[]{0},
                QuestionType.SINGLE_CHOICE,
                "Python is a programming language. HTML and CSS are markup/styling languages, and JSON is a data format.",
                Difficulty.EASY
        ));

        easyQuestions.add(new Question(
                3,
                "What symbol is used for single-line comments in Java?",
                new String[]{"//", "/*", "#", "--"},
                new int[]{0},
                QuestionType.SINGLE_CHOICE,
                "In Java, // is used for single-line comments, while /* */ is used for multi-line comments.",
                Difficulty.EASY
        ));

        easyQuestions.add(new Question(
                4,
                "Which of the following are valid variable names? (Select all)",
                new String[]{"myVariable", "123variable", "_privateVar", "my-variable"},
                new int[]{0, 2},
                QuestionType.MULTIPLE_CHOICE,
                "Variable names typically can't start with numbers or contain hyphens. myVariable and _privateVar are valid.",
                Difficulty.EASY
        ));

        easyQuestions.add(new Question(
                5,
                "What does CPU stand for?",
                new String[]{
                        "Central Processing Unit",
                        "Computer Personal Unit",
                        "Central Program Utility",
                        "Control Processing Unit"
                },
                new int[]{0},
                QuestionType.SINGLE_CHOICE,
                "CPU stands for Central Processing Unit, the primary component that executes instructions.",
                Difficulty.EASY
        ));

        mediumQuestions = new ArrayList<>();
        mediumQuestions.add(new Question(
                6,
                "What is the time complexity of binary search?",
                new String[]{"O(n)", "O(log n)", "O(n²)", "O(1)"},
                new int[]{1},
                QuestionType.SINGLE_CHOICE,
                "Binary search has O(log n) time complexity as it divides the search space in half with each iteration.",
                Difficulty.MEDIUM
        ));

        mediumQuestions.add(new Question(
                7,
                "Which design patterns belong to Creational category? (Select all)",
                new String[]{"Singleton", "Observer", "Factory", "Adapter"},
                new int[]{0, 2},
                QuestionType.MULTIPLE_CHOICE,
                "Singleton and Factory are Creational patterns. Observer is Behavioral, and Adapter is Structural.",
                Difficulty.MEDIUM
        ));

        mediumQuestions.add(new Question(
                8,
                "In OOP, what does encapsulation refer to?",
                new String[]{
                        "Hiding implementation details",
                        "Creating multiple instances",
                        "Inheriting properties",
                        "Overloading methods"
                },
                new int[]{0},
                QuestionType.SINGLE_CHOICE,
                "Encapsulation refers to bundling data and methods together and hiding internal implementation details.",
                Difficulty.MEDIUM
        ));

        mediumQuestions.add(new Question(
                9,
                "Which statements about REST APIs are true? (Select all)",
                new String[]{
                        "REST uses HTTP methods",
                        "REST is stateless",
                        "REST requires XML format",
                        "REST supports caching"
                },
                new int[]{0, 1, 3},
                QuestionType.MULTIPLE_CHOICE,
                "REST uses HTTP methods, is stateless, and supports caching. It doesn't require XML; JSON is commonly used.",
                Difficulty.MEDIUM
        ));

        mediumQuestions.add(new Question(
                10,
                "What is a stack overflow?",
                new String[]{
                        "A popular Q&A website",
                        "When stack memory is exhausted",
                        "A type of sorting algorithm",
                        "A database error"
                },
                new int[]{1},
                QuestionType.SINGLE_CHOICE,
                "Stack overflow occurs when the call stack memory is exhausted, often due to infinite recursion.",
                Difficulty.MEDIUM
        ));

        hardQuestions = new ArrayList<>();
        hardQuestions.add(new Question(
                11,
                "What is the space complexity of merge sort?",
                new String[]{"O(1)", "O(log n)", "O(n)", "O(n log n)"},
                new int[]{2},
                QuestionType.SINGLE_CHOICE,
                "Merge sort requires O(n) additional space for the temporary arrays used during the merge process.",
                Difficulty.HARD
        ));

        hardQuestions.add(new Question(
                12,
                "Which concepts are fundamental to functional programming? (Select all)",
                new String[]{
                        "Immutability",
                        "Higher-order functions",
                        "Inheritance",
                        "Pure functions"
                },
                new int[]{0, 1, 3},
                QuestionType.MULTIPLE_CHOICE,
                "Immutability, higher-order functions, and pure functions are core to functional programming. Inheritance is OOP.",
                Difficulty.HARD
        ));

        hardQuestions.add(new Question(
                13,
                "In database normalization, what does 3NF eliminate?",
                new String[]{
                        "Partial dependencies",
                        "Transitive dependencies",
                        "Multi-valued dependencies",
                        "Join dependencies"
                },
                new int[]{1},
                QuestionType.SINGLE_CHOICE,
                "Third Normal Form (3NF) eliminates transitive dependencies where non-key attributes depend on other non-key attributes.",
                Difficulty.HARD
        ));

        hardQuestions.add(new Question(
                14,
                "Which statements about microservices are correct? (Select all)",
                new String[]{
                        "Services are independently deployable",
                        "All services share the same database",
                        "Services communicate via APIs",
                        "Supports polyglot programming"
                },
                new int[]{0, 2, 3},
                QuestionType.MULTIPLE_CHOICE,
                "Microservices are independently deployable, communicate via APIs, and support different languages.",
                Difficulty.HARD
        ));

        hardQuestions.add(new Question(
                15,
                "What is the CAP theorem in distributed systems?",
                new String[]{
                        "You can have Consistency, Availability, and Partition tolerance simultaneously",
                        "You can only guarantee two of: Consistency, Availability, Partition tolerance",
                        "Caching improves Application Performance",
                        "Containers Allow Portability"
                },
                new int[]{1},
                QuestionType.SINGLE_CHOICE,
                "CAP theorem states that in a distributed system, you can only guarantee two out of three: Consistency, Availability, and Partition tolerance.",
                Difficulty.HARD
        ));
    }

    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: return new ArrayList<>(easyQuestions);
            case MEDIUM: return new ArrayList<>(mediumQuestions);
            case HARD: return new ArrayList<>(hardQuestions);
            default: return new ArrayList<>(easyQuestions);
        }
    }
}
