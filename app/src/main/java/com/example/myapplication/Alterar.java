package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Alterar extends Activity {

    EditText descricao;
    EditText valor;
    EditText data;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_layout);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        descricao = (EditText)findViewById(R.id.editText4);
        valor = (EditText)findViewById(R.id.editText5);
        data = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DESCRICAO)));
        valor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.VALOR)));
        data.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DATA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        descricao.getText().toString(),valor.getText().toString(),
                        data.getText().toString());
                Intent intent = new Intent(Alterar.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(Alterar.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
