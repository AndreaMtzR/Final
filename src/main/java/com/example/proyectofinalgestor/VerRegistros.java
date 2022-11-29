package com.example.proyectofinalgestor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerRegistros extends AppCompatActivity {
    EditText txtNombre, txtTelefono,txtCorreo_electronico,txtPedido;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    Contactos contacto;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo_electronico = findViewById(R.id.txtCorreo_electronico);
        txtPedido = findViewById(R.id.txtPedido);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuardar.setVisibility(View.INVISIBLE);
        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if (extras ==null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        BdContactos bdContactos = new BdContactos(VerRegistros.this);
        contacto =bdContactos.verContactos(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo_electronico.setText(contacto.getCorreo_electronico());
            txtPedido.setText(contacto.getPedido());
            btnGuardar.setVisibility((View.INVISIBLE));
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo_electronico.setInputType(InputType.TYPE_NULL);
            txtPedido.setInputType(InputType.TYPE_NULL);

        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(VerRegistros.this,EditarRegistros.class);
                intento.putExtra("ID",id);
                startActivity(intento);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(VerRegistros.this);
                builder.setMessage("Eliminar Cliente?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(bdContactos.eliminarContacto(id)){
                            lista();

                        }
                    }
                })
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intento = new Intent(this,MainActivity2.class);
        startActivity(intento);
    }

}