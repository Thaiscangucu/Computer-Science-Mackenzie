public class LinkedList {
	private Node head;
	
	public LinkedList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return getHead() == null;
	}
	
	public boolean isFull() {
		Node aux = new Node();
		return aux == null;
	}
	
	public Node getHead() {
		return head;
	}
	
	public boolean insertTail(int id){
		Node aux, pAnda;
	    if (!isFull()){
	      aux = new Node(id, null);
	      if (isEmpty()){ // Lista está vazia
	        head = aux;
	      }else { // Insere no final e atualiza o ponteiro
	        pAnda = head;  // Ponteiro que anda até o final da lista 
	        while (pAnda.getProx() != null)
	           pAnda = pAnda.getProx();
	        pAnda.setProx( aux );
	      }
		  return true;
	    }
	    else return false; 
	};
	
	public Node search(int id){
		Node pAnda;
		
	    if (isEmpty()) {
			return null; // Lista vazia
	    }else{
	      pAnda = head;
	      // procura a posição do elemento na lista
	      while ((pAnda != null) && (pAnda.getId() != id))
	        pAnda = pAnda.getProx();
	      return pAnda; // Retorna a referência para o elemento	encontrado
	    }
	}
	
	public boolean remove(int id){
		Node pAnt = null, pAnda;
	    if (isEmpty()) return false; // Lista vazia
	    else{
	      pAnda = head;
	      // procura a posição do elemento na lista
	      while ((pAnda != null) && (pAnda.getId() != id)){
	        pAnt = pAnda;
	        pAnda = pAnda.getProx();
	      }
	      if (pAnda == null) return false; // Se não encontrou o elemento
	      // Se elemento encontrado remove da lista
	      else {
	      	// se o elemento encontrado está na cabeça da lista
	      	if ((head == pAnda)) {
			  head = pAnda.getProx();
		    } else{ // remove elemento do meio/fim
		    	pAnt.setProx(pAnda.getProx());
			}
	      	pAnda = null;
	      	return true;   
	      }
	    }
	}
	
	public void print(){
		Node pAnda; 
	    System.out.print("L: [ ");
	    pAnda = head;
	    while (pAnda != null) {
	      System.out.print(pAnda.getId()+" ");
	      pAnda = pAnda.getProx();
	    }
	    System.out.print("]\n");
	}
	
	public boolean insertHead(int id){
		Node aux;
	    if (!isFull()){
	      aux = new Node(id, null);
	      if (isEmpty()){ // Lista está vazia
	        head = aux;
	      }else { // Insere no começo e atualiza o ponteiro
	      	aux.setProx(head);
	      	head = aux;
	      }
	      return true;
	    }
	    else return false; 
	};
	
	public void clear(){
		Node pAnt, pAnda = head;
		
		while(pAnda != null){
			pAnt = pAnda;  // Libera o nó
			pAnda = pAnda.getProx();
			pAnt.setProx(null);
			pAnt = null;
		}
		head = null;	
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		int qtde = 0;
		sb.append("\n[Lista]\n");
	
	    sb.append("L: [ ");
	    Node pAnda = head;
	    while (pAnda != null) {
	      sb.append(pAnda.getId()+" ");
	      qtde++;
	      pAnda = pAnda.getProx();
	    }
	    sb.append("]\n");
	    
	    sb.append("Qtde.: " + qtde);
	    sb.append("\n");
	    
	    return sb.toString();
	}
	
}
