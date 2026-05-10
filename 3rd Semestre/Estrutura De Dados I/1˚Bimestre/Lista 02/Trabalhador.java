// Exercício 3
public class Trabalhador {
  String nome;
  float salario;
  String cpf;
  int idade;
  char sexo;
  String endereco;

  public Trabalhador() {
    
  }

  public Trabalhador(String nome, float salario, String cpf, int idade, char sexo, String endereco) {
    this.nome = nome;
    this.salario = salario;
    this.cpf = cpf;
    this.idade = idade;
    this.sexo = sexo;
    this.endereco = endereco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public float getSalario() {
    return salario;
  }

  public void setSalario(float salario) {
    this.salario = salario;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public char getSexo() {
    return sexo;
  }

  public void setSexo(char sexo) {
    this.sexo = sexo;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public float calculaSalarioAnual() {
    return 12 * salario;
  }

  public void mostra() {
    System.out.println("Nome: " + nome);
    System.out.println("Salário: R$" + salario);
    System.out.println("CPF: " + cpf);
    System.out.println("Idade: " + idade);
    System.out.println("Sexo: " + sexo);
    System.out.println("Endereço: " + endereco);
    System.out.println("Salário Anual: R$" + calculaSalarioAnual());
    System.out.print("\n");

  }

}