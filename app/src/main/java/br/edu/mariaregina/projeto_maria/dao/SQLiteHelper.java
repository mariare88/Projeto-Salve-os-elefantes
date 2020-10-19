package br.edu.mariaregina.projeto_maria.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "elefante.db";

    public static final String TABLE_ELEFANTE = "elefantes";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AFFILIATION = "affiliation";
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_NOTE = "note";

    private Context mContext;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;

        sql = "CREATE TABLE " + TABLE_ELEFANTE + " (";
        sql += COLUMN_NAME + " TEXT NOT NULL, ";
        sql += COLUMN_AFFILIATION + " TEXT NOT NULL, ";
        sql += COLUMN_SPECIES + " TEXT NOT NULL, ";
        sql += COLUMN_SEX + " TEXT NOT NULL, ";
        sql += COLUMN_NOTE + " TEXT NOT NULL, ";
        sql += "PRIMARY KEY (" + COLUMN_NAME + ") );";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}