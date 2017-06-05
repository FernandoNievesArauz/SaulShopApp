package com.nieves.fernando.saulshop;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by usuario on 05/06/2017.
 */

public class AdaptadorCliente extends RecyclerView.Adapter<AdaptadorCliente.ClienteViewHolder>{

    List<Cliente> lista_clientes;

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        CardView cv2;
        TextView cliente_nombre_card;
        ImageView cliente_foto;

        ClienteViewHolder(View itemView) {
            super(itemView);
            cv2 = (CardView)itemView.findViewById(R.id.cardview_cliente);
            cliente_nombre_card = (TextView)itemView.findViewById(R.id.tv_cardview_cliente_nombre);
            cliente_foto = (ImageView)itemView.findViewById(R.id.img_cardview_cliente);
        }
    }

    AdaptadorCliente(List<Cliente> lista_clientes){
        this.lista_clientes = lista_clientes;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        ClienteViewHolder pvh = new ClienteViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AdaptadorCliente.ClienteViewHolder holder, int position) {
        holder.cliente_nombre_card.setText(lista_clientes.get(position).getNombre());
        //holder.producto_foto.setImageResource(lista_clientes.get(position).getCantidad_stock());
    }

    @Override
    public int getItemCount() {
        return lista_clientes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
