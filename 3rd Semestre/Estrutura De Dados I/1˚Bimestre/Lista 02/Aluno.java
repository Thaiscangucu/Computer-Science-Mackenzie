// Exercício 4

public class Aluno{
    private String rgm;
    private String nome;
    private char sexo;
    private float notaA;
    private float notaB;
    private float notaC;
    private float notaD;

    public Aluno(){
    
    }

    public Aluno(String rgm, String nome){
        this.rgm = rgm;
        this.nome = nome;
    }
    
    public Aluno(String rgm, String nome, char sexo, float notaA, float notaB, float notaC, float notaD){
        this.rgm = rgm;
        this.nome = nome;
        this.sexo = sexo;
        this.notaA = notaA;
        this.notaB = notaB;
        this.notaC = notaC;
        this.notaD = notaD;
    }

    public float calculaMedia(){
        return (notaA + notaB + notaC + notaD) / 4;
    }

    public boolean avaliaSituacao(){
        return calculaMedia() >= 6 ? true : false;
    }

    public void mostraDados(){
        System.out.println("Rgm: " + rgm);
        System.out.println("Nome: " + nome);
        System.out.println("Sexo: " + sexo);
        System.out.println("Nota A: " + notaA);
        System.out.println("Nota B: " + notaB);
        System.out.println("Nota C: " + notaC);
        System.out.println("Nota D: " + notaD);
        System.out.println("Média: " + calculaMedia());
        System.out.println("Situação: " + (avaliaSituacao() ? "Aprovado" : "Reprovado"));
    }
}