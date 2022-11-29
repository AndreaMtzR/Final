package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ingreso extends AppCompatActivity {
    EditText usuario, contraseña;
    Button entrar;
    BDlog BD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        usuario=(EditText) findViewById(R.id.usuario1);
        contraseña =(EditText) findViewById(R.id.contraseña1);

        entrar= (Button) findViewById(R.id.entrar);
        BD= new BDlog(this);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usuario.getText().toString();
                String pass= contraseña.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Ingreso.this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = BD.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Ingreso.this, "Inicio con exito", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(Ingreso.this, MainActivity2.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Ingreso.this, "Dato invalido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}