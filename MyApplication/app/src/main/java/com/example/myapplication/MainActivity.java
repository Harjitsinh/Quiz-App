package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnC = findViewById(R.id.btnC);
        Button btnJava = findViewById(R.id.btnJava);
        Button btnPython = findViewById(R.id.btnPython);

        btnC.setOnClickListener(v -> startQuiz("C"));
        btnJava.setOnClickListener(v -> startQuiz("Java"));
        btnPython.setOnClickListener(v -> startQuiz("Python"));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
