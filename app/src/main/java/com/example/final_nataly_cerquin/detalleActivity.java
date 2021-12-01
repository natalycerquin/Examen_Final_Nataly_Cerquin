package com.example.final_nataly_cerquin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_nataly_cerquin.model.sucursalC;
import com.squareup.picasso.Picasso;

public class detalleActivity extends AppCompatActivity {

    EditText nombre, monto, fecha_creacion;
    TextView sucursal_1, sucursal_2, sucursal_3;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        sucursalC sucursalC = (sucursalC) getIntent().getSerializableExtra("sucur");
        Log.e("ll",sucursalC.getNombre());

        nombre = findViewById(R.id.nombre);
        monto = findViewById(R.id.monto);
        fecha_creacion = findViewById(R.id.fecha_creacion);
        sucursal_1 = findViewById(R.id.sucursal_1);
        sucursal_2 = findViewById(R.id.sucursal_2);
        sucursal_3 = findViewById(R.id.sucursal_3);
        imagen = findViewById(R.id.imagen);


        nombre.setText(sucursalC.getNombre());
        monto.setText(String.valueOf(sucursalC.getMonto()));
        fecha_creacion.setText(sucursalC.getFecha_creacion());
        sucursal_1.setText(sucursalC.getSucursal_1());
        sucursal_2.setText(sucursalC.getSucursal_2());
        sucursal_3.setText(sucursalC.getSucursal_3());

        String url = "https://upn.lumenes.tk" + sucursalC.getImagen_url();
        Picasso.get().load(url).into(imagen);

        sucursal_1.setOnClickListener(v -> {
            Intent intent = new Intent(this,MapsActivity.class);
            intent.putExtra("cordena",sucursalC.getSucursal_1());
            startActivity(intent);
        });
        sucursal_2.setOnClickListener(v -> {
            Intent intent = new Intent(this,MapsActivity.class);
            intent.putExtra("cordena",sucursalC.getSucursal_2());
            startActivity(intent);
        });
        sucursal_3.setOnClickListener(v -> {
            Intent intent = new Intent(this,MapsActivity.class);
            intent.putExtra("cordena",sucursalC.getSucursal_3());
            startActivity(intent);
        });
    }
}