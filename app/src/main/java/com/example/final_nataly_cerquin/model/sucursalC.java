package com.example.final_nataly_cerquin.model;

import java.io.Serializable;

public class sucursalC implements Serializable {

  String  nombre,fecha_creacion,sucursal_1,sucursal_2,sucursal_3;
  String imagen_url;
  float monto;

    public sucursalC(String nombre, String fecha_creacion, String sucursal_1, String sucursal_2, String sucursal_3, float monto, String imagen_url) {
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.sucursal_1 = sucursal_1;
        this.sucursal_2 = sucursal_2;
        this.sucursal_3 = sucursal_3;
        this.monto = monto;
        this.imagen_url = imagen_url;
    }

    public sucursalC() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getSucursal_1() {
        return sucursal_1;
    }

    public void setSucursal_1(String sucursal_1) {
        this.sucursal_1 = sucursal_1;
    }

    public String getSucursal_2() {
        return sucursal_2;
    }

    public void setSucursal_2(String sucursal_2) {
        this.sucursal_2 = sucursal_2;
    }

    public String getSucursal_3() {
        return sucursal_3;
    }

    public void setSucursal_3(String sucursal_3) {
        this.sucursal_3 = sucursal_3;
    }


    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
}
