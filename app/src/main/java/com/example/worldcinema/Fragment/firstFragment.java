package com.example.worldcinema.Fragment;

import android.app.slice.Slice;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldcinema.R;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class firstFragment extends Fragment {
    String urlImage = "https://www.kinopoisk.ru/picture/";
    String poster = "";

    private HttpURLConnection connection = null;
    private String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    data = GetDownloadData();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }

//    private void Parsing(String jsonData) {
//        try {
//            Object object = new JSONParser().parse(jsonData);
//            org.json.simple.JSONObject jsonObject = (JSONObject) object;
//            org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) jsonObject.get("poster");
//            System.out.println(jsonObject);
//        } catch (Exception e) {
//
//        }
//    }

    private String GetDownloadData() {
        StringBuilder result = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL("http://cinema.areas.su/movies/cover").openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    result.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (connection != null) {
            connection.disconnect();
        }
        return result.toString();
    }

}