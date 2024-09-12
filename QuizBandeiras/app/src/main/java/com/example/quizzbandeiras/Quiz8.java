package com.example.quizzbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz8 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz8;
    private RadioButton rbtQ8Venezuela;
    private RadioButton rbtQ8Colombia;
    private RadioButton rbtQ8Equador;
    private RadioButton rbtQ8Peru;
    private Button btnQ8Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz8);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz8 = findViewById(R.id.rgpQuiz8);
        rbtQ8Venezuela = findViewById(R.id.rbtQ8Venezuela);
        rbtQ8Colombia = findViewById(R.id.rbtQ8Colombia);
        rbtQ8Equador = findViewById(R.id.rbtQ8Equador);
        rbtQ8Peru = findViewById(R.id.rbtQ8Peru);
        btnQ8Proximo = findViewById(R.id.btnQ8Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior e o nome do usuário da main
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz8.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ8Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz8.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecionado == R.id.rbtQ8Venezuela) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade (ou terminar o quiz)
        Intent quiz9 = new Intent(this, Quiz9.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz9.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz9.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz9);
        finish();
    }
}
