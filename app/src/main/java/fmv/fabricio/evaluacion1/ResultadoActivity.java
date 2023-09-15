package fmv.fabricio.evaluacion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResultadoActivity extends AppCompatActivity {
    //Declaracion de variables
    TextView resultado;
    Button Volver;
    ListView historial;
    ImageView vaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        resultado = findViewById(R.id.txtResultado);
        Volver = findViewById(R.id.btnVolver);
        historial = findViewById(R.id.ListViewR);
        vaciar = findViewById(R.id.ImageVaciar);

        //Nombre de aplicacion barra principal
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cotizacion");

        //Recibir Un Campo Numerico
        int calculo = getIntent().getIntExtra("Resultado", 0);
        resultado.setText("Total: "+ String.valueOf(calculo));

        //Configuracion Del ListView para historial de calculos
        //Obtener calculo
        SharedPreferences sharedPreferences = getSharedPreferences("Calculos", Context.MODE_PRIVATE);

        //Obtener la lista de resultados desde SharedPreferences
        Set<String> resultadosSet = sharedPreferences.getStringSet("resultados", new HashSet<>());
        List<String> resultadosList = new ArrayList<>(resultadosSet);

        //Configurar el adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultadosList);
        historial.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        //Configuracion Botones CLcik
        //Volver a la pantalla de inicio
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Limpiar el ListView
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) historial.getAdapter();
                adapter.clear();
                adapter.notifyDataSetChanged();

                //Limpiar SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("Calculos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });





    }
}