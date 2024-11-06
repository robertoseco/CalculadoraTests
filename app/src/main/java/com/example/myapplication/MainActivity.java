package com.example.myapplication;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private TextView resultado;
    private String operacion = "";
    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.Resultado);
        calculadora = new Calculadora();


        configurarBotones();
    }


    private void configurarBotones() {

        int[] numeros = {R.id.B0, R.id.B1, R.id.B2, R.id.B3, R.id.B4, R.id.B5, R.id.B6, R.id.B7, R.id.B8, R.id.B9};
        for (int id : numeros) {
            Button boton = findViewById(id);
            boton.setOnClickListener(AsignarNum);
        }


        Button botonSumar = findViewById(R.id.Bmas);
        Button botonRestar = findViewById(R.id.Bmenos);
        botonSumar.setOnClickListener(operadores);
        botonRestar.setOnClickListener(operadores);



        Button botonIgual = findViewById(R.id.Bigual);
        Button botonBorrar = findViewById(R.id.BC);
        botonIgual.setOnClickListener(igual);
        botonBorrar.setOnClickListener(borrar);
    }


    private void reiniciarCalculadora() {
        resultado.setText("");
        operacion = "";
    }


    private final View.OnClickListener AsignarNum = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Button boton = (Button) view;
            String numero = boton.getText().toString();


            operacion += numero;
            resultado.setText(operacion);
        }
    };


    private final View.OnClickListener borrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reiniciarCalculadora();
        }
    };


    private final View.OnClickListener igual = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                double resultadoOperacion = calculadora.resolverExpresion(operacion);
                resultado.setText(String.valueOf(resultadoOperacion));
                operacion = String.valueOf(resultadoOperacion);
            } catch (Exception e) {
                resultado.setText("Sintax Error");

            }
        }
    };


    private final View.OnClickListener operadores = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Button boton = (Button) view;
            String operador = boton.getText().toString();


            operacion += operador;
            resultado.setText(operacion);
        }
    };
}