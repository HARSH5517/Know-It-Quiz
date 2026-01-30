package com.knowit.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.knowit.quiz.managers.PreferencesManager;
import com.knowit.quiz.models.Question.Difficulty;

public class MainActivity extends AppCompatActivity {

    private TextView tvHighScore;
    private TextView tvTotalQuestions;
    private Button btnEasy;
    private Button btnMedium;
    private Button btnHard;

    private PreferencesManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsManager = new PreferencesManager(this);
        initializeViews();
        updateStatistics();
        setupClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatistics();
    }

    private void initializeViews() {
        tvHighScore = findViewById(R.id.tv_high_score);
        tvTotalQuestions = findViewById(R.id.tv_total_questions);
        btnEasy = findViewById(R.id.btn_easy);
        btnMedium = findViewById(R.id.btn_medium);
        btnHard = findViewById(R.id.btn_hard);
    }

    private void updateStatistics() {
        int highScore = prefsManager.getHighScore();
        int totalQuestions = prefsManager.getTotalQuestions();

        tvHighScore.setText("High Score: " + highScore);
        tvTotalQuestions.setText("Questions Answered: " + totalQuestions);
    }

    private void setupClickListeners() {
        btnEasy.setOnClickListener(v -> startQuiz(Difficulty.EASY));
        btnMedium.setOnClickListener(v -> startQuiz(Difficulty.MEDIUM));
        btnHard.setOnClickListener(v -> startQuiz(Difficulty.HARD));
    }

    private void startQuiz(Difficulty difficulty) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("DIFFICULTY", difficulty.name());
        startActivity(intent);
    }
}