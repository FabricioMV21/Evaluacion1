package fmv.fabricio.evaluacion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    //Declaracion de variables
    Button Calcular;
    Button Limpiar;
    EditText CVerduras;
    EditText CHectareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Calcular   = findViewById(R.id.btnCalcular);
        Limpiar    = findViewById(R.id.btnLimpiar);
        CVerduras  = findViewById(R.id.edtCostoV);
        CHectareas = findViewById(R.id.edtTotalH);

        //Nombre de aplicacion barra principal
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cotizacion");

        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validación de campos vacíos
                if (CVerduras.getText().toString().isEmpty() || CHectareas.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingrese Los Datos Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    String verduraStr = CVerduras.getText().toString();
                    String hectareaStr = CHectareas.getText().toString();
                    int v = Integer.parseInt(verduraStr);
                    int h = Integer.parseInt(hectareaStr);
                    int calculo = VerdurasPorHectareas(v, h);

                    //Guardar el resultado en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("Calculos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Obtener la lista actual de resultados (si existe)
                    Set<String> resultadosSet = sharedPreferences.getStringSet("resultados", new HashSet<>());

                    //Calcular el índice para el nuevo cálculo
                    int nuevoIndice = resultadosSet.size() + 1;

                    //Agregar el nuevo cálculo con un prefijo indicando el índice
                    resultadosSet.add("Resultado " + nuevoIndice + " = " + String.valueOf(calculo));

                    //Guardar la lista actualizada en SharedPreferences
                    editor.putStringSet("resultados", resultadosSet);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                    intent.putExtra("Resultado", calculo);
                    startActivity(intent);
                }
            }
        });

        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LimpiarCampos();
            }
        });

    }

    //Metodo que genera el calulo Matematico
    public int VerdurasPorHectareas(int verduras, int hectareas){
        int resultado = verduras * hectareas;
        return resultado;
    }
    //Metodo para limpiar los campos
    public void LimpiarCampos(){
        CVerduras.setText("");
        CHectareas.setText("");
    }
}