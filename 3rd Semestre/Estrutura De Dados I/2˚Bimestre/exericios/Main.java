


public class Main{
    public static void main(String[] args){
        CircleLinkedList <Integer> listaCircular = new CircleLinkedList<>();
        CircleLinkedList <Integer> listaCircular2 = new CircleLinkedList<>();

        listaCircular.addLast(1);
        listaCircular.addLast(2);
        listaCircular.addLast(3);
        listaCircular.addLast(4);
        listaCircular.addLast(5);
        listaCircular.addLast(6);
        listaCircular.addLast(7);
        listaCircular.addLast(8);
        listaCircular.addLast(9);
        listaCircular.addLast(10);
        
        System.out.print(listaCircular.toString());
        listaCircular.remove(1);
        System.out.print(listaCircular.toString());


    }

}