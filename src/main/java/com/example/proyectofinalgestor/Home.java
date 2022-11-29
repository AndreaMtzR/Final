package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalgestor.adaptador.ListaClientesAdapter;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.proyectofinalgestor.Entidad.Contactos;
import com.example.proyectofinalgestor.BDcontacto;
import com.example.proyectofinalgestor.BDcliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listaContactos = findViewById(R.id.listaclientes);

        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        BDcontacto bdcontacto = new BDcontacto(Home.this);

        listaArrayContactos = new ArrayList<>();

        ListaClientesAdapter adapter= new ListaClientesAdapter(bdcontacto.mostrarContactos());
        listaContactos.setAdapter(adapter);



    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void nuevoRegistro (){
        Intent intent = new Intent(this,Clientes.class);
        startActivity(intent);
    }



}