public class Tuple<T1, T2> {
    private T1 first;
    private T2 second;

    // Construtor para inicializar a tupla com dois elementos
    public Tuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    // Métodos getters para acessar os elementos da tupla
    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    // Método setter caso queira alterar os valores da tupla
    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    // Sobrescrita do método toString para exibir a tupla de forma legível
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}