package com.example.worldcinema.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.worldcinema.R;
import com.example.worldcinema.SignInScreen;

public class FourthFragment extends Fragment {
    SharedPreferences sharedPreferences;
    Button exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fourth, container, false);
        exit = view.findViewById(R.id.exit_account);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("token").commit();
                startActivity(new Intent(getContext(),SignInScreen.class));
            }
        });
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}