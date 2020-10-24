package td1.ex3.simple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Feuille implements Arbre {
    private final Integer valeur;

    public Feuille(Integer valeur) { this.valeur = valeur; }

    @Override
    public int taille() { return 1; }

    @Override
    public boolean contient(final Integer val) { return val.equals(this.valeur); }

    @Override
    public Integer min() { return this.valeur; }

    @Override
    public Integer max() { return this.valeur; }

    @Override
    public boolean estTrie() { return true; }

    @Override
    public Set<Integer> valeurs() { return new HashSet<>(List.of(this.valeur)); }

    @Override
    public Integer somme() { return this.valeur; }
}
