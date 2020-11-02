package td1.ex3.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noeud implements Arbre {
    private final List<Arbre> fils;

    public Noeud() { this.fils = new ArrayList<>(); }
    public Noeud(List<Arbre> fils) { this.fils = fils; }

    @Override
    public int taille() { return this.fils.stream().map(Arbre::taille).reduce(0, Integer::sum); }

    @Override
    public Integer somme() { return this.taille() != 0 ? this.fils.stream().map(Arbre::somme).reduce(0, Integer::sum) : null; }

    @Override
    public Integer min() { 
        if(this.taille() == 0) {
            return null;
        }
        Integer min = Integer.MAX_VALUE;
        for(Arbre unfils: this.fils) {
            Integer tmpMin = unfils.min();
            if(tmpMin < min) {
                min = tmpMin;
            }
        }
        return min;
    }

    @Override
    public Integer max() { 
        if(this.taille() == 0) {
            return null;
        }
        Integer max = Integer.MIN_VALUE;
        for(Arbre unfils: this.fils) {
            Integer tmpMax = unfils.max();
            if(tmpMax > max) {
                max = tmpMax;
            }
        }
        return max;
    }

    private boolean condition1() {
        return this.fils.stream().allMatch(Arbre::estTrie);
    }

    private boolean condition2() {
        // FIX
        boolean rtr = true;
        for(int i = 0; i < this.fils.size() - 1; i++) {
            final Arbre f1 = this.fils.get(i);
            final Arbre f2 = this.fils.get(i + 1);
            if(f1.max() > f2.min()){
                rtr = false;
                break;
            }
        }
        return rtr;
    }
    
    @Override
    public boolean estTrie() { return this.condition1() && this.condition2(); }

    @Override
    public boolean contient(final Integer val) { return this.fils.stream().anyMatch(unFils -> unFils.contient(val)); }

    @Override
    public Set<Integer> valeurs() {
        Set<Integer> fusion = new HashSet<>();
        this.fils.forEach(unFils -> fusion.addAll(unFils.valeurs()));
        return fusion;
    }
}
