package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Clientes extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo_electronico, txtPedido;
    Button btnGuardar,btnlimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo_electronico = findViewById(R.id.txtCorreo_electronico);
        txtPedido= findViewById(R.id.txtPedido);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnlimpiar = findViewById(R.id.btnLimpar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    BDcontacto bdcontacto = new BDcontacto(Clientes.this);
                    long id = bdcontacto.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo_electronico.getText().toString(), txtPedido.getText().toString());

                    if (id > 0) {
                        Toast.makeText(Clientes.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(Clientes.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Clientes.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }

     });
    } private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo_electronico.setText("");
        txtPedido.setText("");
    }
}