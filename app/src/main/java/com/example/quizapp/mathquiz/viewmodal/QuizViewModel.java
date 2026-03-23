package com.example.quizapp.mathquiz.viewmodal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.mathquiz.modalquiz.Question;
import com.example.quizapp.mathquiz.repository.quizrepository;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private final quizrepository repository;
    private final LiveData<List<Question>> questionListLiveData;

    public QuizViewModel() {
        this.repository = new quizrepository();
        this.questionListLiveData = repository.getQuestions();
    }

    public LiveData<List<Question>> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
