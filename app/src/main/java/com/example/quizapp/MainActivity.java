package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.mathquiz.MainActivity_mathquiz;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCardView mathsCard = findViewById(R.id.card_maths);
        
        mathsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity_mathquiz.class);
            startActivity(intent);
        });
        
        // Other cards are disabled in XML (alpha and clickable=false)
    }
}
