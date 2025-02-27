package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore, tvHighScore, tvWrongAnswers;
    private Button btnRetry;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        tvWrongAnswers = findViewById(R.id.tvWrongAnswers);
        btnRetry = findViewById(R.id.btnRetry);
        sharedPreferences = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        ArrayList<String> wrongQuestions = getIntent().getStringArrayListExtra("WRONG_QUESTIONS");
        ArrayList<String> correctAnswers = getIntent().getStringArrayListExtra("CORRECT_ANSWERS");

        // Display score
        tvScore.setText("Your Score: " + score + " / " + total);

        // Check and update high score
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);
        if (score > highScore) {
            sharedPreferences.edit().putInt("HIGH_SCORE", score).apply();
            tvHighScore.setText("🏆 New High Score: " + score);
        } else {
            tvHighScore.setText("🏆 High Score: " + highScore);
        }

        // Show wrong questions and their correct answers
        StringBuilder wrongAnswersText = new StringBuilder();
        if (wrongQuestions != null && correctAnswers != null && !wrongQuestions.isEmpty()) {
            for (int i = 0; i < wrongQuestions.size(); i++) {
                wrongAnswersText.append("❌ ")
                        .append(wrongQuestions.get(i))  // Display wrong question
                        .append("\n✅ Correct Answer: ")
                        .append(correctAnswers.get(i))  // Display correct answer
                        .append("\n\n");
            }
        } else {
            wrongAnswersText.append("🎉 No wrong answers! Well done!");
        }

        tvWrongAnswers.setText(wrongAnswersText.toString());

        // Fix Retry Button
        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
