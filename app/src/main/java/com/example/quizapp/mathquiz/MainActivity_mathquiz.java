package com.example.quizapp.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityMainMathquizBinding;
import com.example.quizapp.mathquiz.modalquiz.Question;
import com.example.quizapp.mathquiz.viewmodal.QuizViewModel;

import java.util.List;

public class MainActivity_mathquiz extends AppCompatActivity {

    private ActivityMainMathquizBinding binding;
    private QuizViewModel viewModel;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_mathquiz);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        viewModel.getQuestionListLiveData().observe(this, questions -> {
            if (questions != null && !questions.isEmpty()) {
                questionList = questions;
                displayQuestion(currentQuestionIndex);
            } else {
                Toast.makeText(this, "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });

        // Unified click listener for both buttons
        binding.btnNext.setOnClickListener(v -> checkAnswerAndProceed());
        binding.btnSaveNext.setOnClickListener(v -> checkAnswerAndProceed());

        binding.btnMarkReview.setOnClickListener(v -> {
            Toast.makeText(this, "Marked for review", Toast.LENGTH_SHORT).show();
        });

        binding.btnClear.setOnClickListener(v -> {
            binding.rgOptions.clearCheck();
        });
    }

    private void checkAnswerAndProceed() {
        if (questionList == null || questionList.isEmpty()) return;

        int selectedId = binding.rgOptions.getCheckedRadioButtonId();
        
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString().trim();
            
            Question currentQuestion = questionList.get(currentQuestionIndex);
            String correctValue = currentQuestion.getCorrectOption();

            if (correctValue != null) {
                correctValue = correctValue.trim();
                boolean isCorrect = false;

                // 1. Direct Text Match (e.g., correctValue is "8" and selected text is "8")
                if (selectedAnswer.equalsIgnoreCase(correctValue)) {
                    isCorrect = true;
                } 
                // 2. Index Match (e.g., correctValue is "1" and user selected the first option)
                else {
                    int selectedIndex = -1;
                    if (selectedId == R.id.rb_option1) selectedIndex = 1;
                    else if (selectedId == R.id.rb_option2) selectedIndex = 2;
                    else if (selectedId == R.id.rb_option3) selectedIndex = 3;
                    else if (selectedId == R.id.rb_option4) selectedIndex = 4;

                    if (correctValue.equals(String.valueOf(selectedIndex))) {
                        isCorrect = true;
                    }
                }

                if (isCorrect) {
                    score++;
                    Log.d("QUIZ_DEBUG", "Correct! Current Score: " + score);
                } else {
                    Log.d("QUIZ_DEBUG", "Wrong! Selected: [" + selectedAnswer + "] | Expected: [" + correctValue + "]");
                }
            }
        }

        // Navigation logic
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            showResults();
        }
    }

    private void displayQuestion(int index) {
        if (questionList == null || index >= questionList.size()) return;
        
        Question currentQuestion = questionList.get(index);
        binding.tvQuestionText.setText(currentQuestion.getQuestion());
        binding.rbOption1.setText(currentQuestion.getOption1());
        binding.rbOption2.setText(currentQuestion.getOption2());
        binding.rbOption3.setText(currentQuestion.getOption3());
        binding.rbOption4.setText(currentQuestion.getOption4());
        binding.tvCorrectOptionStatus.setText("Question " + (index + 1) + " of " + questionList.size());
        
        binding.rgOptions.clearCheck();
    }

    private void showResults() {
        Log.d("QUIZ_DEBUG", "Final Score: " + score + "/" + (questionList != null ? questionList.size() : 0));
        Intent intent = new Intent(MainActivity_mathquiz.this, ResultsActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questionList != null ? questionList.size() : 0);
        startActivity(intent);
        finish();
    }
}
