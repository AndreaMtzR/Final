package com.example.proyectofinalgestor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdHelper extends SQLiteOpenHelper {

    private static final String Contactos="clientes.bd";
    public static final String TABLE_CONTACTOS ="t_contactos";

    public BdHelper(@Nullable Context context) {

        super(context,Contactos, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CONTACTOS+ "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "telefono INT NOT NULL,"+
                "correo_electronico TEXT," +
                "pedido TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
