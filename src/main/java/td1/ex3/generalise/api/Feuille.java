package td1.ex3.generalise.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Feuille<T> implements Arbre<T> {
    private final T valeur;

    public Feuille(T valeur) { this.valeur = valeur; }

    @Override
    public int taille() { return 1; }

    @Override
    public boolean contient(final T val) { return val.equals(this.valeur); }

    @Override
    public Set<T> valeurs() { return new HashSet<>(List.of(this.valeur)); }
    
    // @Override
    // public T min() { return this.valeur; }

    // @Override
    // public T max() { return this.valeur; }

    // @Override
    // public boolean estTrie() { return true; }


    // @Override
    // public T somme() { return this.valeur; }
}
