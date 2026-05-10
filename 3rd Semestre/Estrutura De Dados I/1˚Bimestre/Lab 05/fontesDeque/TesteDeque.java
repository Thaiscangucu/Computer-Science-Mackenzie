import java.util.Scanner;

public class TesteDeque {
	public static void main(String args[]) {
		int n1, n2, n1aux, n2aux,  valor;
	 	
		Deque  D = new Deque();
	 	
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Digite um número: ");
	 	n1 = entrada.nextInt();
	 	
	 	System.out.print("Digite outro número: ");
	 	n2 = entrada.nextInt();
	 	
	 	System.out.println(D);
	 	
	 	n1aux = n1; 
	 	n2aux = n2;

	 	System.out.println("Tamanho do Deque: " + D.size());
		
		while ( n1aux >= 1) {
	    	if (n1aux % 2 == 1)
	      		D.enqueueRight( n2aux );
	    	n1aux = n1aux / 2;
	    	n2aux = n2aux * 2;
	  	}

		System.out.println("Tamanho do Deque: " + D.size() );
		
	 	System.out.println(D);
	 	
		valor = 0;
	  	while (! D.dIsEmpty( ) )
	    	valor = D.dequeueLeft( ) + valor;
	  	
	  	System.out.println("\nO produto de " + n1 + " por " + n2 + " = " + valor );
	}

}
