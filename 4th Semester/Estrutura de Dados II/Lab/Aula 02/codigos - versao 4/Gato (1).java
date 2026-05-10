class Gato extends Animal {
    
    //construtores
    public Gato(String nome,  int energia, boolean dormindo) {
            super(nome, energia, dormindo);
    }
    
    public Gato() {
    }
    
    //métodos
    public void andar() {
        this.setEnergia(this.getEnergia() - 2);
    }
    
    public void comer(int quantidade){
        this.setEnergia(this.getEnergia()+quantidade*2);
    }
    
    public void som(){
        System.out.println("Miauuuuuu!");
    } 
}