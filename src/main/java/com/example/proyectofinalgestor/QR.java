package com.example.proyectofinalgestor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR extends AppCompatActivity {

    EditText txtDatos;
    Button btnGenerar;
    ImageView imgQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        txtDatos = findViewById(R.id.txtDatos);
        btnGenerar = findViewById(R.id.btnGenera);
        imgQR = findViewById(R.id.qrCode);


        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("SMSTO:4141148085"+txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750,750);

                    imgQR.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}