// Exercício 1

public class Aula2 {
    public static void main(String[] args) {
       Carro c = new Carro();
       c.setAno(2025);
       c.setMarca("Nissan");
       c.imprima();
        
       Carro c2 = new Carro();
       c2.setMarca("Toyota");
       c2.setAno(2018);
       c2.imprima();
       
       Carro c3 = new Carro(2023, "Toyota", "Corolla", "sedan");
       c3.imprima();
    }
    
}
