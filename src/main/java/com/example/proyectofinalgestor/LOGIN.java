package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LOGIN extends AppCompatActivity {
    EditText usuario, contraseña,contraseña2;
    Button registro, iniciarsesion;
    BDlog BD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        contraseña2 = findViewById(R.id.contraseña2);

        registro = findViewById(R.id.registro);
        iniciarsesion = findViewById(R.id.iniciarsesion);
        BD = new BDlog(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usuario.getText().toString();
                String pass = contraseña.getText().toString();
                String pass2 = contraseña2.getText().toString();

                if (user.equals("") || pass.equals("") || pass2.equals(""))
                    Toast.makeText(LOGIN.this, "ingresa datos en todos los campos", Toast.LENGTH_LONG).show();

                else {
                    if (pass.equals(pass2)) {
                        Boolean checkuser = BD.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = BD.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(LOGIN.this, "Registro con exito", Toast.LENGTH_LONG).show();

                                Intent intento = new Intent(getApplicationContext(), Home.class);
                                startActivity(intento);

                            } else {
                                Toast.makeText(LOGIN.this, "Fallo de registro", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(LOGIN.this, "Usuario existente", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(LOGIN.this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(),INICIO.class);
                startActivity(intento);

            }
        });
    }
}