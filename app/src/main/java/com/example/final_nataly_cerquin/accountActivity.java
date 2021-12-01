package com.example.final_nataly_cerquin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_nataly_cerquin.model.servicio.service;
import com.example.final_nataly_cerquin.model.sucursalClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class accountActivity extends AppCompatActivity {

    EditText nombre, monto, fecha_creacion, sucursal_1, sucursal_2, sucursal_3;
    ImageView imagen;
    private static final int PICK_IMAGE = 11;
    String imagenString;
    Uri imageUri;
    service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nombre = findViewById(R.id.nombre);
        monto = findViewById(R.id.monto);
        fecha_creacion = findViewById(R.id.fecha_creacion);
        sucursal_1 = findViewById(R.id.sucursal_1);
        sucursal_2 = findViewById(R.id.sucursal_2);
        sucursal_3 = findViewById(R.id.sucursal_3);
        imagen = findViewById(R.id.imagen);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(service.class);

        checkPermisos();
        findViewById(R.id.registrar).setOnClickListener(v -> registrar());

        imagen.setOnClickListener(v -> cargarImagen());

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    private void registrar() {
        if (imagenString == null) {
            Toast.makeText(this, "Seleccionar imagen", Toast.LENGTH_LONG).show();
            return;
        }

        String get_nombre = nombre.getText().toString().trim();
        String get_monto = monto.getText().toString().trim();
        String get_fecha_creacion = fecha_creacion.getText().toString().trim();
        String get_sucursal_1 = sucursal_1.getText().toString().trim();
        String get_sucursal_2 = sucursal_2.getText().toString().trim();
        String get_sucursal_3 = sucursal_3.getText().toString().trim();

        //sucursalClass(String nombre, String fecha_creacion, String sucursal_1, String sucursal_2, String sucursal_3, String imagen, float monto)
        //Float.parseFloat(lati)
        sucursalClass sucursalClass = new sucursalClass();
        sucursalClass.setNombre(get_nombre);
        sucursalClass.setMonto(Float.parseFloat(get_monto));
        sucursalClass.setFecha_creacion(get_fecha_creacion);
        sucursalClass.setSucursal_1(get_sucursal_1);
        sucursalClass.setSucursal_2(get_sucursal_2);
        sucursalClass.setSucursal_3(get_sucursal_3);
        sucursalClass.setImagen(imagenString);
//https://upn.lumenes.tk/N00019639/accounts/

        Call<Void> surcur = service.postCrearSucursal(sucursalClass);
        surcur.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                String respuesta = String.valueOf(response.code());
                if (respuesta.equals("200")){
                    Toast.makeText(getApplicationContext(),"Sucursal registrada",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(getApplicationContext(),"Sucursal no registrada",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imagen.setImageURI(imageUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] image = outputStream.toByteArray();
                String encodedString = Base64.encodeToString(image, Base64.DEFAULT);
                imagenString = encodedString;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void checkPermisos() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(accountActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }
    }
}