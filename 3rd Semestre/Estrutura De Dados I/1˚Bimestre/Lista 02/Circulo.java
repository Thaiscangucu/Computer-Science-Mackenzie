// Exercício 2

// define classe com o nome Circulo 
public class Circulo {

    // declara as variáveis raio e pi
    private float raio;
    private static final double PI = Math.PI;

    // condição que o raio deve ser positivo
    public Circulo(float raio) {
        if (raio > 0) {
            this.raio = raio;
        } else {
            throw new IllegalArgumentException("O raio deve ser maior que zero.");
        }
    }
    
    // método para receber o raio
    public float getRaio() {
        return raio;
    }

    // método para calcular o diametro
    public float calculaDiametro() {
        return 2 * raio;
    }

    // método para calcular a área
    public float calcularArea() {
        return (float) (PI * raio * raio);
    }

    // método para imprimir os dados
    public void imprima() {
        System.out.println("Raio: " + raio);
        System.out.println("Diâmetro: " + calculaDiametro());
        System.out.println("Área: " + calcularArea());

    }
}

