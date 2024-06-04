package com.example.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;

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

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowResult();
            }
        });
    }

    private void calculateAndShowResult() {
            // solicita peso e altura
            double weight = Double.parseDouble(editTextWeight.getText().toString());
            double height = Double.parseDouble(editTextHeight.getText().toString());

            // calculo do IMC
            double imc = weight / (height * height);

            // nova intent para resultado
            Intent intent = new Intent(MainActivity.this, Activity_Result.class);

            // indica o resultado do imc para próxima tela
            intent.putExtra("BMI_RESULT", imc);

            // inicia nova tela
            startActivity(intent);
        }
}