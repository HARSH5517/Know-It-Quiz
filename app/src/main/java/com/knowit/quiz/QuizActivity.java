package com.knowit.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.knowit.quiz.managers.PreferencesManager;
import com.knowit.quiz.managers.QuizManager;
import com.knowit.quiz.models.Question;
import com.knowit.quiz.models.Question.Difficulty;
import com.knowit.quiz.utils.AnswerValidator;
import com.knowit.quiz.utils.ScoreCalculator;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvLives;
    private TextView tvScore;
    private TextView tvQuestionNumber;
    private TextView tvQuestion;
    private TextView tvFeedback;
    private RadioGroup radioGroup;
    private LinearLayout checkboxContainer;
    private Button btnSubmit;
    private ProgressBar progressBar;

    private QuizManager quizManager;
    private PreferencesManager prefsManager;
    private List<Integer> selectedAnswers;
    private int currentLives;
    private int currentScore;
    private List<Question> wrongQuestions;
    private boolean answered;

    private static final int MAX_LIVES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String difficultyStr = getIntent().getStringExtra("DIFFICULTY");
        Difficulty difficulty = Difficulty.valueOf(difficultyStr);

        quizManager = new QuizManager(difficulty);
        prefsManager = new PreferencesManager(this);

        selectedAnswers = new ArrayList<>();
        currentLives = MAX_LIVES;
        currentScore = 0;
        wrongQuestions = new ArrayList<>();
        answered = false;

        initializeViews();
        displayCurrentQuestion();
    }

    private void initializeViews() {
        tvLives = findViewById(R.id.tv_lives);
        tvScore = findViewById(R.id.tv_score);
        tvQuestionNumber = findViewById(R.id.tv_question_number);
        tvQuestion = findViewById(R.id.tv_question);
        tvFeedback = findViewById(R.id.tv_feedback);
        radioGroup = findViewById(R.id.radio_group_answers);
        checkboxContainer = findViewById(R.id.checkbox_container);
        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progress_bar);

        btnSubmit.setOnClickListener(v -> {
            if (!answered) {
                handleSubmit();
            } else {
                nextQuestion();
            }
        });
    }

    private void displayCurrentQuestion() {
        Question question = quizManager.getCurrentQuestion();
        if (question == null) {
            finishQuiz();
            return;
        }

        selectedAnswers.clear();
        tvFeedback.setVisibility(View.GONE);
        answered = false;
        btnSubmit.setText("Submit Answer");
        btnSubmit.setEnabled(false);

        tvQuestion.setText(question.getQuestionText());
        tvQuestionNumber.setText("Question " + (quizManager.getCurrentQuestionIndex() + 1) +
                "/" + quizManager.getTotalQuestions());

        radioGroup.removeAllViews();
        checkboxContainer.removeAllViews();

        if (question.getType() == Question.QuestionType.SINGLE_CHOICE) {
            radioGroup.setVisibility(View.VISIBLE);
            checkboxContainer.setVisibility(View.GONE);
            displaySingleChoice(question);
        } else {
            radioGroup.setVisibility(View.GONE);
            checkboxContainer.setVisibility(View.VISIBLE);
            displayMultipleChoice(question);
        }

        updateUI();
    }

    private void displaySingleChoice(Question question) {
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioButton.setId(i);
            radioButton.setPadding(24, 24, 24, 24);
            radioButton.setTextSize(16);

            final int index = i;
            radioButton.setOnClickListener(v -> {
                selectedAnswers.clear();
                selectedAnswers.add(index);
                btnSubmit.setEnabled(true);
            });

            radioGroup.addView(radioButton);
        }
    }

    private void displayMultipleChoice(Question question) {
        TextView hint = new TextView(this);
        hint.setText("Select all correct answers");
        hint.setTextSize(14);
        hint.setTextColor(Color.GRAY);
        hint.setPadding(16, 8, 16, 16);
        checkboxContainer.addView(hint);

        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(options[i]);
            checkBox.setId(i);
            checkBox.setPadding(24, 24, 24, 24);
            checkBox.setTextSize(16);

            final int index = i;
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedAnswers.add(index);
                } else {
                    selectedAnswers.remove(Integer.valueOf(index));
                }
                btnSubmit.setEnabled(!selectedAnswers.isEmpty());
            });

            checkboxContainer.addView(checkBox);
        }
    }

    private void handleSubmit() {
        if (selectedAnswers.isEmpty()) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        Question current = quizManager.getCurrentQuestion();
        boolean isCorrect = AnswerValidator.validateAnswer(current, selectedAnswers);

        answered = true;
        tvFeedback.setVisibility(View.VISIBLE);

        if (isCorrect) {
            tvFeedback.setBackgroundColor(Color.parseColor("#C8E6C9"));
            tvFeedback.setText("✓ Correct! " + current.getExplanation());
            int points = ScoreCalculator.calculatePoints(current.getDifficulty(), true);
            currentScore += points;
            prefsManager.saveHighScore(currentScore);
        } else {
            tvFeedback.setBackgroundColor(Color.parseColor("#FFCDD2"));
            tvFeedback.setText("✗ Incorrect. " + current.getExplanation());
            currentLives--;
            wrongQuestions.add(current);

            if (currentLives == 0) {
                btnSubmit.postDelayed(() -> {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("FINAL_SCORE", currentScore);
                    intent.putExtra("TOTAL_QUESTIONS", quizManager.getTotalQuestions());
                    intent.putExtra("CORRECT_ANSWERS",
                            quizManager.getTotalQuestions() - wrongQuestions.size());
                    intent.putExtra("GAME_OVER", true);
                    startActivity(intent);
                    finish();
                }, 2000);
                return;
            }
        }

        updateUI();
        btnSubmit.setText("Next Question");
    }

    private void nextQuestion() {
        quizManager.nextQuestion();
        displayCurrentQuestion();
    }

    private void updateUI() {
        tvScore.setText("Score: " + currentScore);

        StringBuilder livesText = new StringBuilder("Lives: ");
        for (int i = 0; i < MAX_LIVES; i++) {
            livesText.append(i < currentLives ? "❤" : "🖤");
        }
        tvLives.setText(livesText.toString());

        int progress = (int) ((quizManager.getCurrentQuestionIndex() + 1) * 100.0
                / quizManager.getTotalQuestions());
        progressBar.setProgress(progress);
    }

    private void finishQuiz() {
        int correctAnswers = quizManager.getTotalQuestions() - wrongQuestions.size();
        prefsManager.updateStatistics(quizManager.getTotalQuestions(), correctAnswers);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("FINAL_SCORE", currentScore);
        intent.putExtra("TOTAL_QUESTIONS", quizManager.getTotalQuestions());
        intent.putExtra("CORRECT_ANSWERS", correctAnswers);
        intent.putExtra("GAME_OVER", false);
        startActivity(intent);
        finish();
    }
}