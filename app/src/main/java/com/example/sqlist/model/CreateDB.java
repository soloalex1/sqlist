package com.example.sqlist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sqlist.db";
    private static final String TABELA = "tarefas";
    private static final String ID = "_id";
    private static final String TITULO = "titulo";
    private static final String DESCRICAO = "descricao";
    private static final String STATUS = "status";
    private static final int VERSAO = 1;

    private static final String createQuery =
            "CREATE TABLE " + TABELA + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITULO + " TEXT, " + DESCRICAO + " TEXT, " + STATUS + " INTEGER DEFAULT 0 );";

    public CreateDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    // Getters
    public static String getTabela() {
        return TABELA;
    }

    public static String getID() {
        return ID;
    }

    public static String getTitulo() {
        return TITULO;
    }

    public static String getDesc() {
        return DESCRICAO;
    }

    public static String getStatus() {
        return STATUS;
    }
}
