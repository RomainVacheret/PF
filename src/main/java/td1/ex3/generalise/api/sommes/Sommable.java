package td1.ex3.generalise.api.sommes;

public interface Sommable<T> extends Comparable<T> {
    T sommer(T t);
}
