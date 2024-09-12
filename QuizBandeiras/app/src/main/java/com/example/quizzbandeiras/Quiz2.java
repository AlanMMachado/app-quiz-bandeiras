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

public class Quiz2 extends AppCompatActivity {

    private int qntRespostasCertas = 0;
    private RadioGroup rgpQuiz2;
    private RadioButton rbtQ2Alemanha;
    private RadioButton rbtQ2india;
    private RadioButton rbtQ2Australia;
    private RadioButton rbtQ2Cuba;
    private Button btnQ2Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //JAVA - XML
        rgpQuiz2 = findViewById(R.id.rgpQuiz2);
        rbtQ2Alemanha = findViewById(R.id.rbtQ2Alemanha);
        rbtQ2Australia = findViewById(R.id.rbtQ2Australia);
        rbtQ2Cuba = findViewById(R.id.rbtQ2Cuba);
        rbtQ2india = findViewById(R.id.rbtQ2India);
        btnQ2Proximo = findViewById(R.id.btnQ2Proximo);

        // Recuperar a quantidade de respostas corretas da atividade anterior
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz2.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ2Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view){
        int selecionado = rgpQuiz2.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(selecionado == R.id.rbtQ2Cuba){
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }
        // Criar um Intent para iniciar a próxima atividade
        Intent quiz3 = new Intent(this, Quiz3.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz3.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz3.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz3);
        finish();

    }
}