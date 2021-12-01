package com.example.final_nataly_cerquin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.confirm_button).setOnClickListener(v -> {
            startActivity(new Intent(this,MapsActivity.class));
        });
    }
}