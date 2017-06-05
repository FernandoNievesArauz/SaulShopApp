package com.nieves.fernando.saulshop;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nieves.fernando.saulshop.R;

import java.util.List;

/**
 * Created by usuario on 26/05/2017.
 */

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ProductoViewHolder>{

    List<Producto> catalogoer;

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView producto_nombre_card;
        TextView producto_precio_card;
        ImageView producto_foto;

        ProductoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardview_producto);
            producto_nombre_card = (TextView)itemView.findViewById(R.id.tv_cardview_producto_nombre);
            producto_precio_card = (TextView)itemView.findViewById(R.id.tv_cardview_producto_precio);
            producto_foto = (ImageView)itemView.findViewById(R.id.img_cardview_producto);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    MiAdaptador(List<Producto> catalogoer){
        this.catalogoer = catalogoer;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        ProductoViewHolder pvh = new ProductoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MiAdaptador.ProductoViewHolder holder, int position) {
        holder.producto_nombre_card.setText(catalogoer.get(position).getNombre_producto());
        holder.producto_precio_card.setText(catalogoer.get(position).getPrecio());
        //holder.producto_foto.setImageResource(catalogoer.get(position).getCantidad_stock());
    }

    @Override
    public int getItemCount() {
        return catalogoer.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}