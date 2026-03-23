package com.example.quizapp.mathquiz.Retrofit;
import com.example.quizapp.mathquiz.modalquiz.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface questionapi {

    @GET("myquizapi.php") 
    Call<List<Question>> getQuestionList();

}
