
//Classe DLL<T>: encarregada de manipular a estrutura de dados
//lista duplamente ligada ordenada e circular genérica.
//
//Autor1: Ivan Carlos Alcântara de Oliveira.
//Data da Criação: 10/10/2024. 15h.
public class DLL<T extends Comparable<T>> {
	private	NodeDLL<T> head;
	private int size;
	
	// DLL(): Construtor vazio
	// inicia o cabeça da lista como null e tamanho = 0
	public DLL(){
		this.head = null;
		this.size = 0;
	}
	// isEmpty(): verifica se a lista está vazia, retornando "true" se vazia
	// "false" se não vazia.
	public boolean isEmpty() { // Verifica se DLL está vazia
		return head == null;
	}
	// isFull(): verifica se a lista está cheia, retornando "true" se cheia
	// "false" se não cheia.
	public boolean isFull() {
		NodeDLL<T> aux = new NodeDLL<>();
		return aux == null;
	}
	// getSize(): retorna o tamanho da lista (total de elementos armazenados).
	public int getSize() { return size; }
	// getHead(): retorna o NodeDLL cabeça de lista.
	public NodeDLL<T> getHead() { return head; }
	// Percorre a DLL em ordem crescente 
	// e mostra os seus valores
	public void showAscending(){
		// Percorre a lista e imprime os valores
		// em ordem Crescente
		if (isEmpty()) System.out.print( "Lista vazia!\n");
		else{
			System.out.print( "Lista: [ ");
			NodeDLL<T> pAnda = this.head;
			while (pAnda.getRight() != this.head){
				System.out.print( pAnda.getData() + " ");
				pAnda= pAnda.getRight();
			}
			System.out.print( pAnda.getData() +"]");
		}
	}

	public void inverte() {
		if (isEmpty() || size == 1) return;

		NodeDLL<T> pAnda = head;
		NodeDLL<T> pAnt = null;
		int cont = 0;
		while(cont < size){
			pAnt = pAnda.getLeft();
			pAnda.setLeft(pAnda.getRight());
			pAnda.setRight(pAnt);
			pAnda = pAnda.getLeft();
			cont++;
		}
		// Ajustar o ponteiro de cabeça para ser o novo início da lista
		head = head.getRight();
	}

	public void concatena(DLL lista){
		if(lista.isEmpty())return;
		if(isEmpty()){
			head = lista.getHead();
			size = lista.getSize();
		}
		else{
			NodeDLL<T> tail = head.getLeft();
			tail.setRight(lista.getHead());
			lista.getHead().getLeft().setRight(head);
			tail.setLeft(lista.getHead().getLeft());
		}
		size += lista.getSize();
	}

