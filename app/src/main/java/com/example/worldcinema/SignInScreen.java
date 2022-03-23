package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInScreen extends AppCompatActivity {
    Button logIn;

    private static final String MY_SETTINGS = "my_settings";

    EditText editEmail, editPassword;

    ApiService service = ApiHandler.getInstance().getService();
    SharedPreferences mSettings;
    Boolean is_logged;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        logIn = findViewById(R.id.LogIn);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
//        if(mSettings.getBoolean("is_logged",false)){
//            startActivity(new Intent(SignInScreen.this,MainActivity.class));
//            finish();
//        }
    }

    private void doLogin() {

        AsyncTask.execute(() -> {
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        token = response.body().getToken();
//                        mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = mSettings.edit();
//                        editor.putBoolean("is_logged", true).apply();
                        System.out.println(token);
                        Intent intent = new Intent(SignInScreen.this, MainActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 400) {

                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serverErrorMessage, Toast.LENGTH_SHORT).show();
                        System.out.println(serverErrorMessage);
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка! Попробуйте позже", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });
    }

    private LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }

}