package com.example.proyectofinalgestor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectofinalgestor.BDcontacto;
import com.example.proyectofinalgestor.Entidad.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {


    EditText txtNombre, txtTelefono,txtCorreo_electronico,txtPedido;
    Button btnGuardar;
    Boolean correcto = false;
    Contactos contacto;
    FloatingActionButton fabEditar;
    int id=0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo_electronico = findViewById(R.id.txtCorreo_electronico);
        txtPedido= findViewById(R.id.txtPedido);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);

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

        BDcontacto bDcontacto = new BDcontacto(EditarActivity.this);
        contacto = bDcontacto.verContactos(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo_electronico.setText(contacto.getCorreo_electronico());
            txtPedido.setText(contacto.getPedido());

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    correcto = bDcontacto.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo_electronico.getText().toString(), txtPedido.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "Rgistro modificado Correctamente", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "No se pudo mdoificar volver a intentar", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(EditarActivity.this,"LLena los campos", Toast.LENGTH_LONG).show();


                }
            }
        });

    }
    private void verRegistro() {
        Intent intento = new Intent(this, Ver.class);
        intento.putExtra("ID", id);
        startActivity(intento);
    }

}
