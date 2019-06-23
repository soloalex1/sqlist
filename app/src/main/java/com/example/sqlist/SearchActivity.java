package com.example.sqlist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.sqlist.model.ControllerDB;
import com.example.sqlist.model.CreateDB;

public class SearchActivity extends Activity {

    private ListView listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);        

        ControllerDB crud = new ControllerDB(getBaseContext());
        Cursor cursor = crud.carregarTarefas();

        String[] fieldNames = new String[] { CreateDB.getTitulo(), CreateDB.getStatus() };
        int[] idViews = new int[] { R.id.nomeTarefa, R.id.statusTarefa };
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_list, cursor, fieldNames, idViews, 0);
        
        listaTarefas = findViewById(R.id.listaTarefas);
        listaTarefas.setAdapter(adapter); 
    }
}
