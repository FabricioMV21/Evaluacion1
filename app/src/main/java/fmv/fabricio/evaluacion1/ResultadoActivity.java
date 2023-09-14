package fmv.fabricio.evaluacion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
    //Declaracion de variables
    TextView resultado;
    Button Volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        //Nombre de aplicacion barra principal
        getSupportActionBar().setTitle("Resultados");

        resultado = findViewById(R.id.txtResultado);
        Volver = findViewById(R.id.btnVolver);

        //Recibir Un Campo Numerico
        int calculo = getIntent().getIntExtra("Resultado", 0);
        resultado.setText("Total: "+ String.valueOf(calculo));

        //Volver a la pantalla de inicio
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}