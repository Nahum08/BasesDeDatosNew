package com.hpblue.basesdedatos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DaoContacto {
SQLiteDatabase _sqLiteDatabase;

    public DaoContacto(Context ctx){
        MiDB miDB =  new MiDB(ctx);
                _sqLiteDatabase = miDB.getWritableDatabase();
    }
    public long insert(Contacto contacto){
        ContentValues cv =
                new ContentValues();
        cv.put(MiDB.COLUMNS_CONTACTOS[1],contacto.getUsuario());
        cv.put(MiDB.COLUMNS_CONTACTOS[2],contacto.getEmail());
        cv.put(MiDB.COLUMNS_CONTACTOS[3],contacto.getTelefono());
        cv.put(MiDB.COLUMNS_CONTACTOS[4],contacto.getFecha_nacimiento());
        return _sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,null,cv);
    }

    public List<Contacto> getAll(){
        List<Contacto> lst = null;
        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
               MiDB.COLUMNS_CONTACTOS,null,null,null,null,null);
       if (c.moveToFirst()){
           lst = new ArrayList<Contacto>();
           do {
               Contacto contacto =  new Contacto(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
               lst.add(contacto);
           }while (c.moveToNext());
       }
       return lst;
    }


        public  int delete(String ID)
        {
            String where="_id=?";
            int numberOFEntriesDeleted= _sqLiteDatabase.delete(MiDB.TABLE_NAME_CONTACTOS, where, new String[]{ID}) ;
            return numberOFEntriesDeleted;
        }


    public Cursor getAllCursor(){

        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_CONTACTOS,null,null,null,null,null);
        return c;
    }
}
