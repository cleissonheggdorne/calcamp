package com.calculadora.calcamp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.calculadora.calcamp.R;
import com.calculadora.calcamp.model.OrdenaPontos;
import com.calculadora.calcamp.model.PontuacaoLbffAntiga;
import com.calculadora.calcamp.model.PontuacaoLbffNova;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;



public class Equipes extends AppCompatActivity {
    int numQueda = 1;
    int tipo = 2; //tipo selecionado de pontuacao
    EditText edtEquipe1, edtEquipe2, edtEquipe3, edtEquipe4, edtEquipe5,
            edtEquipe6, edtEquipe7, edtEquipe8, edtEquipe9, edtEquipe10,
            edtEquipe11, edtEquipe12, edtkill1, edtkill2, edtkill3, edtkill4, edtkill5, edtkill6,
            edtkill7, edtkill8, edtkill9, edtkill10, edtkill11, edtkill12;

    public static final int CONSTANTE_TELA_EQUIPES = 1;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    public void onRadioButtonClicked(View view) {
        // botão check?
        boolean checked = ((RadioButton) view).isChecked();

        // Verifica qual radioButton foi marcado
        switch(view.getId()) {
            case R.id.onlbffAntiga:
                if (checked)
                    tipo = 1;
                    break;

            case R.id.onlbffNova:
                if (checked)
                    tipo = 2;
                    break;
        }
    }

    //public void calcularQueda(View view) {
    public void calcularQueda(View v, String[] listaEquipsString, int[] listaKillsInt) {

        int [] auxValidacaoQueda1 = {0};

        //Vetor de 1 posição apenas para validação de primeira queda
        int[] validacaoQueda1 = {111};

        int[] qtdPontos = new int[12];

        if(tipo == 2){
            PontuacaoLbffNova pontuaNova = new PontuacaoLbffNova(listaEquipsString, listaKillsInt, validacaoQueda1, auxValidacaoQueda1);
            qtdPontos = pontuaNova.calcular();
        }else{
            PontuacaoLbffAntiga pontuaAntiga = new PontuacaoLbffAntiga(listaEquipsString, listaKillsInt, validacaoQueda1, auxValidacaoQueda1);
            qtdPontos = pontuaAntiga.calcular();
        }

        OrdenaPontos ordena = new OrdenaPontos(qtdPontos, listaEquipsString, listaKillsInt);
        ArrayList<Object> informacoesOrdenadas = (ArrayList<Object>) ordena.bubbleSort();

        Bundle params = new Bundle();
        params.putIntArray("pontuacao", (int[]) informacoesOrdenadas.get(0));
        params.putStringArray("equipes", (String[]) informacoesOrdenadas.get(1));
        params.putIntArray("kills", (int[]) informacoesOrdenadas.get(2));
        params.putInt("tipo", tipo);
        params.putInt("numQueda", numQueda);
        Intent intent2 = new Intent(getApplicationContext(), resultado.class);
        intent2.putExtras(params);

        startActivityForResult(intent2, CONSTANTE_TELA_EQUIPES);
    }

    public void mostrarAlerta(View v, String[] listaEquipsString, int[] listaKillsInt){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Poxa, a galera não compareceu");
        alerta.setMessage("Deseja calcular assim mesmo?");
        alerta.setPositiveButton("Sim", (dialog, which) -> calcularQueda(v, listaEquipsString, listaKillsInt));
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.create().show();
    }
    public void verificaEntradaNew(View view) {

        //Pega o que foi digitado nos campos das equipes
        EditText[] listaEquips = {edtEquipe1 = (EditText) findViewById(R.id.Equipe1),
                edtEquipe2 = (EditText) findViewById(R.id.Equipe2),
                edtEquipe3 = (EditText) findViewById(R.id.Equipe3),
                edtEquipe4 = (EditText) findViewById(R.id.Equipe4),
                edtEquipe5 = (EditText) findViewById(R.id.Equipe5),
                edtEquipe6 = (EditText) findViewById(R.id.Equipe6),
                edtEquipe7 = (EditText) findViewById(R.id.Equipe7),
                edtEquipe8 = (EditText) findViewById(R.id.Equipe8),
                edtEquipe9 = (EditText) findViewById(R.id.Equipe9),
                edtEquipe10 = (EditText) findViewById(R.id.Equipe10),
                edtEquipe11 = (EditText) findViewById(R.id.Equipe11),
                edtEquipe12 = (EditText) findViewById(R.id.Equipe12)};

        //Pega o que foi digitado nos campos das kills
        EditText[] listaKills = {edtkill1 = (EditText) findViewById(R.id.edtkill1),
                edtkill2 = (EditText) findViewById(R.id.edtkill2),
                edtkill3 = (EditText) findViewById(R.id.edtkill3),
                edtkill4 = (EditText) findViewById(R.id.edtkill4),
                edtkill5 = (EditText) findViewById(R.id.edtkill5),
                edtkill6 = (EditText) findViewById(R.id.edtkill6),
                edtkill7 = (EditText) findViewById(R.id.edtkill7),
                edtkill8 = (EditText) findViewById(R.id.edtkill8),
                edtkill9 = (EditText) findViewById(R.id.edtkill9),
                edtkill10 = (EditText) findViewById(R.id.edtkill10),
                edtkill11 = (EditText) findViewById(R.id.edtkill11),
                edtkill12 = (EditText) findViewById(R.id.edtkill12)};

        int[] auxValidacaoQueda1 = {0};

        //Vetor de 1 posição apenas para validação de primeira queda
        int[] validacaoQueda1 = {111};

        String[] listaEquipsString = new String[listaEquips.length]; //Inicializa vetor que vai receber todas equipes

        //Inicializa vetor que vai receber todas kills das equipes
        int[] listaKillsInt = new int[listaKills.length];


        //Pegar String Digitada pelo usuário em kills e equipes
        for (int i = 0; i < listaEquips.length; i++) {
            if (TextUtils.isEmpty(listaEquips[i].getText())) {
                listaEquipsString[i] = ""; //equipesAux[i]"";
            } else {
                listaEquipsString[i] = listaEquips[i].getText().toString();
            }
            if (TextUtils.isEmpty(listaKills[i].getText())) {
                listaKillsInt[i] = 0;
            } else {
                listaKillsInt[i] = Integer.valueOf(listaKills[i].getText().toString());
            }
        }

        boolean nenhumCampoPreenchido = true;
        boolean todosCamposPreenchidos = true;

        for(String str : listaEquipsString){
            if(str.equals("")){
                todosCamposPreenchidos = false;
            }else{
                nenhumCampoPreenchido = false;
            }
        }

        if(todosCamposPreenchidos){
            calcularQueda(view, listaEquipsString, listaKillsInt);
        }else if(nenhumCampoPreenchido){
            Toast toast = Toast.makeText(this, "Não Há Nada Para Calcular!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }else{
            mostrarAlerta(view, listaEquipsString, listaKillsInt);
        }
    }
}