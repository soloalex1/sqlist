package com.example.sqlist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final Cursor cursor = crud.carregarTarefas();

        String[] fieldNames = new String[] { CreateDB.getID(), CreateDB.getTitulo(), CreateDB.getStatus() };
        int[] idViews = new int[] { R.id.nomeTarefa, R.id.statusTarefa };
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_list, cursor, fieldNames, idViews, 0);
        
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
}
