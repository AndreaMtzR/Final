package com.example.proyectofinalgestor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectofinalgestor.Entidad.Contactos;

import java.util.ArrayList;

public class BDcontacto extends BDcliente {
    Context context;

    public BDcontacto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaContacto(String nombre, String telefono, String Correo_electronico, String pedido) {
        long id = 0;
        try {
            BDcliente bdcliente = new BDcliente(context);
            SQLiteDatabase bd = bdcliente.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("Correo_electronico", Correo_electronico);
            values.put("pedido", pedido);

            id = bd.insert(TABLE_CONTACTOS, null, values);


        } catch (Exception ex) {
            ex.toString();
        }

        return id;


    }

    public ArrayList<Contactos> mostrarContactos() {

        BDcliente bDcliente = new BDcliente(context);
        SQLiteDatabase bd = bDcliente.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = bd.rawQuery("Select * from " + TABLE_CONTACTOS + " ORDER BY nombre ASC", null);
        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;

    }

    public Contactos verContactos(int id) {

        BDcliente bDcliente = new BDcliente(context);
        SQLiteDatabase bd = bDcliente.getWritableDatabase();


        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = bd.rawQuery("Select * from " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));

        }

        cursorContactos.close();

        return contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String Correo_electronico, String pedido) {
        Boolean correcto = false;

        BDcliente bdcliente = new BDcliente(context);
        SQLiteDatabase bd = bdcliente.getWritableDatabase();
        try {

            bd.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', Correo_electronico = '" + Correo_electronico + "', pedido = '" + pedido + "' WHERE id='" + id + "' ");
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            bd.close();

        }

        return correcto;


    }
}
