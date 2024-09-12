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

public class Quiz9 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz9;
    private RadioButton rbtQ9EstadosUnidos;
    private RadioButton rbtQ9Canada;
    private RadioButton rbtQ9México;
    private RadioButton rbtQ9Brasil;
    private Button btnQ9Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz9 = findViewById(R.id.rgpQuiz9);
        rbtQ9EstadosUnidos = findViewById(R.id.rbtQ9EstadosUnidos);
        rbtQ9Canada = findViewById(R.id.rbtQ9Canada);
        rbtQ9México = findViewById(R.id.rbtQ9México);
        rbtQ9Brasil = findViewById(R.id.rbtQ9Brasil);
        btnQ9Proximo = findViewById(R.id.btnQ9Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz9.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ9Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz9.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecionado == R.id.rbtQ9EstadosUnidos) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade (ou terminar o quiz)
        Intent quiz10 = new Intent(this, Quiz10.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz10.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz10.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz10);
        finish();
    }
}
