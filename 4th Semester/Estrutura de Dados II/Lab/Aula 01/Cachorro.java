class Cachorro {
    //atributos
    private String nome;
    private double peso;
    private int nascimento;
    private String raca;
    private String cor;
    private int energia;
    private boolean dormindo;
    
    //construtor
    public Cachorro(String nome, double peso, int nascimento, String raca, 
                    String cor, int energia, boolean dormindo) {
            this.nome = nome;
            this.peso = peso;
            this.nascimento = nascimento;
            this.raca = raca;
            this.cor = cor;
            this.energia = energia;
            this.dormindo = dormindo;
    }
    
    public Cachorro() {
        
    }
    
    //métodos
    public String toString() {
        String s;
        s = "\nNome = " + this.nome + 
            "\nDormindo = " + this.dormindo +
            "\nEnergia = " + this.energia + "\n\n";
        return s;
    }
    
    public void setEnergia(int energia) {
        if(energia >= 0)
            this.energia = energia;
    }
    
    public int getEnergia() {
        return this.energia;
    }
    
    public void latir(){
        System.out.println("Au Au!!");
    }
    public void andar() {
        this.energia -= 1;
        
    }
    public void dormir() {
        this.dormindo = true;
        
    }
    public void acordar() {
        this.dormindo = false;
        
    }
    public void comer(int quantidade){
        this.energia += quantidade;
    }
}