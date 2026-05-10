class Cachorro extends Animal{
   
    //construtores
    public Cachorro(String nome,  int energia, boolean dormindo) {
            super(nome, energia, dormindo);
        }
    
    public Cachorro() {
    }
    
    public void andar() {
        this.setEnergia(this.getEnergia() - 1);
    }
    
    public void comer(int quantidade){
        this.setEnergia(this.getEnergia()+quantidade);
    }
    
    public void latir(){
        System.out.println("Au Au!!");
    }
}