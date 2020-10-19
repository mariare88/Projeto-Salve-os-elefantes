package br.edu.mariaregina.projeto_maria.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.mariaregina.projeto_maria.model.Elefante;

public class ElefanteDao {

    private SQLiteDatabase mSqLiteDatabase;
    private SQLiteHelper mHelper;

    public ElefanteDao(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public void adicionar(Elefante elefante) throws NullPointerException {
        if (elefante == null) {
            throw new NullPointerException();
        }

        ContentValues valores = new ContentValues();
        valores.put(SQLiteHelper.COLUMN_NAME, elefante.getName());
        valores.put(SQLiteHelper.COLUMN_AFFILIATION, elefante.getAffiliation());
        valores.put(SQLiteHelper.COLUMN_SPECIES, elefante.getSpecies());
        valores.put(SQLiteHelper.COLUMN_SEX, elefante.getSex());
        valores.put(SQLiteHelper.COLUMN_NOTE, elefante.getNote());

        mSqLiteDatabase = mHelper.getWritableDatabase();

        mSqLiteDatabase.insert(SQLiteHelper.TABLE_ELEFANTE, null, valores);

        mSqLiteDatabase.close();
    }

    public List<Elefante> recuperaTodos(){
        List<Elefante> elefantesList;
        Elefante elefante;
        Cursor mCursor;
        elefantesList = new ArrayList<>();

        String colunas[] = new String[]{
                SQLiteHelper.COLUMN_NAME,
                SQLiteHelper.COLUMN_AFFILIATION,
                SQLiteHelper.COLUMN_SPECIES,
                SQLiteHelper.COLUMN_SEX,
                SQLiteHelper.COLUMN_NOTE
        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
                SQLiteHelper.TABLE_ELEFANTE,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NAME
        );

        while (mCursor.moveToNext()){
            elefante = new Elefante(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2),
                    mCursor.getString(3),
                    mCursor.getString(4)
            );
            elefantesList.add(elefante);
        }

        mCursor.close();
        mSqLiteDatabase.close();
        return elefantesList;
    }
}
