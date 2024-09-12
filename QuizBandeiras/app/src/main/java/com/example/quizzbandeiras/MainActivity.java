package com.example.quizzbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText edtNome;
    private Button btnIniciar;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //JAVA - XML
        edtNome = findViewById(R.id.edtNome);
        btnFechar = findViewById(R.id.btnFechar);
        btnIniciar = findViewById(R.id.btnIniciar);


        btnIniciar.setEnabled(false);
        //Desabilita o botão caso o usuário não tenha preenchido o campo "usuário"
        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnIniciar.setEnabled(!s.toString().trim().isEmpty());

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void iniciar(View view){
        String nomeUsuario = edtNome.getText().toString();
        Intent quiz1 = new Intent(MainActivity.this, Quiz1.class);
        //Envia o nome do usuário para a próxima tela
        quiz1.putExtra("nomeUsuario", nomeUsuario);
        //Vai para a próxima tela
        startActivity(quiz1);
    }
    public void fechar (View view){
        finishAffinity();;
    }



}