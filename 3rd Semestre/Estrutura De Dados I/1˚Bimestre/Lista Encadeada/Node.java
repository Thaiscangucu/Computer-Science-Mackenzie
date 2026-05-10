public class Node {
	private int id;
	private Node prox;
	
	public Node() {
		this(0, null);
	}
	
	public Node(int id, Node prox) {
		this.id = id;
		this.prox = prox;
	}

	public Node getProx() { return prox; };
	public int getId(){ return id; };
	
	public void setProx(Node prox) { this.prox = prox; };
	public void setId(int id) { this.id = id;	};	
	
}
