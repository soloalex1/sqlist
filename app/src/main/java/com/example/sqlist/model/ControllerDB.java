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
        values.put(CreateDB.getTitulo(), titulo);
        values.put(CreateDB.getDesc(), descricao);

        resultado = database.insert(CreateDB.getTabela(), null, values);
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
        String[] fields = { createDB.getID(), createDB.getTitulo() };
        database = createDB.getReadableDatabase();

        // executando a consulta
        cursor = database.query(createDB.getTabela(), fields, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        // encerrando a conexão e retornando
        database.close();
        return cursor;
    }

    public Cursor carregaDadoTarefa(int id){
        Cursor cursor;
        String[] fields = { createDB.getID(), createDB.getTitulo(), createDB.getDesc(), createDB.getStatus() };
        database = createDB.getReadableDatabase();
        cursor = database.query(createDB.getTabela(), fields, (createDB.getID() + "=" + id), null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        database.close();
        return cursor;
    }

    public void alteraTarefa(int id, String novoTitulo, String novaDesc, boolean status){
        ContentValues values = new ContentValues();
        database = createDB.getWritableDatabase();

        int statusAux;

        if(status == true){
            statusAux = 1;
        } else {
            statusAux = 0;
        }

        values.put(createDB.getTitulo(), novoTitulo);
        values.put(createDB.getDesc(), novaDesc);
        values.put(createDB.getStatus(), statusAux);

        database.update(createDB.getTabela(),  values, (createDB.getID() + "=" + id),null);
        database.close();
    }

    public void deletarTarefa(int id){
        database = createDB.getReadableDatabase();
        database.delete(createDB.getTabela(), (createDB.getID() + "=" + id), null);
        database.close();
    }
}