	// Percorre a DLL em ordem decrescente e mostra os seus valores
	public void showDescending(){
		// Percorre a lista e imprime os valores
		// em ordem decrescente
		if (isEmpty()) System.out.print( "Lista vazia!\n" );
		else{
			System.out.print( "Lista: [ " );
			NodeDLL<T> pAnda = this.head;
			while (pAnda.getLeft() != this.head){
				pAnda= pAnda.getLeft();
				System.out.print( pAnda.getData() + " " );
			}
			System.out.print( pAnda.getLeft().getData() + "]");
		}
	}    
	// Insere um novo elemento na DLL na ordem
	// crescente
	public boolean insertAscending(T data){
		if (isFull()) return false;
		
		// Cria um novo no
		NodeDLL<T> novoNo = new NodeDLL<>(data, null, null);

		// se lista vazia, insere o nó no ponteiro cabeça
		// e faz o nó apontar para ele mesmo
		if (isEmpty()) {
			this.head = novoNo;
			novoNo.setRight(novoNo);
			novoNo.setLeft(novoNo);
		} else { 
		    // Caso contrário, procura posição de inserção
		    // na ordem crescente de valores
			NodeDLL<T> pAnda = this.head, pAnt = null;
			while (pAnda.getRight() != head && pAnda.getData().compareTo(data) < 0){
					pAnt = pAnda;
					pAnda = pAnda.getRight();
			}	
			// Se não andou na lista e a Key que pAnda aponta
		    // é maior que a Key a ser inserida, significa que a Key
			// a ser inserida é antes do cabeça
			// e cabeça precisa ser redefinido
			if (pAnt == null && pAnda.getData().compareTo(data) > 0){
				novoNo.setLeft(this.head.getLeft());
				this.head.getLeft().setRight(novoNo);
				this.head.setLeft(novoNo);
				novoNo.setRight(this.head);
				this.head = novoNo;
			} else {
				// Caso pAnda tenha caminhado na lista e
				// a Key que o pAnda aponta é maior do que a Key a ser inserido
				// insere o nó depois do ponteiro anterior pAnt
			 	if (pAnda.getData().compareTo(data) > 0 && pAnt != null) {	 
					pAnt.getRight().setLeft(novoNo);
					novoNo.setRight(pAnda);
					pAnt.setRight(novoNo);
					novoNo.setLeft(pAnt);
				} else {// Senão, insere a Key depois do pAnda
			    	pAnda.getRight().setLeft(novoNo);
					novoNo.setRight(pAnda.getRight());
					pAnda.setRight(novoNo);
					novoNo.setLeft(pAnda);
				}
			}
		}
		this.size++;
		return true;
	}	
	// get(int pos): retorna o "NodeDLL" que se encontra na posição "pos" da lista.	
	//   O head está na posição 1. Lista vazia retorna null. Se pos > size retorna null.
	public NodeDLL<T> get(int pos){
		if (isEmpty()) return null;
		if (pos <= 0 || pos > size) return null;
		int cont = 1;
		NodeDLL<T> pAnda = head;
		while (cont != pos){
			pAnda = pAnda.getRight();
			cont++;
		}
		return pAnda;		
	}
	// clear(): limpa a lista ligada original, deixando-a vazia
	public void clear(){
		NodeDLL<T> pAnt, pAnda = head;
		int total = size;
		int cont = 0;
		while(cont != total){
			pAnt = pAnda;  // Libera o nó
			pAnda = pAnda.getRight();
			pAnt.setRight(null);
			pAnt.setLeft(null);
			pAnt = null;
		}
		size = 0;
	    head = null;	
	}
	// search(T Data): procura pelo data em ordem crescente no nó e retorna 
	// a posição onde foi enconradoo ou -1 se não encontrou
	public int searchAscending(T data){
		// Lista vazia
		if (isEmpty()) return -1;
		
		// Procura o elemento 
		NodeDLL<T> pAnda = this.head;
		int pos = 1;
		while (pAnda.getRight() != this.head && pAnda.getData().compareTo(data) != 0){
			pAnda = pAnda.getRight();
			pos++;
		}
		
		if (pAnda.getRight() == this.head && pAnda.getData().compareTo(data) != 0){// elemento não encontrado
			return -1;
		} else { // elemento encontrado
			return pos;
		}
	}
	// remove(T data): Remove um elemento da DLL
	// retornando verdadeiro ou falso
	public boolean remove(T data) {
		// Lista vazia
		if (isEmpty()) return false;
		
		// Procura o elemento a ser removido
		NodeDLL<T> pAnda = this.head, pAnt = null;
		while (pAnda.getRight() != this.head && pAnda.getData().compareTo(data) != 0){
			pAnt = pAnda;
			pAnda = pAnda.getRight();
		}
		
		if (pAnda.getRight() == this.head && pAnda.getData().compareTo(data) != 0){// elemento não encontrado
			return false;
		} else {
			// elemento encontrado no cabeça
			if (pAnt == null && pAnda.getData().compareTo(data) == 0){
				pAnda.getLeft().setRight(pAnda.getRight());
				this.head = this.head.getRight();
				this.head.setLeft(pAnda.getLeft());
				pAnda.setLeft(null);
				pAnda.setRight(null);
			} else { // elemento encontra-se no meio da lista
			    pAnt.setRight(pAnda.getRight());
			    pAnda.getRight().setLeft(pAnt);
			    pAnda.setLeft(null);
			    pAnda.setRight(null);
			}
			pAnda = null;
			this.size--;
			return true;
		}
	}
	
	// removePos(int pos): Remove e retorna um elemento da DLL na posição pos
	public NodeDLL<T> remove(int pos) {
		// Lista vazia
		if (isEmpty()) return null;
		if (pos <= 0 || pos > size) return null;
		
		// Procura o elemento a ser removido
		NodeDLL<T> pAnda = this.head, pAnt = null;
		int cont = 1;
		while (pAnda.getRight() != this.head && cont != pos){
			pAnt = pAnda;
			pAnda = pAnda.getRight();
			cont++;
		}
		
		if (pAnda.getRight() == this.head && cont != pos){// elemento não encontrado
			return null;
		} else {
			// elemento encontrado no cabeça
			if (cont == 1){
				pAnda.getLeft().setRight(pAnda.getRight());
				this.head = this.head.getRight();
				this.head.setLeft(pAnda.getLeft());
				pAnda.setLeft(null);
				pAnda.setRight(null);
			} else { // elemento encontra-se no meio da lista
			    pAnt.setRight(pAnda.getRight());
			    pAnda.getRight().setLeft(pAnt);
			    pAnda.setLeft(null);
			    pAnda.setRight(null);
			}
			this.size--;
			return pAnda;
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		int qtde = 0;
	
	    sb.append("L: [ ");
	    NodeDLL<T> pAnda = head;
	    while (qtde != size) {
	      sb.append(pAnda.getData()).append(" ");
	      qtde++;
	      pAnda = pAnda.getRight();
	    }
	    sb.append("]\n");
	    sb.append("Qtde.: ").append(size);    
	    return sb.toString();
	}
}
