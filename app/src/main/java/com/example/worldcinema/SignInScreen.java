package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    //создаем переменные
    Button logIn;
    EditText editEmail, editPassword;
    String token;
    SharedPreferences sharedPreferences;
    SharedPreferences edit;
    Button reg;
    ApiService service = ApiHandler.getInstance().getService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        //иницилизируем переменные
        logIn = findViewById(R.id.LogIn);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        reg = findViewById(R.id.SignIn);
        //клик по кнопке
        reg.setOnClickListener(view -> {
            startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
        });

        //сохраняем токен
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        edit = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        //проверка на авторизию
        //проверяем пользователь был авторизиван или нет
        if (token != "") {
            startActivity(new Intent(SignInScreen.this, MainActivity.class));
            finish();
        }
    }
        //метод для регистрации
    private void doLogin() {
        AsyncTask.execute(() -> {
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        //если все успешно переходим на главный экрна
                        Intent intent = new Intent(SignInScreen.this, MainActivity.class);
                        startActivity(intent);
                        //Записывает токен в локальное хранилище
                        sharedPreferences.edit().putString("token", response.body().getToken()).apply();

                    } else if (response.code() == 400) {
                        //если ошибка выводим сообщение
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