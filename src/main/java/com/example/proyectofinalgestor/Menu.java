package com.example.proyectofinalgestor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                inicioSesion();
                return true;

            case R.id.login:
                login();
                return true;

            case R.id.locacion:
                locacion();
                return true;

            case R.id.codigo:
                codigo();
                return true;

            case R.id.print:
                galeria();
                return true;


            case R.id.galeria:
                galeria();
                return true;







            default:super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }
    private void inicioSesion(){
        Intent intent =new Intent(this,LOGIN.class);
        startActivity(intent);
    }
    private void login(){
        Intent intent =new Intent(this, Ingreso.class);
        startActivity(intent);

    }
    private void locacion(){
        Intent intent =new Intent(this, Ubicacion.class);
        startActivity(intent);

    }
    private void codigo(){
        Intent intent =new Intent(this, Codigos.class);
        startActivity(intent);

    }
    private void galeria() {
        Intent intent = new Intent(this, Galeria.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}