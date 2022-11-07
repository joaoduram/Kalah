import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Escreva a descrição da classe Tabuleiro aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Tabuleiro
{
    public Map<Integer,Cava> cavas; //Tabuleiro
    public int[] tab1 = {1,2,3,4,5,6}; //Cavas do jogador 1
    public int[] tab2 = {8,9,10,11,12,13}; //Cavas do jogador 2
    
    public void criarTabuleiro(){
        this.cavas = new HashMap<Integer,Cava>();
        int[] nomesCavas = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        
        for(int i = 0; i < 14; i++){
            if(nomesCavas[i]!=7&&nomesCavas[i]!=14){
                Cava cavaJogavel = new Cava();
                cavaJogavel.cava(nomesCavas[i],4);
                cavas.put(nomesCavas[i],cavaJogavel);
            }else{
                Cava cavaJogavel = new Cava();
                cavaJogavel.cava(nomesCavas[i],0);
                cavas.put(nomesCavas[i],cavaJogavel);            
            }
        }
    }
    
    void mostrarTabuleiro(){
        for(int i = 1; i<=14;i++){
            Cava cavaAtual = cavas.get(i);
            System.out.println("");
            System.out.println("Cava de nome "+ cavaAtual.getNomeCava());
            System.out.println("Qtd de pecas "+ cavaAtual.getQtdPecas());
            System.out.println("");
        }
    }
    
    public Map<Integer,Cava> getCavas(){
        return cavas;
    }
    
    public int[] getTab1(){
        return tab1;
    }
    
    public int[] getTab2(){
        return tab2;
    }
}
