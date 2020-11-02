package td1.ex3.generalise.api.general;

import java.util.Set;

import td1.ex3.generalise.api.sommes.Sommable;

public class Feuille<T extends Sommable<T>> implements Arbre<T> {
    private final T valeur;

    public Feuille(T valeur) { this.valeur = valeur; }

    @Override
    public int taille() { return 1; }

    @Override
    public boolean contient(final T val) { return val.equals(this.valeur); }

    @Override
    public Set<T> valeurs() { return Set.of(this.valeur); }
    
    @Override
    public T min() { return this.valeur; }

    @Override
    public T max() { return this.valeur; }

    @Override
    public T somme() { return this.valeur; }

    @Override
    public boolean estTrie() { return true; }
}
