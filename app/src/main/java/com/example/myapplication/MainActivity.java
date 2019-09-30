package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText descricao;
    private EditText valor;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descricao = (EditText) findViewById(R.id.editText);
        valor = (EditText) findViewById((R.id.editText2));
        data = (EditText) findViewById(R.id.editText3);

        Button botao = (Button) findViewById(R.id.button);
        Button listar = (Button) findViewById(R.id.button2);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descricao.getText().toString().trim().equals("")
                    || valor.getText().toString().trim().equals("")
                    || data.getText().toString().trim().equals("")){

                    Toast.makeText(MainActivity.this, "Preencha todos os campos!",
                            Toast.LENGTH_LONG).show();
                } else {
                    BancoController crud = new BancoController(getBaseContext());

                    String tituloString = descricao.getText().toString();
                    String autorString = valor.getText().toString();
                    String editoraString = data.getText().toString();
                    String resultado;

                    resultado = crud.insereDado(tituloString, autorString, editoraString);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                }
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consulta.class);
                startActivity(intent);
            }
        });
    }
}
