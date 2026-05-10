/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreavl;

/**
 *
 * @author USER
 */
/* 
 * Implementação de árvore AVL, adotando o uso de referências unidirecionais pai->filhos.
*/

public class ArvoreAVL {
    public No raiz;
    
    public void excluirArvore() {
        this.raiz = null;
    }
    public int max(int altura_no1, int altura_no2) {
        return (altura_no1 > altura_no2) ? altura_no1 : altura_no2;
    }
    
    public int getAlturaNo(No no) {
        return (no == null) ? -1 : no.altura;
    }
    public No rotacionarDireita(No no) {
        No aux;
        aux = no.esquerda;
        no.esquerda = aux.direita;
        aux.direita = no;
        no.altura = this.max(this.getAlturaNo(no.direita), this.getAlturaNo(no.esquerda))+1;
        aux.altura = this.max(this.getAlturaNo(aux.esquerda), no.altura)+1;
        return aux;
    }
    public No rotacionarEsquerda(No no) {
        No aux;
        aux = no.direita;
        no.direita = aux.esquerda;
        aux.esquerda = no;
        no.altura = this.max(this.getAlturaNo(no.direita), this.getAlturaNo(no.esquerda))+1;
        aux.altura = this.max(this.getAlturaNo(aux.direita), no.altura)+1;
        return aux;
    }
    public No rotacionarEsquerdaDireita(No no) {
        no.esquerda = this.rotacionarEsquerda(no.esquerda);
        return this.rotacionarDireita(no);
    }
    public No rotacionarDireitaEsquerda(No no) {
        no.direita = this.rotacionarDireita(no.direita);
        return this.rotacionarEsquerda(no);
    }   
    public void inserir(int valor) {
        this.raiz =  inserir(this.raiz, valor);
    }
    
