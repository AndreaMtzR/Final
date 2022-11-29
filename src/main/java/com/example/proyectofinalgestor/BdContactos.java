package com.example.proyectofinalgestor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BdContactos extends BdHelper {
    Context context;

    public BdContactos(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertaContacto(String nombre, String telefono, String correo_electronico, String pedido) {

        long id = 0;

        try {

            BdHelper bdHelper = new BdHelper(context);
            SQLiteDatabase bd = bdHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            values.put("pedido", pedido);

            id = bd.insert(TABLE_CONTACTOS, null, values);


        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Contactos> mostrarContactos() {

        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

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
                contacto.setPedido(cursorContactos.getString(4));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;

    }
    public Contactos verContactos(int id) {

        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();


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
        boolean correcto = false;

        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();
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
    public boolean eliminarContacto(int id){
        boolean correcto= false;
        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        try{
            bd.execSQL("delete from "+ TABLE_CONTACTOS + "WHERE id ='"+ id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            bd.close();
        }
        return correcto;
    }
}
