package com.example.sqlist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sqlist.model.ControllerDB;
import com.example.sqlist.model.CreateDB;

public class EditActivity extends Activity {

    EditText nomeTarefa;
    EditText descricao;
    CheckBox status;
    Button btnAlterar;
    Button btnDeletar;
    Cursor cursor;
    ControllerDB crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new ControllerDB(getBaseContext());

        nomeTarefa = findViewById(R.id.inputTitle);
        descricao = findViewById(R.id.inputDesc);
        status = findViewById(R.id.statCheckbox);

        btnAlterar = findViewById(R.id.btnAlterarTarefa);
        btnDeletar = findViewById(R.id.btnDeletarTarefa);

        cursor = crud.carregaDadoTarefa(Integer.parseInt(codigo));
        nomeTarefa.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.getTitulo())));
        descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.getDesc())));
        status.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow(CreateDB.getStatus())) > 0);

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraTarefa(Integer.parseInt(codigo), nomeTarefa.getText().toString(), descricao.getText().toString(), status.isChecked());
                Intent intent = new Intent(EditActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                crud.deletarTarefa(Integer.parseInt(codigo));
                Intent i = new Intent(EditActivity.this, SearchActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}