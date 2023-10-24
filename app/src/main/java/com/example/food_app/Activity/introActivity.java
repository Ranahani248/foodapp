package com.example.food_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.example.food_app.R;


public class introActivity extends AppCompatActivity {
    private AppCompatButton start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(v -> {
            Intent intent = new Intent(introActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}