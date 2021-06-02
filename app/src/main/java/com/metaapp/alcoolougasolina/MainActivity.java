package com.metaapp.alcoolougasolina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText precoAlcool, precoGasolina;
    private EditText consumoAlcool, consumoGasolina, distanciaPercorrida ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.precoAlcool = findViewById(R.id.precoAlcool);
        this.precoGasolina = findViewById(R.id.precoGasolina);
        this.consumoAlcool = findViewById(R.id.consumoAlcool);
        this.consumoGasolina = findViewById(R.id.consumoGasolina);
        this.distanciaPercorrida = findViewById(R.id.distanciaPercorrida);

    }

    public void calculo () {

        String pAlcool = precoAlcool.getText().toString();
        String pGasolina = precoGasolina.getText().toString();
        String cAlcool = consumoAlcool.getText().toString();
        String cGasolina = consumoGasolina.getText().toString();
        String dPercorrida = distanciaPercorrida.getText().toString();

        Boolean verificado =autenticacao (pAlcool, pGasolina, cAlcool, cGasolina, dPercorrida);

        if ( verificado ){

            Double valorPAlcool = Double.parseDouble(pAlcool);
            Double valorPGasolina = Double.parseDouble(pGasolina);
            Double valorCAlcool = Double.parseDouble(cAlcool);
            Double valorCGasolina = Double.parseDouble(cGasolina);
            Double valordPercorrida = Double.parseDouble(dPercorrida);

            DecimalFormat df = new DecimalFormat("0.00");

            Double formulaAlcool = valordPercorrida / valorCAlcool * valorPAlcool;
            Double formulaGasolina = valordPercorrida / valorCGasolina * valorPGasolina;

            Double diferencaAlcool = formulaGasolina - formulaAlcool;
            Double diferencaGasolina = formulaAlcool - formulaGasolina;

            if ( formulaAlcool < formulaGasolina){

                AlertDialog.Builder resultAlcool = new AlertDialog.Builder(this);
                resultAlcool.setTitle("Realize o abastecimento com Alcool");
                resultAlcool.setMessage("Assim realizando este abastecimento você ira economizar: R$ "
                                    + df.format(diferencaAlcool));

                resultAlcool.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // AÇÃO DE RESET
                        consumoGasolina.setText("");
                        consumoAlcool.setText("");
                        distanciaPercorrida.setText("");
                        precoAlcool.setText("");
                        precoGasolina.setText("");


                    }
                });

                resultAlcool.create();
                resultAlcool.show();
            }else if(formulaAlcool == formulaGasolina){

                AlertDialog.Builder resultEmpate = new AlertDialog.Builder(this);
                resultEmpate.setTitle("Realize o abastecimento com qualquer um dos Combustiveis");
                resultEmpate.setMessage("A economia nao tras beneficio significativo ao uso");

                resultEmpate.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // AÇÃO DE RESET
                        consumoGasolina.setText("");
                        consumoAlcool.setText("");
                        distanciaPercorrida.setText("");
                        precoAlcool.setText("");
                        precoGasolina.setText("");

                    }
                });

            }else{

                AlertDialog.Builder resultGasolina = new AlertDialog.Builder(this);
                resultGasolina.setTitle("Realize o abastecimento com Gasolina");
                resultGasolina.setMessage("Assim realizando este abastecimento você ira economizar: R$ "
                        +  df.format(diferencaGasolina));

                resultGasolina.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // AÇÃO DE RESET
                        consumoGasolina.setText("");
                        consumoAlcool.setText("");
                        distanciaPercorrida.setText("");
                        precoAlcool.setText("");
                        precoGasolina.setText("");

                    }
                });

                resultGasolina.create();
                resultGasolina.show();

            }

        }else{

            Toast.makeText(getApplicationContext(),
                            "FAVOR PREENCHER TODOS OS CAMPOS",
                            Toast.LENGTH_LONG   ).show();

        }



    }

    public Boolean autenticacao (String precoA, String precoG, String consumoA, String consumoG, String distanciaP ){

        Boolean verificado = true;
        if(precoA ==null || precoA.equals("") || precoG == null || precoG.equals("") ||
            consumoA == null || consumoA.equals("") || consumoG == null || consumoG.equals("") ||
            distanciaP == null || distanciaP.equals("")){

            verificado = false;

        }

        return verificado;


    }

    public void btncalcular (View view) {

        calculo();

    }
}