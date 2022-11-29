package com.example.proyectofinalgestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarRegistros extends AppCompatActivity {

    EditText txtNombre, txtTelefono,txtCorreo_electronico,txtPedido;
    Button btnGuardar;
    boolean correcto = false;
    FloatingActionButton fabEditar,fabEliminar;
    Contactos contacto;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);

        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo_electronico = findViewById(R.id.txtCorreo_electronico);
        txtPedido= findViewById(R.id.txtPedido);
        btnGuardar= findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

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
         final BdContactos bdContactos = new BdContactos(EditarRegistros.this);
        contacto =bdContactos.verContactos(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo_electronico.setText(contacto.getCorreo_electronico());
            txtPedido.setText(contacto.getPedido());

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    correcto = bdContactos.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo_electronico.getText().toString(), txtPedido.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarRegistros.this, "Rgistro modificado Correctamente", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarRegistros.this, "No se pudo mdoificar volver a intentar", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(EditarRegistros.this,"LLena los campos", Toast.LENGTH_LONG).show();


                }
            }
        });
    }
    private void verRegistro() {
        Intent intento = new Intent(this, VerRegistros.class);
        intento.putExtra("ID", id);
        startActivity(intento);
    }
}
