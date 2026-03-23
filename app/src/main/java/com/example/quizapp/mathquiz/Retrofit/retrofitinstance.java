package com.example.quizapp.mathquiz.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitinstance {

    // Using 10.0.2.2 to point to the host's localhost (127.0.0.1) from the Android emulator
    static String baseUrl = "http://192.168.1.6/quiz/";
    
    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
