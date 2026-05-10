public class Main{
    public static void main(String[] args) throws Exception {
        FilaCircular<Integer> f = new FilaCircular<>(10);
        Pilha p = new Pilha (10);
        int cont = 6;
        int digitoTIA = 2;
        while (!p.isFull()) {
            p.push( (digitoTIA +1)*cont--);
        }
        while (!f.qIsFull()) {
            f.enqueue(p.pop() % 2);
        }
        p.push(f.dequeue());
        p.push(f.dequeue());
        f.enqueue(p.pop() % 2 );
        while( !f.qIsEmpty( )){
            System.out.println(f.dequeue()); }
        System.out.println(p.toString());
        
    }
}