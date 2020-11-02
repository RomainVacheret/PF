package td1.ex3.generalise.api.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import td1.ex3.generalise.api.sommes.Sommable;

public class Noeud<T extends Sommable<T>> implements Arbre<T> {
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
    
    @Override
    public T somme() {
        if(fils == null || fils.isEmpty())
            return null;
        T rtr = fils.get(0).somme();
        for(int i = 0; i < fils.size(); i++){
            rtr = rtr.sommer(fils.get(i).somme());
        }
        return rtr;
    }

    @Override
    public T min() {
        if(fils == null || fils.isEmpty())
            return null;
        T rtr = fils.get(0).somme();
        for(int i = 0; i < fils.size(); i++){
            if(rtr.compareTo(fils.get(i).min()) > 0) {
                rtr = fils.get(i).min();
            }
        }
        return rtr;
    }

    @Override
    public T max() {
        if(fils == null || fils.isEmpty())
            return null;
        T rtr = fils.get(0).somme();
        for(int i = 0; i < fils.size(); i++){
            if(rtr.compareTo(fils.get(i).max()) < 0) {
                rtr = fils.get(i).max();
            }
        }
        return rtr;
    }

    private boolean condition1() {
        return this.fils.stream().allMatch(Arbre::estTrie);
    }

    private boolean condition2() {
        boolean rtr = true;
        for(int i = 0; i < this.fils.size() - 1; i++) {
            final Arbre<T> f1 = this.fils.get(i);
            final Arbre<T> f2 = this.fils.get(i + 1);
            if(f1.max().compareTo(f2.min()) > 0){
                rtr = false;
                break;
            }
        }
        return rtr;
    }
    
    @Override
    public boolean estTrie() { return this.condition1() && this.condition2(); }
}
