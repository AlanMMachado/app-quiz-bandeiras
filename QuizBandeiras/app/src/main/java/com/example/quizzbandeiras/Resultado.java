package com.example.quizzbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resultado extends AppCompatActivity {

    private int qntRespostasCertas;
    private TextView txtvResultado;
    private TextView txtvNomeUsuario;
    private Button btnReiniciar;
    private Button btnSair;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //JAVA - XML
        txtvResultado = findViewById(R.id.txtvResultado);
        txtvNomeUsuario = findViewById(R.id.txtvNomeUsuario);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnSair = findViewById(R.id.btnSair);

        // Recuperar dados da Intent
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Atualizar os textos na tela
        if(qntRespostasCertas >= 5){
            txtvResultado.setText(qntRespostasCertas + "/10");
            txtvNomeUsuario.setText("😄 Parabéns, " + nomeUsuario + "!");
        }else{
            txtvResultado.setText(qntRespostasCertas + "/10");
            txtvNomeUsuario.setText("😬 Ai não, " + nomeUsuario + "!");
        }

    }

    public void reiniciarQuiz(View view) {
        // Reiniciar o quiz
        Intent reiniciar = new Intent(this, MainActivity.class);
        startActivity(reiniciar);
        finish();
    }

    public void sairDoQuiz(View view) {
        finishAffinity(); // Fecha todas as atividades
    }
}
