public class Fila {
    No []f;
    int inicio = 0;
    int fim = -1;
    int qtdElementos = 0;


    Fila (int tam){
        f = new No[tam];
    }
    /**
     * Tamanho padrÃ£o para fila: 500 posições
     */
    Fila (){
        f = new No[500];
    }

    boolean filaVazia(){
        if (qtdElementos==0)
            return true;
        return false;
    }

    boolean filaCheia(){
        if(qtdElementos == f.length)
            return true;
        return false;
    }

    boolean inserir(No no){
       if(filaCheia())
           return false;
       fim = (fim + 1)% f.length;
       f[fim] = new No();
       f[fim] = no;
       qtdElementos++;
       return true;
    }

    No remover(){
        No no;
        if(filaVazia())
            return null;
        no = f[inicio];
        inicio = (inicio + 1) % f.length;
        qtdElementos--;
        return no;

    }

    No verInicio() {
        if(filaVazia())
            return null;
        return f[inicio];
    }

    void verTodos() {
        int qtd = qtdElementos;
        int i = inicio;

        while(qtd > 0){
            System.out.print("| " + f[i].valor + " | ");
            i = (i + 1) % f.length;
            qtd--;
        }
    }
 }