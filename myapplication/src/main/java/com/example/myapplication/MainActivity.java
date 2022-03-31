package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    LinearLayout mainBtn;
    private TextView mTextView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainBtn = findViewById(R.id.main);
        mainBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, MainScreenMovies.class));
        });
//        mTextView = binding.text;
    }
}