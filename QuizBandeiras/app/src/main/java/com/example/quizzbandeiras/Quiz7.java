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

public class Quiz7 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz7;
    private RadioButton rbtQ7Argentina;
    private RadioButton rbtQ7Chile;
    private RadioButton rbtQ7Uruguai;
    private RadioButton rbtQ7Peru;
    private Button btnQ7Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz7 = findViewById(R.id.rgpQuiz7);
        rbtQ7Argentina = findViewById(R.id.rbtQ7Argentina);
        rbtQ7Chile = findViewById(R.id.rbtQ7Chile);
        rbtQ7Uruguai = findViewById(R.id.rbtQ7Uruguai);
        rbtQ7Peru = findViewById(R.id.rbtQ7Peru);
        btnQ7Proximo = findViewById(R.id.btnQ7Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz7.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ7Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz7.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecionado == R.id.rbtQ7Argentina) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade (ou terminar o quiz)
        Intent quiz8 = new Intent(this, Quiz8.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz8.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz8.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz8);
        finish();
    }
}
