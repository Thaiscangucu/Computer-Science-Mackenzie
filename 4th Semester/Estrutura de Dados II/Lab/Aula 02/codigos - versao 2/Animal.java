class Animal {
     //atributos
    private String nome;
    private int energia;
    private boolean dormindo;
    
    // construtor
    public Animal(String nome,  int energia, boolean dormindo) {
        this.nome = nome;
        this.energia = energia;
        this.dormindo = dormindo;
    }
    
    public Animal() {
        this.nome = "Sem nome";
        this.energia = 2;
        this.dormindo = false;
    }
    public void setEnergia(int energia) {
        if(energia >= 0)
            this.energia = energia;
    }
    
    public int getEnergia() {
        return this.energia;
    }
    
    public void dormir() {
        this.dormindo = true;
        
    }
    
    public void acordar() {
        this.dormindo = false;
    }
    
    public String toString() {
        String s;
        s = "\nNome = " + this.nome + 
            "\nDormindo = " + this.dormindo +
            "\nEnergia = " + this.energia + "\n\n";
        return s;
    }
}