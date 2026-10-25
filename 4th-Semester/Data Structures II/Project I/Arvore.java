import java.util.Stack;

class Arvore {
    public No raiz;
    
    // Método para construir a árvore a partir de uma expressão em notação pós-fixa
    public void inserir(String posfixa) {
        Stack<No> pilha = new Stack<>();
        for (String elemento : posfixa.split(" ")) {
            if (ehOperador(elemento)) {
                No direito = pilha.pop(); // Primeiro sai o operando da direita
                No esquerdo = pilha.pop(); // Depois o operando da esquerda
                No no = new No(elemento);
                no.direito = direito;
                no.esquerdo = esquerdo;
                pilha.push(no);
            } else {
                pilha.push(new No(elemento));
            }
        }
        raiz = pilha.pop();
    }
    
    // Método para avaliar a expressão armazenada na árvore
    public double avaliar() {
        return avaliar(raiz);
    }
    
    private double avaliar(No no) {
        if (no == null) {
            return 0;
        }
        // Se for uma folha, converte para número e retorna
        if (!ehOperador(no.valor)) {
            return Double.parseDouble(no.valor);
        }
        // Avalia os filhos esquerdo e direito
        double valorEsquerdo = avaliar(no.esquerdo);
        double valorDireito = avaliar(no.direito);
        
        // Aplica a operação correspondente
        return aplicarOperador(no.valor, valorEsquerdo, valorDireito);
    }
    
    // Verifica se é um operador válido
    private boolean ehOperador(String elemento) {
        return elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/");
    }
    
    // Utiliza cada operador de acordo com a sua operação 
    private double aplicarOperador(String operador, double a, double b) {
        switch (operador) {
        case "+": return a + b;
        case "-": return a - b;
        case "*": return a * b;
        case "/":
            if (b == 0) throw new ArithmeticException("Erro: Divisão por zero!");
            return a / b;
        default: throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
    
    
    private void exibirArvore(No no, int nivel) {
        if(no != null) {
            exibirArvore(no.direito, nivel + 1);
            for(int r = 1; r <= nivel; r++)
                System.out.print(" - ");
            System.out.println(no.valor);
            exibirArvore(no.esquerdo, nivel + 1);
        }
    }
    
    public void exibirArvore() {
        exibirArvore(this.raiz, 0);
        System.out.println();
    }
    
    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }
    
    private void preOrdem(No no) {
        if(no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerdo);
            preOrdem(no.direito);
        }
        
    }
    
    public void emOrdem() {
        emOrdem(raiz);
        System.out.println();
    }
    
    private void emOrdem(No no) {
        if(no != null) {
            emOrdem(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdem(no.direito);
        }
        
    }
    
    public void posOrdem() {
        posOrdem(raiz);
        System.out.println();
    }
    
    private void posOrdem(No no) {
        if(no != null) {
            posOrdem(no.esquerdo);
            posOrdem(no.direito);
            System.out.print(no.valor + " ");
        }
        
    }
}