    private No inserir(No raiz, int valor) {
        if(raiz == null) {
            return (new No(valor));
        }
        if(valor <= raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, valor);
            if((getAlturaNo(raiz.esquerda) - getAlturaNo(raiz.direita)) == 2)
                if(valor <= raiz.esquerda.valor)
                    raiz = rotacionarDireita(raiz);
                else
                    raiz = rotacionarEsquerdaDireita(raiz);
        }
        else 
            if(valor > raiz.valor) {
              raiz.direita = inserir(raiz.direita, valor);
              if((getAlturaNo(raiz.direita)-getAlturaNo(raiz.esquerda)) == 2)
                  if(valor > raiz.direita.valor)
                      raiz = rotacionarEsquerda(raiz);
                  else
                      raiz = rotacionarDireitaEsquerda(raiz);
            }
        raiz.altura = max(getAlturaNo(raiz.esquerda), getAlturaNo(raiz.direita))+1;
        return raiz;
    }
    
    
    public void remover(int valor) {
        this.raiz = remover(this.raiz, valor);
    }
    private No remover(No no, int valor) {
        if(no == null) return no;   // Elemento a ser removido não foi encontrado.
        if(valor < no.valor) no.esquerda = remover(no.esquerda, valor);
        else
            if( valor > no.valor ) no.direita = remover(no.direita, valor);
            else  // achou o nó a ser removido
                if(no.esquerda != null && no.direita != null ) { // Nó possuio dois filhos 
                    no.valor = sucessor(no.valor).valor;
                    no.direita = remover(no.direita, no.valor);
                }else no = ( no.esquerda != null ) ? no.esquerda : no.direita;
        return no;
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
    
   
    public No buscar(int elemento) {
 
        No atual = this.raiz;
       
        while(atual != null) {
            if(atual.valor == elemento) 
                return atual;
            if(atual.valor <= elemento) 
                atual = atual.direita;
            else 
                atual = atual.esquerda;
            }
        return null;
    }
    
    public No antecessor(int elemento) {
        No atual;  
         
        atual = buscar(elemento); 
        if(atual == null)
            return null;
        if(atual.esquerda == null) // elemento nao possui filho a esquerda
            return null;
         // o elemento possui filho a esquerda
           
         atual = atual.esquerda;
         while(atual.direita != null)
               atual = atual.direita;
          return atual;
    
    }
    
    
    public No sucessor(int elemento) {
        No atual;  
         
        atual = buscar(elemento); 
        if(atual == null)
            return null;
        if(atual.direita == null) // elemento nao possui filho a direita
            return null;
         // o elemento possui filho a direita
           
         atual = atual.direita;
         while(atual.esquerda != null)
               atual = atual.esquerda;
          return atual;
            
        }
    
    
      
    
    public void emOrdem() {
        visitarEmOrdem(raiz);
    }
    private void visitarEmOrdem(No no) {
        if(no != null) {
          visitarEmOrdem(no.esquerda);
          System.out.print(no.valor + " ");
          visitarEmOrdem(no.direita);
        }
    }
    
    public void emOrdemReversa() {
        visitarEmOrdemReversa(raiz);
    }
    private void visitarEmOrdemReversa(No no) {
        if(no != null) {
          visitarEmOrdemReversa(no.direita);
          System.out.print(no.valor + " ");
          visitarEmOrdemReversa(no.esquerda);
        }
    }
   
    public void preOrdem() {
        visitarPreOrdem(raiz);
    }
    
    private void visitarPreOrdem(No no) {
        if(no != null) {
          System.out.print(no.valor + " ");
          visitarPreOrdem(no.esquerda);
          visitarPreOrdem(no.direita);
        }
        
    }
    public void posOrdem() {
        visitarPosOrdem(raiz);
    }
     
    private void visitarPosOrdem(No no) {
        if(no != null) {
          visitarPosOrdem(no.esquerda);
          visitarPosOrdem(no.direita);
          System.out.print(no.valor + " ");
        }
        
    }
    public int quantidadedenos() {
        return quantidadedenos(raiz);
    }
    
    private int quantidadedenos(No raiz) {
        if(raiz == null)
            return 0;
        else
            return 1 + quantidadedenos(raiz.esquerda) + quantidadedenos(raiz.direita);
    }
    public String emLargura() {
        Fila fila = new Fila();
        No no;
        String emlargura = "";
        
        
        if(raiz == null) // árvore vazia
            return null;
        fila.inserir(raiz);
        while(!fila.filaVazia()) {
            no = fila.remover();
            emlargura += no.valor;
            emlargura += " ";
            if(no.esquerda != null)
                fila.inserir(no.esquerda);
            if(no.direita != null)
                fila.inserir(no.direita);
        }
        return emlargura;    
    }
    
    /* 
        Retorma o menor elemento da árvore
    */
    public No getMinimo()  {
        return getMinimo(this.raiz);
    }
    
    public No getMinimo(No raiz) {
        if (raiz.esquerda == null)
            return raiz;
        else {
            return getMinimo(raiz.esquerda);
        }
    }
    
     /* 
        Retorma o maior elemento da árvore
    */
    public No getMaximo() {
        return getMaximo(this.raiz);
    }
    
    public No getMaximo(No raiz) {
        if (raiz.direita == null)
            return raiz;
        else {
            return getMaximo(raiz.direita);
        }
    }
    /* o nó anteterior ao nó "elemento" é aquele que o precede na visitação em ordem
     Exemplo: Viitação em ordem: 10, 15,,17, 22, 67
     o anterior ao nó 21 é o 17
   */
    public No anterior(int elemento) {
        
        // EXERCÍCIO: siomiliar à implementação e programação do método próximo( )
        return null;
    }
    
     // o nó peóximo ao nó "elemento" é aquele que o sucede na visitação em ordem
    // Exemplo: Viitação em ordem: 10, 15,,17, 22, 67
    // o próximo ao nó 21 é o 67
    public No proximo(int elemento) {
       // EXERCÍCIO: método não implementado
       return null;
    }
    
    
     public void ancestrais(int elemento) {
        return;
    }

   public int quantidadeNosIterativo() {
    if (raiz == null) {
        return 0;
    }
    int contador = 0;
    Fila fila = new Fila();
    fila.inserir(raiz);
    while (!fila.filaVazia()) {
        No atual = fila.remover();
        contador++;
        if (atual.esquerda != null) {
            fila.inserir(atual.esquerda);
        }
        if (atual.direita != null) {
            fila.inserir(atual.direita);
        }
    }
    return contador;
}

    
    
}