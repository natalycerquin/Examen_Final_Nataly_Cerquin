package com.example.final_nataly_cerquin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_nataly_cerquin.R;
import com.example.final_nataly_cerquin.detalleActivity;
import com.example.final_nataly_cerquin.model.sucursalC;
import com.example.final_nataly_cerquin.model.sucursalClass;
import com.squareup.picasso.Picasso;

import java.util.List;

public class sucursalAdpater extends RecyclerView.Adapter<sucursalAdpater.viewHolderSucursal> {

    List<sucursalC> classList;
    Context mContext;

    public sucursalAdpater(List<sucursalC> classList, Context mContext) {
        this.classList = classList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderSucursal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderSucursal(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sucursales_list,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderSucursal holder, int position) {
        sucursalC sucursalCla = classList.get(position);

        if (sucursalCla != null){
            String url = "https://upn.lumenes.tk" + sucursalCla.getImagen_url();
            Log.e("Image",url);
            Picasso.get().load(url).into(holder.image);
            holder.nombre.setText(sucursalCla.getNombre());
        }

        holder.id_car.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, detalleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("sucur",sucursalCla);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    static class viewHolderSucursal extends RecyclerView.ViewHolder {

        ImageView image;
        TextView nombre;
        CardView id_car;

        public viewHolderSucursal(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_sucursal);
            nombre = itemView.findViewById(R.id.text_nmbre);
            id_car = itemView.findViewById(R.id.id_car);
        }
    }
}
