package com.example.sqlist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControllerDB {

    private SQLiteDatabase database;
    private CreateDB createDB;

    public ControllerDB(Context context){
        createDB = new CreateDB(context);
    }

    public String inserirTarefa(String titulo, String descricao){

        ContentValues values;
        long resultado;

        database = createDB.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDB.TITULO, titulo);
        values.put(CreateDB.DESCRICAO, descricao);

        resultado = database.insert(CreateDB.TABELA, null, values);
        database.close();

        if(resultado == -1){
            return "Erro ao cadastrar nova tarefa";
        } else {
            return "Tarefa cadastrada com sucesso!";
        }
    }

    public Cursor carregarTarefas(){
        Cursor cursor;

        // definindo campos a serem carregados e iniciando o banco
        String[] fields = { createDB.ID, createDB.TITULO };
        database = createDB.getReadableDatabase();

        // executando a consulta
        cursor = database.query(createDB.TABELA, fields, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        // encerrando a conexão e retornando
        database.close();
        return cursor;
    }

}
