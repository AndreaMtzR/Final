package com.example.proyectofinalgestor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactoAdapter extends RecyclerView.Adapter<ListaContactoAdapter.ContactoViewHolder> {
    ArrayList<Contactos> listaContactos;
    ArrayList<Contactos> listaOriginal;

    public ListaContactoAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos=listaContactos;
        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(listaContactos);
    }
    @NonNull
    @Override
    public ListaContactoAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto,null,false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactoAdapter.ContactoViewHolder holder, int position) {

        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewCorreo_electronico.setText(listaContactos.get(position).getCorreo_electronico());
        holder.viewPedido.setText(listaContactos.get(position).getPedido());
    }
    public void filtrado(final  String txtBuscar){
        int longitud= txtBuscar.length();
        if(longitud==0){
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> coll = listaContactos.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coll);
            } else {
                for(Contactos c: listaOriginal){
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaContactos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
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
            viewCorreo_electronico=itemView.findViewById(R.id.viewCorreo_electronico);
            viewPedido =itemView.findViewById(R.id.viewPedido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intento = new Intent(context, VerRegistros.class);
                    intento.putExtra("ID",listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intento);
                }
            });
        }
    }
}
