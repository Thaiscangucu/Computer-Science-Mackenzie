public class Quadrado {

    private float lado;

    public Quadrado(float lado) {
        if (lado > 0){
            this.lado = lado;
        }
        else {
            throw new IllegalArgumentException("O lado deve ser maior que zero.");
        }
    }

    public float getLado(){
        return lado;
    }

    public float calculaArea(){
        return lado*lado;
    }

    public void imprima() {
        System.out.println("Lado: " + lado);
        System.out.println("Área: " + calculaArea());
    }

}