package td1.ex3.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noeud implements Arbre {
    private final List<Arbre> fils;

    public Noeud() { this.fils = new ArrayList<>(); }
    public Noeud(List<Arbre> noeuds) { this.fils = noeuds; }

    @Override
    public int taille() { return this.fils.stream().map(Arbre::taille).reduce(0, Integer::sum); }

    @Override
    public Integer somme() { return this.fils.stream().map(Arbre::somme).reduce(0, Integer::sum); }

    @Override
    public Integer min() { return this.fils.stream().map(Arbre::min).min(Integer::min).get(); }

    @Override
    public Integer max() { return this.fils.stream().map(Arbre::min).min(Integer::min).get(); }
    
    @Override
    public boolean estTrie() { return this.fils.stream().allMatch(Arbre::estTrie); }

    @Override
    public boolean contient(final Integer val) { return this.fils.stream().anyMatch(unFils -> unFils.contient(val)); }

    @Override
    public Set<Integer> valeurs() {
        Set<Integer> fusion = new HashSet<>();
        this.fils.forEach(unFils -> fusion.addAll(unFils.valeurs()));
        return fusion;
    }
}
