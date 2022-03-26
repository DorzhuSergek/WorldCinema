package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.RegistrationApi;
import com.example.worldcinema.network.models.RegisterBody;
import com.example.worldcinema.network.models.RegisterResponse;
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
        AsyncTask.execute(()->{
            service.registrationAccount(setRegistrationBody()).enqueue(new Callback<RegisterBody>() {
                @Override
                public void onResponse(Call<RegisterBody> call, Response<RegisterBody> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Успешно",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpScreen.this,MainActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<RegisterBody> call, Throwable t) {
                    startActivity(new Intent(getApplicationContext(),SignInScreen.class));
                }
            });

        });


    }


    private RegisterBody setRegistrationBody() {
        return new RegisterBody(email.getText().toString(),password.getText().toString(),firstName.getText().toString(),surname.getText().toString());
    }


}