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

public class Quiz10 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz10;
    private RadioButton rbtQ10Brasil;
    private RadioButton rbtQ10Argentina;
    private RadioButton rbtQ10Chile;
    private RadioButton rbtQ10Colombia;
    private Button btnQ10Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz10);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz10 = findViewById(R.id.rgpQuiz10);
        rbtQ10Brasil = findViewById(R.id.rbtQ10Brasil);
        rbtQ10Argentina = findViewById(R.id.rbtQ10Argentina);
        rbtQ10Chile = findViewById(R.id.rbtQ10Chile);
        rbtQ10Colombia = findViewById(R.id.rbtQ10Colombia);
        btnQ10Proximo = findViewById(R.id.btnQ10Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz10.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ10Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz10.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecionado == R.id.rbtQ10Brasil) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade (ou terminar o quiz)
        Intent resultado = new Intent(this, Resultado.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        resultado.putExtra("qntRespostasCertas", qntRespostasCertas);
        resultado.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(resultado);
        finish();
    }
}
