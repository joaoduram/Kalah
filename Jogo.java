import java.util.Scanner;
import java.util.*;

/**
 * Escreva a descrição da classe Teste aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Jogo
{
    public static void jogo(){
        Jogador jogador1 = new Jogador();
        Jogador jogador2 = new Jogador();
        jogador1.criarJogador("Jog1",14);
        jogador2.criarJogador("Jog2",7);
        Tabuleiro tab = new Tabuleiro();
        tab.criarTabuleiro();
        int numCava;
        boolean fim = false;
        
        do{
            //Jogador 1
            System.out.println("Vez do jogador "+ jogador1.getNome() +" Que possui o repositorio "+ jogador1.getRepositorio());
            jogada(jogador1,tab);
            tab.mostrarTabuleiro();
            if(confereSeHouveVencedor(jogador1,tab)==true){
                finalizarJogo(jogador1);
                fim = true;
            }
            //Jogador 2
            System.out.println("Vez do jogador "+ jogador2.getNome() +" Que possui o repositorio "+ jogador2.getRepositorio());
            jogada(jogador2,tab);
            tab.mostrarTabuleiro();
            if(confereSeHouveVencedor(jogador2,tab)==true){
                finalizarJogo(jogador2);
                fim = true;
            }
        }while(fim!=true);
    }
    
    public static boolean jogoFinalizado(int num){
        if(num!=0){
            return false;
        }else{
            return true;
        }
    }
    
    static void jogada(Jogador jogador,Tabuleiro tab){
        Scanner cava = new Scanner(System.in);
        int numCava = cava.nextInt();
        
        if(numCava==7||numCava==14){
            System.out.println("Jogada invalida");
            jogada(jogador,tab);
        }else{
            if(jogador.getRepositorio()==14 && numCava>7){
                jogador.setVez();
                Map<Integer,Cava> tabuleiro = tab.getCavas();
                int ultimaCava = moverPecas(jogador,numCava,tabuleiro);
                captura(jogador,numCava,tabuleiro);
                if(ultimaCava>7){
                    System.out.println("Vez do "+jogador.getNome());
                    jogada(jogador,tab);
                }
                jogador.setVez();
            }else if(jogador.getRepositorio()==7 && numCava<7){
                jogador.setVez();
                Map<Integer,Cava> tabuleiro = tab.getCavas();
                int ultimaCava = moverPecas(jogador,numCava,tabuleiro);
                captura(jogador,numCava,tabuleiro);
                if(ultimaCava<7){
                    System.out.println("Vez do "+jogador.getNome());
                    jogada(jogador,tab);
                }
                jogador.setVez();
            }else{
                System.out.println("Jogada invalida");
                jogada(jogador,tab);
            }
        }
    }
    
    public static void captura(Jogador jogador,int ultimaCava, Map<Integer,Cava> tabuleiro){
        Cava cava = tabuleiro.get(ultimaCava);
        int qtdDePecas = cava.getQtdPecas();
        int opostaNum;
        if(qtdDePecas==1){
            if(jogador.getRepositorio()==14){
                opostaNum = ultimaCava+7;
            }else{
                opostaNum = ultimaCava-7;
            }
            Cava oposta = tabuleiro.get(ultimaCava);
            System.out.println("Houve uma captura");
            int pecas = oposta.getQtdPecas();
            if(pecas>0){
                oposta.setQtdPecas(0);
                Cava rep = tabuleiro.get(jogador.getRepositorio());
                int qtdAtualPecas = rep.getQtdPecas();
                rep.setQtdPecas(qtdAtualPecas+pecas);
            }
        }
    }
    
    public static boolean confereSeHouveVencedor(Jogador jogador,Tabuleiro tab){
        Map<Integer,Cava> tabuleiro = tab.getCavas();
        if(jogador.getRepositorio()==7){
            for(int i = 1; i < 7; i++){
                Cava cavaAtual = tabuleiro.get(i);
                if(cavaAtual.getQtdPecas()!=0){
                    return false;
                }
            }
            return true;
        }else if(jogador.getRepositorio()==14){
            for(int i = 8; i < 14; i++){
                Cava cavaAtual = tabuleiro.get(i);
                if(cavaAtual.getQtdPecas()!=0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static void finalizarJogo(Jogador jogador){
        System.out.println("Jogo finalizado");
        System.out.println("Jogador "+jogador.getNome()+"foi o vencedor");   
    }
    
    public static int moverPecas(Jogador jogador, int nomeCava, Map<Integer,Cava> tabuleiro){
        Cava cavaAtual = tabuleiro.get(nomeCava); //Cava que ira ser zerada
        int pecas = cavaAtual.getQtdPecas(); //Cava que ira ser zerada
        cavaAtual.setQtdPecas(0); //Zera as peças da casa selecionada
        int nomeCavaAtual = nomeCava; //Cava que tera peças distribuidas
        int qtdAtualPecas; //Cava que tera peças distribuidas
        while(pecas>0){
            nomeCavaAtual = nomeCavaAtual+1;
            if(nomeCavaAtual>14){
                nomeCavaAtual=1;
                cavaAtual = tabuleiro.get(nomeCavaAtual);
            }else{
                cavaAtual = tabuleiro.get(nomeCavaAtual);
            }
            qtdAtualPecas = cavaAtual.getQtdPecas();
            cavaAtual.setQtdPecas(qtdAtualPecas+1);            
            pecas--;
        }
        return nomeCavaAtual; 
    }
}
