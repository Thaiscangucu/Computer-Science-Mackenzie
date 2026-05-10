// implentacaoo com ligacao unidirecional. pai-> filhos
import java.util.LinkedList;
public class Arvore {
    public No raiz;

    
    public void excluirArvore() {
        this.raiz = null;
    }
    
    public void inserir(int elemento) {
        No novoNo = new No();
        novoNo.valor = elemento;
        if(this.raiz == null) // Árvore está vazia
            raiz = novoNo;
        else {
             No atual = raiz;
             No pai;
             while(true) {
              pai = atual;
                if(elemento <= atual.valor) {
                    atual = atual.esquerda;
                     if(atual == null) {
                        pai.esquerda = novoNo;
                        return;
                       }
                }
                else {
                     atual = atual.direita;
                     if (atual == null) {       
                       pai.direita = novoNo;
                       return;
                    }
                }
            }   
        }
    }

    

    
    public  No buscar(int elemento) {
        No atual = raiz;
        
        while(atual != null) {
            if(atual.valor == elemento) 
                return atual;
            if(atual.valor < elemento)
                atual = atual.direita;
            else
                atual = atual.esquerda;
            }
        return null;
    }

    public void emLargura(){
        LinkedList <No> fila = new LinkedList<>();
        if(raiz == null){
            return;
        }
        else{
            fila.add(raiz);
        }
        while(true){
            if(fila.isEmpty()){
                break;
            }
            if(fila.getFirst().esquerda != null) fila.add(fila.getFirst().esquerda);
            if(fila.getFirst().direita != null) fila.add(fila.getFirst().direita);
            System.out.print(fila.getFirst().valor + " ");
            fila.remove(0);
        }
    }
      
        
     public void exibirArvore() {
        exibirArvore(this.raiz, 0);
    }
    
    private void exibirArvore(No no, int nivel) {
        if(no != null) {
            exibirArvore(no.direita, nivel + 1);
            for(int r = 1; r <= nivel; r++)
              System.out.print(" - ");
            System.out.println(no.valor);
            exibirArvore(no.esquerda, nivel + 1);
        }
    }
    
    
    public void emOrdem() {
        exibirArvore(this.raiz, 0);
        emOrdem(raiz);
    }
    
    private void emOrdem(No no) {
        if(no != null) {
          emOrdem(no.esquerda);
          System.out.print(no.valor + " ");
          emOrdem(no.direita);
        }
        
    }
   
    public void preOrdem() {
        preOrdem(raiz);
    }
    
    private void preOrdem(No no) {
        if(no != null) {
          System.out.print(no.valor + " ");
          preOrdem(no.esquerda);
          preOrdem(no.direita);
        }
        
    }
    public void posOrdem() {
        posOrdem(raiz);
    }
     
    private void posOrdem(No no) {
        if(no != null) {
          posOrdem(no.esquerda);
          posOrdem(no.direita);
          System.out.print(no.valor + " ");
        }
        
    }
   
}