package com.example.sqlist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.sqlist.model.ControllerDB;
import com.example.sqlist.model.CreateDB;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity implements View.OnClickListener {

    private ListView listaTarefas;
    public Button btnAddTarefa;
    public List<TextView> titulosTarefa;

//    @Override
//    public void onStart(){
//        super.onStart();
//        ControllerDB crud = new ControllerDB(getBaseContext());
//        Cursor c = crud.carregarTarefas();
//
//        if(c != null) c.moveToFirst();
//
//        for(int i = 0; i < listaTarefas.getCount(); i++){
//            listaTarefas.addView(titulosTarefa.get(i));
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnAddTarefa = findViewById(R.id.btnNovaTarefa);
        btnAddTarefa.setOnClickListener(this);

        titulosTarefa = new ArrayList<>(10);

        ControllerDB crud = new ControllerDB(getBaseContext());
        final Cursor cursor = crud.carregarTarefas();

        String[] fieldNames = new String[] { CreateDB.getID(), CreateDB.getTitulo() };
        int[] idViews = new int[] { android.R.id.text2, android.R.id.text1 };
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.activity_list_item, cursor, fieldNames, idViews, 0);
        
        listaTarefas = findViewById(R.id.listaTarefas);
        listaTarefas.setAdapter(adapter);
        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.getID()));
                Intent i = new Intent(SearchActivity.this, EditActivity.class);
                i.putExtra("codigo", codigo);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v){
        if(v == btnAddTarefa){
            startActivity(new Intent(SearchActivity.this, InsertActivity.class));
        }
    }
}
