package com.example.worldcinema.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldcinema.ChatListScreen;
import com.example.worldcinema.R;
import com.example.worldcinema.SignInScreen;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.models.ProfileResponse;
import com.example.worldcinema.network.service.ApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourthFragment extends Fragment {

    //создаем переменные
    SharedPreferences sharedPreferences;
    Button exit;
    TextView nameUser, emailUser;
    ImageView imageUser;
    private String token;
    private SharedPreferences.Editor editor;
    ApiService service = ApiHandler.getInstance().getService();
    LinearLayout discussion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        //иницилизируем переменные
        exit = view.findViewById(R.id.exit_account);
        nameUser = view.findViewById(R.id.nameUser);
        emailUser = view.findViewById(R.id.email);
        imageUser = view.findViewById(R.id.imageUser);

        //сохраняем в локальное хранилище токен авторизации
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        editor = getContext().getSharedPreferences("token", Context.MODE_PRIVATE).edit();

        //это кнопка выхода из аккаунта, также очищает токен и данные о пользователе
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove("token").commit();
                sharedPreferences.edit().remove("firstName").commit();
                sharedPreferences.edit().remove("lastName").commit();
                startActivity(new Intent(getContext(), SignInScreen.class));
            }
        });
        //вызывает метод для получение данных о пользователе
        getUserInfo();
        //переход на другой экран
        discussion=view.findViewById(R.id.discussion);
        discussion.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), ChatListScreen.class));

        });


        return view;
    }

    private void getUserInfo() {
        AsyncTask.execute(() -> {
            service.getData(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    //onResponse вызывается всегда
                    //проверяем успешен ли запрос
                    if(response.isSuccessful()){
                        emailUser.setText(response.body().get(0).getEmail());
                        nameUser.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                        editor.putString("firstName", response.body().get(0).getFirstName()).apply();
                        editor.putString("lastName", response.body().get(0).getLastName()).apply();
                        String firstName = sharedPreferences.getString("firstName","");
                        Picasso.with(getContext())
                                .load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar())
                                .into(imageUser);
                    }

                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    // в блоке onFailure обрабатываются ошибки, которые не связаны с сервером бэкэнда
                    // например если на устройстве нет доступа в Интернет
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

}