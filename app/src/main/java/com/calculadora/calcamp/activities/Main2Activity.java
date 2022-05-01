package com.calculadora.calcamp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.calculadora.calcamp.R;
import com.calculadora.calcamp.tipo_pontuacao;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void alimentaInformacoes(View view){
        startActivity(new Intent(getBaseContext(), Equipes.class));
    }

    public void mostratiposdepontuacao(View view){
        startActivity(new Intent(getBaseContext(), tipo_pontuacao.class));
    }

    public void mostraajuda(View view){
        startActivity(new Intent(getBaseContext(), ajuda.class));
    }

}