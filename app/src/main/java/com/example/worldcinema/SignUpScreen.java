package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.RegistrationApi;
import com.example.worldcinema.network.models.RegisterAccount;
import com.example.worldcinema.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {
    EditText firstName, surname, email, password;
    ApiService service = RegistrationApi.getInstance().getService();

    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        firstName = findViewById(R.id.personName);
        surname = findViewById(R.id.personSurname);
        email = findViewById(R.id.personEmail);
        password = findViewById(R.id.textPassword);
        registration = findViewById(R.id.button);
        registration.setOnClickListener(view -> {
            registrationAccount();
        });
    }

    private void registrationAccount() {
        AsyncTask.execute(() -> {
            service.registrationAccount(getLoginData()).enqueue(new Callback<RegisterAccount>() {
                @Override
                public void onResponse(Call<RegisterAccount> call, Response<RegisterAccount> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Успешно", Toast.LENGTH_SHORT).show();
                    } else {
                        switch (response.code()) {
                            case 404:
                                String error = ErrorUtils.parseError(response).message();
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                            default:
                                Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка! Попробуйте позже", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegisterAccount> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });
    }
    private RegisterAccount getLoginData() {

        return new RegisterAccount(firstName.getText().toString(),surname.getText().toString(),email.getText().toString(),password.getText().toString());
    }
}