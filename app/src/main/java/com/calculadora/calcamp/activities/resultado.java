package com.calculadora.calcamp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.calculadora.calcamp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class resultado extends AppCompatActivity {

    public static final int CONSTANTE_TELA_EQUIPES = 2;

    String [] eq = new String[12];
    int [] killsInt = new int[12];
    int [] pont = new int[12];
    int numQueda;
    int tipo;


    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

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
                killsInt = params.getIntArray("kills");
                pont = params.getIntArray("pontuacao");
                tipo = params.getInt("tipo");
                numQueda = params.getInt("numQueda");
            }
        }
        mostraResultado(eq, killsInt, pont);
    }

        public void mostraResultado(String[] equipe, int[] kills, int[] pontuacao){

            //Vetor com textviews da activity RESULTADO para as Equipes
            TextView[] tvsEquipes = {(TextView) findViewById(R.id.textView),
                    (TextView) findViewById(R.id.textView2),
                    (TextView) findViewById(R.id.textView3),
                    (TextView) findViewById(R.id.textView4),
                    (TextView) findViewById(R.id.textView5),
                    (TextView) findViewById(R.id.textView6),
                    (TextView) findViewById(R.id.textView7),
                    (TextView) findViewById(R.id.textView8),
                    (TextView) findViewById(R.id.textView9),
                    (TextView) findViewById(R.id.textView10),
                    (TextView) findViewById(R.id.textView11),
                    (TextView) findViewById(R.id.textView12)};
            TextView tv = (TextView) findViewById(R.id.textViewResult);

            //Vetor de textViews da Activity RESULTADO para as kills. textView1 a textView12
            TextView[] tvsKills = {(TextView) findViewById(R.id.textViewk1),
                    (TextView) findViewById(R.id.textViewk2),
                    (TextView) findViewById(R.id.textViewk3),
                    (TextView) findViewById(R.id.textViewk4),
                    (TextView) findViewById(R.id.textViewk5),
                    (TextView) findViewById(R.id.textViewk6),
                    (TextView) findViewById(R.id.textViewk7),
                    (TextView) findViewById(R.id.textViewk8),
                    (TextView) findViewById(R.id.textViewk9),
                    (TextView) findViewById(R.id.textViewk10),
                    (TextView) findViewById(R.id.textViewk11),
                    (TextView) findViewById(R.id.textViewk12)};

            //Vetor de textViews da Activity RESULTADO para as pontuações. textViewk1 a textViewk12
            TextView[] tvsPont = {(TextView) findViewById(R.id.textViewp1),
                    (TextView) findViewById(R.id.textViewp2),
                    (TextView) findViewById(R.id.textViewp3),
                    (TextView) findViewById(R.id.textViewp4),
                    (TextView) findViewById(R.id.textViewp5),
                    (TextView) findViewById(R.id.textViewp6),
                    (TextView) findViewById(R.id.textViewp7),
                    (TextView) findViewById(R.id.textViewp8),
                    (TextView) findViewById(R.id.textViewp9),
                    (TextView) findViewById(R.id.textViewp10),
                    (TextView) findViewById(R.id.textViewp11),
                    (TextView) findViewById(R.id.textViewp12)};

            for (int i = 0; i < equipe.length; i++) {
                int contadorDeEquipes = 1;
                try {
                    if (!tvsEquipes[i].equals("")) {
                        tvsEquipes[i].setText(i + 1 + " " + eq[i]);
                        tvsKills[i].setText(String.valueOf(killsInt[i]).toString());
                        tvsPont[i].setText(String.valueOf(pont[i]).toString());
                        contadorDeEquipes++;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        public void alimentaInformacoes (View view){

            //Joga informações pra tela seguinte
            Bundle params = new Bundle();
            params.putInt("tipo", tipo);
            params.putInt("numQueda", numQueda);
            params.putStringArray("equipes", eq);
            params.putIntArray("pontuacao", pont);
            params.putIntArray("kills", killsInt);
            Intent intent = new Intent(getApplicationContext(), ProximasQuedas.class);
            intent.putExtras(params);
            startActivity(intent);
        }
}
