import java.util.Stack;

class Converter {
    public static String converter(String infixo) {
        StringBuilder saida = new StringBuilder();
        Stack<String> pilha = new Stack<>();
        
        for (String elemento : infixo.split("(?=[-+*/()])|(?<=[-+*/()])")) {
            elemento = elemento.trim();
            if (elemento.isEmpty()) continue;
            
            if (ehNumero(elemento)) {
                saida.append(elemento).append(" ");
            } else if (elemento.equals("(")) {
                pilha.push(elemento);
            } else if (elemento.equals(")")) {
                while (!pilha.isEmpty() && !pilha.peek().equals("(")) {
                    saida.append(pilha.pop()).append(" ");
                }
                pilha.pop(); // Remove '('
            } else if (ehOperador(elemento)) {
                while (!pilha.isEmpty() && precedencia(pilha.peek()) >= precedencia(elemento)) {
                    saida.append(pilha.pop()).append(" ");
                }
                pilha.push(elemento);
            }
        }
        while (!pilha.isEmpty()) {
            saida.append(pilha.pop()).append(" ");
        }
        return saida.toString().trim();
    }
    
    private static boolean ehNumero(String elemento) {
        return elemento.matches("\\d*\\.?\\d+"); // Aceita números com ou sem parte inteira
    }
    
    private static boolean ehOperador(String elemento) {
        return elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/");
    }
    
    private static int precedencia(String operador) {
        switch (operador) {
        case "+": case "-": return 1;
        case "*": case "/": return 2;
        default: return -1;
        }
    }
}
