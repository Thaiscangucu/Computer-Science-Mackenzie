public class Pilha {
	// Atributos privados
	private static final int TAM_DEFAULT = 100;
	private	int topoPilha;
    private	int e[ ];
    // Métodos públicos
   	public	Pilha(int tamanho) {  // construtor 1
   		this.e = new int [tamanho];
   		this.topoPilha = -1;
   	}
   	
  	public	Pilha() {// construtor 2
   		this(TAM_DEFAULT);
   	}
   	
   	
   	//Verifica se a pilha
   	//está vazia
   	public boolean isEmpty() {
   		if (this.topoPilha == -1)
   			return true;
   		else
   			return false;	
   	}
   	// Verifica se a pilha está
   	// cheia
   	public boolean isFull() {
   	  if (this.topoPilha == this.e.length-1)
   	   		return true;
   	  else
   	   		return false;	
   	}
   	// insere um elemento e 
   	// no topo da pilha
   	public void push(int e) {
   		if (! this.isFull( ))
   			this.e[++this.topoPilha] = e;
   		else 
   			System.out.println("overflow - Estouro de Pilha");
   	}
   	//remove um elemento 
   	//do topo da pilha
   	public int pop() {
   		if (! this.isEmpty( ))
   			return this.e[this.topoPilha--];
   		else{
   			System.out.println( "underflow - Esvaziamento de Pilha");
		   return -1;
   		}  
   	}
   	//Retorna o elemento que está
   	//no topo da pilha
   	public int topo() {
	  if ( ! this.isEmpty( ))
			return this.e[this.topoPilha];
		else{
			System.out.println("Underlow - Esvaziamento de Pilha");
			return -1;
		}
   	}
   	// obtém o total de elementos 
   	//armazenados na Pilha
   	public int sizeElements() {
	  return topoPilha+1;
   	}
   	
   	
   
	// Sobrescrita/sobreposição (override) do método toString(), que veio da superclasse Object.
	// O retorno do método toString() é a representação de um objeto em formato string, e toString()
	// geralmente é executado (de forma implícita) quando passamos um objeto ao System.out.print*().
	//
	// Experimente incluir o seguinte código na main() e veja a saída:
	// Pilha p = new Pilha();
	// System.out.println(f);
	//
	// Depois, remova/comente o método toString() abaixo e rode o código acima novamente.
	@Override
	public String toString() {
	
		StringBuilder sb = new StringBuilder();
		sb.append("[Pilha] \ntopoPilha: ")
			.append(topoPilha)
			.append("\ncapacidade: ")
			.append(e.length)
			.append("\nquantidade de elementos: ")
			.append(sizeElements());
		if (topoPilha != -1) {
			sb.append("\nvalor do Topo: ")
				.append(topo());
		}else sb.append("\nvalor do Topo: PILHA VAZIA");

		sb.append("\nConteúdo da Pilha': [ ");
			
		for (int i = 0; i <= topoPilha; ++i)
			if (i != topoPilha) sb.append( e[i] + ", ");
			else sb.append( e[i] );
		sb.append( " ]");
		return sb.toString();
	}
}
