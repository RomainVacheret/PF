package td1.ex3.generalise.api.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import td1.ex3.generalise.api.sommes.Sommable;

public class Noeud<T extends Sommable<T>> implements Arbre<T> {
    private final List<Arbre<T>> fils;
    private Class<T> x;

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
    
    @Override
    public T somme() {
        if(fils == null || fils.size() == 0)
            return null;
        T rtr = fils.get(0).somme();
        for(int i = 0; i < fils.size(); i++){
            rtr = rtr.sommer(fils.get(i).somme());
        }
        return rtr;
    }

    @Override
    public T min() { 
        // if(this.taille() == 0) {
        //     return null;
        // }
        // Integer min = Integer.MAX_VALUE;
        // for(Arbre<T> unfils: this.fils) {
        //     Integer tmpMin = unfils.min();
        //     if(tmpMin < min) {
        //         min = tmpMin;
        //     }
        // }
        // return min;
        return null;
    }

    @Override
    public T max() { 
        // if(this.taille() == 0) {
        //     return null;
        // }
        // Integer max = Integer.MIN_VALUE;
        // for(Arbre<T> unfils: this.fils) {
        //     Integer tmpMax = unfils.max();
        //     if(tmpMax > max) {
        //         max = tmpMax;
        //     }
        // }
        // return max;
        return null;
    }
    
    // @Override
    // public boolean estTrie() { return this.fils.stream().allMatch(Arbre::estTrie); }
}
