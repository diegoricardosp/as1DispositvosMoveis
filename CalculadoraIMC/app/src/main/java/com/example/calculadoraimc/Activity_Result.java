package com.example.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewResult = findViewById(R.id.textViewResult);
        TextView textViewMessage = findViewById(R.id.textViewMessage);
        ImageView imageViewEmoji = findViewById(R.id.imageViewEmoji);
        Button btnBack = findViewById(R.id.btnBack);

        // pega o resultado do IMC gerado anteriormente
        Intent intent = getIntent();
        double imc = intent.getDoubleExtra("BMI_RESULT", 0.0);

        // exibe o imc e mensagem correspondente
        textViewResult.setText(String.format("Seu IMC: %.2f", imc));

        if (imc < 18.5) {
            textViewMessage.setText("Seu IMC indica que você está abaixo do peso.");
            imageViewEmoji.setImageResource(R.drawable.emoji_sad);
        } else if (imc < 24.9) {
            textViewMessage.setText("Seu IMC indica que seu peso está saudável. Parabéns!");
            imageViewEmoji.setImageResource(R.drawable.emoji_happy);
        } else if (imc < 29.9) {
            textViewMessage.setText("Seu IMC indica que você está acima do peso.");
            imageViewEmoji.setImageResource(R.drawable.emoji_sad);
        } else {
            textViewMessage.setText("Seu IMC indica que você tem obesidade mórbida.");
            imageViewEmoji.setImageResource(R.drawable.emoji_very_sad);
        }

        // botão voltar para main
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}