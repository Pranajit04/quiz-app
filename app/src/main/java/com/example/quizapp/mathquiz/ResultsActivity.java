package com.example.quizapp.mathquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quizapp.MainActivity;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultsBinding;

public class ResultsActivity extends AppCompatActivity {

    private ActivityResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);

        // FIX: Keys must match EXACTLY with MainActivity_mathquiz (uppercase)
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);

        // Update the score display
        binding.tvScore.setText(score + "/" + total);

        binding.btnRetake.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity_mathquiz.class);
            startActivity(intent);
            finish();
        });

        binding.btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
