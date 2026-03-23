package com.example.quizapp.mathquiz.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.mathquiz.Retrofit.questionapi;
import com.example.quizapp.mathquiz.Retrofit.retrofitinstance;
import com.example.quizapp.mathquiz.modalquiz.Question;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quizrepository {

    private final questionapi api;

    public quizrepository() {
        this.api = retrofitinstance.getRetrofitInstance()
                .create(questionapi.class);
    }

    public LiveData<List<Question>> getQuestions() {
        MutableLiveData<List<Question>> mutableLiveData = new MutableLiveData<>();

        Call<List<Question>> call = api.getQuestionList();
        
        Log.d("QUIZ_REPO", "Request URL: " + call.request().url());

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(@NonNull Call<List<Question>> call, @NonNull Response<List<Question>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Question> questions = response.body();
                    mutableLiveData.setValue(questions);
                    Log.d("QUIZ_REPO", "Questions fetched successfully. Count: " + questions.size());
                } else {
                    mutableLiveData.setValue(null);
                    String errorMsg = "Response error code: " + response.code();
                    try {
                        if (response.errorBody() != null) {
                            errorMsg += " | Error Body: " + response.errorBody().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("QUIZ_REPO", errorMsg);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Question>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
                Log.e("QUIZ_REPO", "Network failure: " + t.getMessage(), t);
            }
        });

        return mutableLiveData;
    }
}
