package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvTimer;
    private Button btnOption1, btnOption2, btnOption3, btnOption4;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer countDownTimer;
    private List<String[]> shuffledQuestions;
    private ArrayList<String> wrongQuestions = new ArrayList<>();
    private ArrayList<String> correctAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        String topic = getIntent().getStringExtra("TOPIC");

        shuffledQuestions = new ArrayList<>();
        Collections.addAll(shuffledQuestions, QuestionAnswer.getQuestionsForTopic(topic));
        Collections.shuffle(shuffledQuestions);

        loadNewQuestion();

        btnOption1.setOnClickListener(v -> processAnswer(btnOption1));
        btnOption2.setOnClickListener(v -> processAnswer(btnOption2));
        btnOption3.setOnClickListener(v -> processAnswer(btnOption3));
        btnOption4.setOnClickListener(v -> processAnswer(btnOption4));
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex == shuffledQuestions.size()) {
            finishQuiz();
            return;
        }

        String[] currentQuestion = shuffledQuestions.get(currentQuestionIndex);
        tvQuestion.setText(currentQuestion[0]);
        btnOption1.setText(currentQuestion[1]);
        btnOption2.setText(currentQuestion[2]);
        btnOption3.setText(currentQuestion[3]);
        btnOption4.setText(currentQuestion[4]);

        startTimer();
    }

    private void startTimer() {
        tvTimer.setText("Time: 15s");
        countDownTimer = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                wrongQuestions.add(shuffledQuestions.get(currentQuestionIndex)[0]);
                correctAnswers.add(shuffledQuestions.get(currentQuestionIndex)[5]);
                currentQuestionIndex++;
                loadNewQuestion();
            }
        }.start();
    }

    private void processAnswer(Button selectedButton) {
        countDownTimer.cancel();
        String chosenAnswer = selectedButton.getText().toString();
        String correctAnswer = shuffledQuestions.get(currentQuestionIndex)[5];

        if (chosenAnswer.equals(correctAnswer)) {
            score++;
        } else {
            wrongQuestions.add(shuffledQuestions.get(currentQuestionIndex)[0]);
            correctAnswers.add(correctAnswer);
        }

        currentQuestionIndex++;
        loadNewQuestion();
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", shuffledQuestions.size());
        intent.putStringArrayListExtra("WRONG_QUESTIONS", wrongQuestions);
        intent.putStringArrayListExtra("CORRECT_ANSWERS", correctAnswers);
        startActivity(intent);
        finish();
    }
}
