package com.calculadora.calcamp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class ProximasQuedas extends AppCompatActivity {
    String[] eq = new String[12];
    int[] pont = new int[12];
    int tipo;
    int [] abatesAnteriorInt = new int[12];
    int numQueda;

    public static final int CONSTANTE_TELA_EQUIPES = 3;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximas_quedas);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = getIntent().getExtras();
            if (params != null) {
                eq = params.getStringArray("equipes");
                pont = params.getIntArray("pontuacao");
                tipo = params.getInt("tipo");
                abatesAnteriorInt = params.getIntArray("kills");
                numQueda =params.getInt("numQueda")+1;
                passaInformacoesTela(eq, pont);
            }
        }
    }

    public void passaInformacoesTela(String[] eq, int[] pontuacao){
        TextView numQuedaTela = findViewById(R.id.numQueda);
        //Vetor com textviews da activity PROXIMASQUEDAS para as Equipes
        TextView[] tvsEquipes = { (TextView) findViewById(R.id.Equipe1),
                (TextView) findViewById(R.id.Equipe2),
                (TextView) findViewById(R.id.Equipe3),
                (TextView) findViewById(R.id.Equipe4),
                (TextView) findViewById(R.id.Equipe5),
                (TextView) findViewById(R.id.Equipe6),
                (TextView) findViewById(R.id.Equipe7),
                (TextView) findViewById(R.id.Equipe8),
                (TextView) findViewById(R.id.Equipe9),
                (TextView) findViewById(R.id.Equipe10),
                (TextView) findViewById(R.id.Equipe11),
                (TextView) findViewById(R.id.Equipe12)};

        String strNumQueda = Integer.toString(numQueda);
        numQuedaTela.setText("Queda "+strNumQueda);

        for(int i = 0;  i <eq.length; i++ ){
            try{
                tvsEquipes[i].setText(eq[i]);
            }catch (Exception e){
                continue;
            }
        }
    }

    public void mostrarResultado(View view, int[] pontos, String[] eq, int[] kill){

        Bundle params = new Bundle();
        params.putIntArray("pontuacao", pontos);
        params.putStringArray("equipes", eq);
        params.putIntArray("kills", kill);
        params.putInt("tipo", tipo);
        params.putInt("numQueda", numQueda);
        Intent intent = new Intent(getApplicationContext(), resultado.class);
        intent.putExtras(params);
        startActivityForResult(intent, CONSTANTE_TELA_EQUIPES);
    }

    public void somaQuedas(View view){
        int [] posInt = new int[12];
        int[] pontos;
        int[] kill = new int[12];

        //Vetor de textViews da Activity PROXIMASQUEDAS para as Posições
        EditText[] tvsPos = {
                (EditText) findViewById(R.id.tv1),
                (EditText) findViewById(R.id.tv2),
                (EditText) findViewById(R.id.tv3),
                (EditText) findViewById(R.id.tv4),
                (EditText) findViewById(R.id.tv5),
                (EditText) findViewById(R.id.tv6),
                (EditText) findViewById(R.id.tv7),
                (EditText) findViewById(R.id.tv8),
                (EditText) findViewById(R.id.tv9),
                (EditText) findViewById(R.id.tv10),
                (EditText) findViewById(R.id.tv11),
                (EditText) findViewById(R.id.tv12)};

        //Vetor de textViews da Activity PROXIMASQUEDAS
        EditText [] tvsKill = { (EditText) findViewById(R.id.edtkill1),
                (EditText) findViewById(R.id.edtkill2),
                (EditText) findViewById(R.id.edtkill3),
                (EditText) findViewById(R.id.edtkill4),
                (EditText) findViewById(R.id.edtkill5),
                (EditText) findViewById(R.id.edtkill6),
                (EditText) findViewById(R.id.edtkill7),
                (EditText) findViewById(R.id.edtkill8),
                (EditText) findViewById(R.id.edtkill9),
                (EditText) findViewById(R.id.edtkill10),
                (EditText) findViewById(R.id.edtkill11),
                (EditText) findViewById(R.id.edtkill12)};


        for(int i = 0; i < 12; i++){
            if(TextUtils.isEmpty(tvsPos[i].getText())) {
                posInt[i] = 12;
            }else{
                posInt[i] = Integer.valueOf(tvsPos[i].getText().toString());
            }
            if(TextUtils.isEmpty(tvsKill[i].getText())) {
                kill[i] = 0;
            }else{
                kill[i] = Integer.valueOf(tvsKill[i].getText().toString());
            }
        }

        if(tipo == 2){
            PontuacaoLbffNova pontuaNova = new PontuacaoLbffNova(eq, kill, posInt, pont);
            pontos = pontuaNova.calcular();
        }else{
            PontuacaoLbffAntiga pontuaAntiga = new PontuacaoLbffAntiga(eq, kill, posInt, pont);
            pontos = pontuaAntiga.calcular();
        }

        //Soma abates de quedas diferentes e pontos
        for(int i = 0; i < 12 ; i++){
            kill[i] = kill[i]+ abatesAnteriorInt[i];
        }

        OrdenaPontos ordena = new OrdenaPontos(pontos, eq, kill);
        ArrayList<Object> informacoesOrdenadas = (ArrayList<Object>) ordena.bubbleSort();

        pontos = (int[]) informacoesOrdenadas.get(0);
        eq = (String[]) informacoesOrdenadas.get(1);
        kill = (int[]) informacoesOrdenadas.get(2);

        //método que chama nova Intent e mostra o resultado
        mostrarResultado(view, pontos, eq, kill);
    }

}