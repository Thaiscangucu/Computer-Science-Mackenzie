
public class TestePIlha {

	public static void main(String[] args) {
		PilhaChar p = new PilhaChar ();
		p.push('a');
		p.push('b');
		p.push('c');
		char x = p.topo();
		System.out.println(x);
		p.pop();
		char y = p.topo();
		System.out.println(y);
		p.pop();
		p.push( x);
		p.push( y);
		p.pop();
		System.out.println(p.toString());


}
}
