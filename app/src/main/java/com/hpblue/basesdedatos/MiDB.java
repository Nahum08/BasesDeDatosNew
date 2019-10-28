package com.hpblue.basesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MiDB extends SQLiteOpenHelper {

    public MiDB(@Nullable Context context) {
        super(context, "DBfile",null,1);
    }

    private final String SCRIPT_TABLE_CONTACTOS="create table contactos(_id integer primary key autoincrement,"+
            "usuario text not null,"+
            "email text not null,"+
            "telefono text not null,"+
            "fecha_nacimiento text not null);";

    public static final String[] COLUMNS_CONTACTOS = {"_id","usuario","email","telefono","fecha_nacimiento"};
    public static final String TABLE_NAME_CONTACTOS = "contactos";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
