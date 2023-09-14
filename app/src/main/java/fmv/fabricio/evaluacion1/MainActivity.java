package fmv.fabricio.evaluacion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        setSupportActionBar(toolbar);
        //Nombre de aplicacion barra principal
        getSupportActionBar().setTitle("Cotizacion");

        Calcular   = findViewById(R.id.btnCalcular);
        Limpiar    = findViewById(R.id.btnLimpiar);
        CVerduras  = findViewById(R.id.edtCostoV);
        CHectareas = findViewById(R.id.edtTotalH);


        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validacion de campos vacios
                if(CVerduras.getText().toString().isEmpty() || CHectareas.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Ingrese Los Datos Correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    String verduraStr = CVerduras.getText().toString();
                    String hectareaStr = CHectareas.getText().toString();
                    int v = Integer.parseInt(verduraStr);
                    int h = Integer.parseInt(hectareaStr);
                    Intent intent = new Intent(getApplicationContext(),ResultadoActivity.class);
                    intent.putExtra("Resultado",VerdurasPorHectareas(v,h));
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