package com.example.final_nataly_cerquin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.final_nataly_cerquin.adapter.sucursalAdpater;
import com.example.final_nataly_cerquin.model.servicio.service;
import com.example.final_nataly_cerquin.model.sucursalC;
import com.example.final_nataly_cerquin.model.sucursalClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.confirm_button).setOnClickListener(v -> {
            startActivity(new Intent(this,accountActivity.class));
        });

        recyclerView = findViewById(R.id.lista_sucursales);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         service = retrofit.create(service.class);

        getList();
       findViewById(R.id.sincronizar).setOnClickListener(v -> {
            List<sucursalC> classList = new ArrayList<>();
            sucursalAdpater adapter = new sucursalAdpater(classList,getApplicationContext());
            recyclerView.setAdapter(adapter);
            getList();
        });
    }

    private void getList() {

        Call<List<sucursalC>> listGetCap = service.getListaSucursales();
        listGetCap.enqueue(new Callback<List<sucursalC>>() {
            @Override
            public void onResponse(Call<List<sucursalC>> call, Response<List<sucursalC>> response) {

                String code = String.valueOf(response.code());
                if (code.equals("200")) {
                    Toast.makeText(getApplicationContext(), " hay sucursales", Toast.LENGTH_SHORT).show();
                    List<sucursalC> myList = response.body();
                    sucursalAdpater adapter = new sucursalAdpater(myList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "no hay sucursales", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<sucursalC>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

}