
public class Arvore {
    public No raiz;
    
    public void excluirArvore() {
        this.raiz = null;
    }
    
    public void inserir(int elemento) {
        No novoNo = new No();
        novoNo.valor = elemento;
        if(this.raiz == null) // árvore está vazia
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
                }else {
                     atual = atual.direita;
                     if (atual == null) {       
                       pai.direita = novoNo;
                       return;
                    }
                }
            }   
        }
    }

    public static No getMinimo(No raiz) {
        if (raiz.esquerda == null)
            return raiz;
        else {
            return getMinimo(raiz.esquerda);
        }
    }
    
    public No remover(int valor) {
        return remover(this.raiz, valor);
    }
    
    public static No remover(No raiz, int valor) {
        if (raiz == null)
            return null;
        if (raiz.valor > valor) {
            raiz.esquerda = remover(raiz.esquerda, valor);
        } else 
            if (raiz.valor < valor) {
                raiz.direita = remover(raiz.direita, valor);
 
        } else {
            //se o nó  a ser removido tem filho à esquerda e filho à direita
            if (raiz.esquerda != null && raiz.direita != null) {
                No temp = raiz;
                // Busca o mínimo nó à direita 
                
                 No no_minimo_direita = getMinimo(temp.direita);
                // troca o nó atual com o mínimo nó da sub-árvore À direite
                raiz.valor = no_minimo_direita.valor;
                // Remove o mínimo nó à direita
                raiz.direita = remover(raiz.direita, no_minimo_direita.valor);
 
            }
            //se o nó  a ser removido tem apenas filho à esquerda
            else if (raiz.esquerda != null) {
                raiz = raiz.esquerda;
            }
            //se o nó  a ser removido tem apenas filho à direita
            else if (raiz.direita != null) {
                raiz = raiz.direita;
            }
            // se o nó a ser removido é uma folha
            else
                raiz = null;
        }
        return raiz;
    }
    

    public  No buscar(int elemento) {
        No atual = raiz;
        boolean achou = false;
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
    // o nó anteterior ao nó "elemento" é aquele que o precede na visitação em ordem
    // Exemplo: Viitação em ordem: 10, 15,,17, 22, 67
    // o anterior ao nó 21 é o 17
    public No anterior(int elemento) {
        
        // EXERCÍCIO: siomiliar à implementação e programação do método próximo( )
        return null;
    }
    
    // o antecessor do nó com chave "elemento" é o maior dos menores presente NA SUB-ÁRVORE QUE TEM COMO RAÍZ 
    // o nó com chave "elemento"
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
    
    // o nó peóximo ao nó "elemento" é aquele que o sucede na visitação em ordem
    // Exemplo: Viitação em ordem: 10, 15,,17, 22, 67
    // o próximo ao nó 21 é o 67
    public No proximo(int elemento) {
       No atual;   
       No pai = this.raiz; 
       atual = buscar(elemento); 
      
       if(atual == null)
          return null;
       if(atual.direita == null) // elemento nao possui filho a direita
          if(atual == this.raiz) // se o elemento nao tem filho a direita e eh a raiz
             return null;
          else
             if(pai.valor < elemento) {   // elemento eh filho a direta
                while(atual != null) {   
                   if(pai.valor < elemento) {
                       // percorre os ancestrais do elemento ate encontrar um ancestral 
                        // maior que elemento,caso exista.
                      atual = pai;
                   }
                   else
                      return pai;
                        // retorno o menor ancestral ancestral que eh maior que elemento,
                atual = pai;
                }
            return null; // elemento eh filho a direita e nao possui ancestral maior que ele.
            }
            else  // elemento eh filho a direita
               return pai;     
       else {  // o elemento possui filho a direita
          atual = atual.direita;
          while(atual.esquerda != null)
             atual = atual.esquerda;
          return atual;
       }
    }
        
    // o sucessor do nó com chave "elemento" é o menor dos maiores presente NA SUB-ÁRVORE QUE TEM COMO RAÍZ 
    // o nó com chave "elemento"
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
    
     public void ancestrais(int elemento) {
        return;
    }
    
    public void emOrdem() {
        emOrdem(raiz);
    }
    
    private void emOrdem(No no) {
        if(no != null) {
          emOrdem(no.esquerda);
          System.out.print(no.valor + " ");
          emOrdem(no.direita);
        }
        
    }

    public void emOrdemDec() {
        emOrdemDec(raiz);
    }
    
    private void emOrdemDec(No no) {
        if(no != null) {
          emOrdemDec(no.direita);
          System.out.print(no.valor + " ");
          emOrdemDec(no.esquerda);
        }
        
    }

    public void inverteNos() {
        raiz = inverteNos(raiz);
    }

    private No inverteNos(No no) {
        if (no == null) {
            return null;
        }
        
        No temp = no.esquerda;
        no.esquerda = inverteNos(no.direita);
        no.direita = inverteNos(temp);
        
        return no;
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

    public No copiar() {
        return this.raiz;
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
}
