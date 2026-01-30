package com.knowit.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvFinalScore;
    private TextView tvCorrectAnswers;
    private TextView tvAccuracy;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        int correctAnswers = getIntent().getIntExtra("CORRECT_ANSWERS", 0);
        boolean gameOver = getIntent().getBooleanExtra("GAME_OVER", false);

        initializeViews();
        displayResults(finalScore, totalQuestions, correctAnswers, gameOver);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void initializeViews() {
        tvTitle = findViewById(R.id.tv_title);
        tvFinalScore = findViewById(R.id.tv_final_score);
        tvCorrectAnswers = findViewById(R.id.tv_correct_answers);
        tvAccuracy = findViewById(R.id.tv_accuracy);
        btnHome = findViewById(R.id.btn_home);
    }

    private void displayResults(int score, int total, int correct, boolean gameOver) {
        if (gameOver) {
            tvTitle.setText("Game Over!");
            tvTitle.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            tvTitle.setText("Quiz Complete! 🏆");
        }

        tvFinalScore.setText("Score: " + score);
        tvCorrectAnswers.setText("Correct: " + correct + "/" + total);

        int accuracy = total > 0 ? (correct * 100) / total : 0;
        tvAccuracy.setText("Accuracy: " + accuracy + "%");
    }
}
