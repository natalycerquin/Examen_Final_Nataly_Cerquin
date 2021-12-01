package com.example.final_nataly_cerquin.model.servicio;

import com.example.final_nataly_cerquin.model.sucursalC;
import com.example.final_nataly_cerquin.model.sucursalClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface service {
    //https://upn.lumenes.tk/N00019639/accounts/
    @POST("N00019639/accounts/")
    Call<Void> postCrearSucursal(@Body sucursalClass sucursalClass);

    @GET("N00019639/accounts")
    Call<List<sucursalC>> getListaSucursales();
}
