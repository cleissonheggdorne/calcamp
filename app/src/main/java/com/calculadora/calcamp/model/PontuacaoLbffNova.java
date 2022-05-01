package com.calculadora.calcamp.model;

public class PontuacaoLbffNova implements TipoPontuacao {

    private int tipo;
    int [] pontuacao = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // Vetor auxiliar caso não se digite nada na pontuação
    //Vetor auxiliar usado no cálculo da primeira queda
    int posInt[] = {1,2,3,4,5,6,7,8,9,10,11,12};

    public int getTipo(){
        return tipo;
    }
    public void setTipo(int tplbff){
        tipo = tplbff;
    }

    private String[] eq;
    private int[] kills;

    public PontuacaoLbffNova(String[] equips, int[] kills, int[] pos, int[]pontuacao){
        this.eq = equips;
        this.kills = kills;
        if(pos[0] == 111){
            pos = this.posInt;
            pontuacao = this.pontuacao;
        }else{
            this.posInt = pos;
            this.pontuacao = pontuacao;
        }
    }

    public PontuacaoLbffNova(int[] kills, int[] pos) {
        this.kills = kills;
        this.posInt = pos;
    }

    public PontuacaoLbffNova(int[] kills, int[] pontuacao, int[] pos) {
        this.kills = kills;
        this.pontuacao = pontuacao;
        this.posInt = pos;
    }

    @Override
    public int[] calcular() {
        int[] qtdPontos = new int[eq.length];
        for (int i = 0; i < posInt.length;i++) {
            if(eq[i] != "") {
                switch (posInt[i]) {
                    case 1:
                        qtdPontos[i] = 12;
                        break;
                    case 2:
                        qtdPontos[i] = 9;
                        break;
                    case 3:
                        qtdPontos[i] = 8;
                        break;
                    case 4:
                        qtdPontos[i] = 7;
                        break;
                    case 5:
                        qtdPontos[i] = 6;
                        break;
                    case 6:
                        qtdPontos[i] = 5;
                        break;
                    case 7:
                        qtdPontos[i] = 4;
                        break;
                    case 8:
                        qtdPontos[i] = 3;
                        break;
                    case 9:
                        qtdPontos[i] = 2;
                        break;
                    case 10:
                        qtdPontos[i] = 1;
                        break;
                    case 11:
                        qtdPontos[i] = 0;
                        break;
                    case 12:
                        qtdPontos[i] = 0;
                        break;
                }

                try {
                    qtdPontos[i] += kills[i] + pontuacao[i];
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return qtdPontos;
    }
}
