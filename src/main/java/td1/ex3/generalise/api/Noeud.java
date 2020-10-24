package td1.ex3.generalise.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noeud<T> implements Arbre<T> {
    private final List<Arbre<T>> fils;

    public Noeud() { this.fils = new ArrayList<>(); }
    public Noeud(List<Arbre<T>> fils) { this.fils = fils; }

    @Override
    public int taille() { return this.fils.stream().map(Arbre<T>::taille).reduce(0, Integer::sum); }


    @Override
    public boolean contient(final T val) { return this.fils.stream().anyMatch(unFils -> unFils.contient(val)); }

    @Override
    public Set<T> valeurs() {
        Set<T> fusion = new HashSet<>();
        this.fils.forEach(unFils -> fusion.addAll(unFils.valeurs()));
        return fusion;
    }
    // @Override
    // public T somme() { return null; }

    // @Override
    // public T min() { 
    //     return null;
    // }

    // @Override
    // public T max() { 
    //     return null;
    // }
    
    // @Override
    // public boolean estTrie() { return this.fils.stream().allMatch(Arbre::estTrie); }
}
