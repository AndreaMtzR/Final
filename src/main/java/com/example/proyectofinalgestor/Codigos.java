package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Codigos extends AppCompatActivity {

    Button btnlector, btnQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigos);
        btnlector = findViewById(R.id.btnCodigo);
        btnQR = findViewById(R.id.btnLector);

        btnlector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(Codigos.this, Lector.class);
                startActivity(intento);

            }
        });

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Codigos.this,QR.class);
                startActivity(intent);
            }
        });
    }
}