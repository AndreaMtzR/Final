package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Galeria extends AppCompatActivity {

    Button btnCmamara;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        btnCmamara = findViewById(R.id.btnCamara);
        imageView2= findViewById(R.id.imageView2);

        btnCmamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }
    private void abrirCamara(){
        Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intento.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intento,1);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imageView2.setImageBitmap(imgBitmap);
        }
    }
}