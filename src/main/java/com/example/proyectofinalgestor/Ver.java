package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.proyectofinalgestor.Entidad.Contactos;
import com.example.proyectofinalgestor.BDcontacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ver extends AppCompatActivity {

    EditText txtNombre, txtTelefono,txtCorreo_electronico,txtPedido;
    Button btnGuardar;
    Contactos contactos;
    FloatingActionButton fabEditar;
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo_electronico = findViewById(R.id.txtCorreo_electronico);
        txtPedido= findViewById(R.id.txtPedido);
        fabEditar.setVisibility(View.INVISIBLE);

        fabEditar= findViewById(R.id.fabEditar);

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

        BDcontacto bDcontacto = new BDcontacto(Ver.this);
        contactos =bDcontacto.verContactos(id);

        if(contactos != null){
            txtNombre.setText(contactos.getNombre());
            txtTelefono.setText(contactos.getTelefono());
            txtCorreo_electronico.setText(contactos.getCorreo_electronico());
            txtPedido.setText(contactos.getPedido());
            btnGuardar.setVisibility((View.INVISIBLE));
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo_electronico.setInputType(InputType.TYPE_NULL);
            txtPedido.setInputType(InputType.TYPE_NULL);


        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(Ver.this,EditarActivity.class);
                intento.putExtra("ID",id);
                startActivity(intento);
            }
        });

    }

}