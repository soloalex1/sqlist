package com.example.sqlist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sqlist.model.ControllerDB;
import com.example.sqlist.model.CreateDB;

public class SearchActivity extends Activity {

    private ListView listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaTarefas = findViewById(R.id.listaTarefas);


        ControllerDB crud = new ControllerDB(getBaseContext());
        Cursor cursor = crud.carregarTarefas();

//        String[] fieldNames = new String[] { CreateDB.ID, CreateDB.TITULO };
        
    }
}
