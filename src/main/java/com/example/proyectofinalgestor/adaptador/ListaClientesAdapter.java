package com.example.proyectofinalgestor.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalgestor.Entidad.Contactos;

import com.example.proyectofinalgestor.R;
import com.example.proyectofinalgestor.Ver;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ContactoViewHolder> {
    ArrayList<Contactos>listaContactos;

    public ListaClientesAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos=listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clientes,null,false);
       return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {

        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewCorreo_electronico.setText(listaContactos.get(position).getCorreo_electronico());
        holder.viewPedido.setText(listaContactos.get(position).getPedido());
    }

    @Override
    public int getItemCount() {

        return listaContactos.size();
    }


    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono,viewCorreo_electronico,viewPedido;



        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre=itemView.findViewById(R.id.viewNombre);
            viewTelefono=itemView.findViewById(R.id.viewTelefono);
            viewCorreo_electronico=itemView.findViewById(R.id.viewcorreo_electronico);
            viewPedido =itemView.findViewById(R.id.viewPedido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intento = new Intent(context, Ver.class);
                    intento.putExtra("ID",listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intento);
                }
            });
        }
    }
}
