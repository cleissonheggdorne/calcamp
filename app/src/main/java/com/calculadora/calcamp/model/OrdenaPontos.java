package com.calculadora.calcamp.model;

import java.util.ArrayList;

public class OrdenaPontos {

    //Ordena Equipes pela Pontuação
    int[] pontuacao;
    String[] equipes;
    int[] kills;
    int tipo;
    ArrayList<Object> informacoesOrdenadas = new ArrayList<Object>();

    public OrdenaPontos(int[] pontuacao, String[] equipes, int[] kills) {
        this.pontuacao = pontuacao;
        this.equipes = equipes;
        this.kills = kills;
    }

    public Object bubbleSort(){
        boolean troca = true;
        int aux;
        String aux1;
        int aux2;

        while (troca) {
            troca = false;
            for (int i = 0; i < pontuacao.length - 1; i++) {
                if (pontuacao[i] < pontuacao[i + 1]) {
                    aux = pontuacao[i];
                    pontuacao[i] = pontuacao[i + 1];
                    pontuacao[i + 1] = aux;

                    //Ordena equipes
                    aux1 = equipes[i];
                    equipes[i] = equipes[i + 1];
                    equipes[i + 1] = aux1;

                    //Ordena Kills//
                    aux2 = kills[i];
                    kills[i] = kills[i + 1];
                    kills[i + 1] = aux2;
                    troca = true;

                    //Se houver empate em pontos, verifica quantidade de kills e ordena por ela
                }
            }
            for (int i = 0; i < pontuacao.length - 1; i++) {
                if(pontuacao[i] == pontuacao[i + 1]){
                    if(kills[i] < kills[i+1]){
                        aux = pontuacao[i];
                        pontuacao[i] = pontuacao[i + 1];
                        pontuacao[i + 1] = aux;

                        //Ordena equipes//
                        aux1 = equipes[i];
                        equipes[i] = equipes[i + 1];
                        equipes[i + 1] = aux1;

                        //Ordena Kills//
                        aux2 = kills[i];
                        kills[i] = kills[i + 1];
                        kills[i + 1] = aux2;
                        troca = true;
                    }
                }
            }
        }
        informacoesOrdenadas.add(pontuacao);
        informacoesOrdenadas.add(equipes);
        informacoesOrdenadas.add(kills);

        return informacoesOrdenadas;
    }
}

