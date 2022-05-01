package com.calculadora.calcamp.activities;

public class CalculoQueda {
    private int tipo;
    int [] pontuacao = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // Vetor auxiliar caso não se digite nada na pontuação
    int pos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}; //Vetor auxiliar usado no cálculo da primeira queda


    public int getTipo(){
        return tipo;
    }

    public void setTipo(int tplbff){
        tipo = tplbff;
    }

    public int[] calculaPontuacao(String[] eq, int[] kills, int[] pontuacao, int[] pos) {

        int[] qtdPontos = new int[eq.length];

        //Verificação se é a primeira queda
        if(pos[0] == 111){
            pos = this.pos;
            pontuacao = this.pontuacao;
        }

        if(this.tipo == 1){ //LBFF Antiga escolhida
            for (int i = 0; i < pos.length; i++) {
                switch (pos[i]) {
                    case 1:
                        qtdPontos[i] = 20;
                        break;
                    case 2:
                        qtdPontos[i] = 17;
                        break;
                    case 3:
                        qtdPontos[i] = 15;
                        break;
                    case 4:
                        qtdPontos[i] = 13;
                        break;
                    case 5:
                        qtdPontos[i] = 12;
                        break;
                    case 6:
                        qtdPontos[i] = 10;
                        break;
                    case 7:
                        qtdPontos[i] = 6;
                        break;
                    case 8:
                        qtdPontos[i] = 4;
                        break;
                    case 9:
                        qtdPontos[i] = 3;
                        break;
                    case 10:
                        qtdPontos[i] = 2;
                        break;
                    case 11:
                        qtdPontos[i] = 1;
                        break;
                    case 12:
                        qtdPontos[i] = 0;
                        break;
                }
                try{
                    qtdPontos[i] += kills[i]* 2 + pontuacao[i];
                }catch (Exception e){
                    continue;
                }
            }
        }else{ //lbff Nova Escolhida
            for (int i = 0; i < pos.length;i++){
                switch (pos[i]) {
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
                try{
                    qtdPontos[i] += kills[i] + pontuacao[i];
                }catch (Exception e){
                    continue;
                }
            }
        }
        return qtdPontos;
    }

}
