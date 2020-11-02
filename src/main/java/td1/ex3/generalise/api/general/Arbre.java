package td1.ex3.generalise.api.general;

import java.util.Set;

import td1.ex3.generalise.api.sommes.Sommable;

public interface Arbre<T extends Sommable<T>> {
    int taille();
    boolean contient(final T val);
    Set<T> valeurs();
    T somme();
    T min();
    T max();
    boolean estTrie();
}
