package com.example.sqlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlist.model.ControllerDB;

public class InsertActivity extends Activity implements View.OnClickListener {

    EditText tituloTarefa;
    EditText descTarefa;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // Views
        tituloTarefa = findViewById(R.id.inputTitle);
        descTarefa = findViewById(R.id.inputDesc);

        btnInsert = findViewById(R.id.btnAlterarTarefa);
        btnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        String result;

        // inicializando o controller do banco
        ControllerDB crud = new ControllerDB(getBaseContext());

        // recuperando os valores de input
        String title = tituloTarefa.getText().toString();
        String desc = descTarefa.getText().toString();

        // efetua a transação SQL
        result = crud.inserirTarefa(title, desc);

        // exibe o feedback por um Toast
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        // vai pra lista de tarefas
        Intent i = new Intent(InsertActivity.this, SearchActivity.class);
        startActivity(i);
    }
}
