package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.network.PosterApi;
import com.example.myapplication.network.models.LoginBody;
import com.example.myapplication.network.models.LoginResponse;
import com.example.myapplication.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInScreen extends AppCompatActivity {
    Button button;
    ApiService service = PosterApi.getInstance().getService();
    EditText email, password;
    SharedPreferences sharedPreferences;
    SharedPreferences edit;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.button);
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        edit = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        button.setOnClickListener(view -> {
            doLogin();
        });
        if (token != "") {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    private void doLogin() {
        AsyncTask.execute(() -> {
            service.registrationAccount(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        sharedPreferences.edit().putString("token", response.body().getToken()).apply();
                    } else {
                        Toast.makeText(SignInScreen.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(SignInScreen.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private LoginBody getLoginData() {
        return new LoginBody(email.getText().toString(), password.getText().toString());
    }
}