package td1.ex2;

import java.lang.StringBuilder;

class Paire<T, U> {
    private final T element1;
    private final U element2;

    public Paire(T element1, U element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T fst() {
        return this.element1;
    }

    public U snd() {
        return this.element2;
    }

    public Paire<T, U> changeFst(T element) {
        return new Paire(element, this.element2);
    }

    public Paire<T, U> changeSnd(U element) {
        return new Paire(this.element1, element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.element1)
            .append(",")
            .append(this.element2)
            .append(") :: Paire[")
            .append(this.element1.getClass())
            .append(", ")
            .append(this.element2.getClass())
            .append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        Paire p = new Paire(1, "un");
        System.out.println(p.toString());
        p = p.changeFst(1.0);
        System.out.println(p.toString());
        p = p.changeSnd(new Paire(1, "un"));
        System.out.println(p.toString());
    }
}