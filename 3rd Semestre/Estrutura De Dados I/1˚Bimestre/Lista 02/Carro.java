// Exercício 1

public class Carro {
    private int ano;
    public String marca;
    private String modelo;
    private String categoria;
    
    public Carro() {
        
    }
    
    public Carro(int ano, String marca, String modelo, String categoria) {
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        if(ano > 0)
          this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    void setMarca(String marca) {
        this.marca = marca;
    }
    
    public void imprima() {
        System.out.println("Ano: " + ano);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Categoria: " + categoria);
    }

}
