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

public class Quiz6 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz6;
    private RadioButton rbtQ6CostaRica;
    private RadioButton rbtQ6Honduras;
    private RadioButton rbtQ6Panama;
    private RadioButton rbtQ6Guatemala;
    private Button btnQ6Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz6 = findViewById(R.id.rgpQuiz6);
        rbtQ6CostaRica = findViewById(R.id.rbtQ6CostaRica);
        rbtQ6Honduras = findViewById(R.id.rbtQ6Honduras);
        rbtQ6Panama = findViewById(R.id.rbtQ6Panama);
        rbtQ6Guatemala = findViewById(R.id.rbtQ6Guatemala);
        btnQ6Proximo = findViewById(R.id.btnQ6Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz6.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ6Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz6.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecionado == R.id.rbtQ6CostaRica) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade (ou terminar o quiz)
        Intent quiz7 = new Intent(this, Quiz7.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz7.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz7.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz7);
        finish();
    }
}
