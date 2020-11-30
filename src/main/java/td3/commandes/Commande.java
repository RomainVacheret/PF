package td3.commandes;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;

import td3.paires.Paire;

public class Commande {
    private List<Paire<Produit, Integer>> lignes;

    public Commande() {
        this.lignes = new ArrayList<>();
    }

    // Q2.1
    public static final Function<Paire<Produit, Integer>, String> formatteurLigne = paire -> String.format("%s x%d%n", paire.fst(), paire.snd());

    public Commande ajouter(Produit p, int q) {
        lignes.add(new Paire<>(p, q));
        return this;
    }

    public List<Paire<Produit, Integer>> lignes() {
        return lignes;
    }

    // Q2.2
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Commande\n");
        str.append(lignes.stream().map(formatteurLigne).collect(Collectors.joining()));
        return str.toString();
    }

    // Q2.3
    public String toString(Function<Paire<Produit, Integer>, String> func) {
        StringBuilder sb = new StringBuilder();
        sb.append("Commandes\n");
        sb.append(lignes.stream().map(func).collect(Collectors.joining()));
        return sb.toString();
    }

    /**
     * cumule les lignes en fonction des produits
     */
    public Commande normaliser() {
        Map<Produit, Integer> lignesCumulees = new HashMap<>();
        for (Paire<Produit, Integer> ligne : lignes) {
            Produit p = ligne.fst();
            int qte = ligne.snd();
            if (lignesCumulees.containsKey(ligne.fst())) {
                lignesCumulees.put(p, lignesCumulees.get(p) + qte);
            } else {
                lignesCumulees.put(p, qte);
            }
        }
        Commande commandeNormalisee = new Commande();
        for (Produit p : lignesCumulees.keySet()) {
            commandeNormalisee.ajouter(p, lignesCumulees.get(p));
        }
        return commandeNormalisee;
    }

    // Q2.4
    public Double cout(Function<Paire<Produit, Integer>, Double> calculLigne) {
        // double rtr = 0;
        // for (Paire<Produit, Integer> l : normaliser().lignes) {
        //     rtr += calculLigne.apply(l);
        // }
        // return rtr;
        return normaliser().lignes().stream().map(calculLigne).reduce(0.0, (sub, el) -> sub + el);
    }

    public String affiche(Function<Paire<Produit, Integer>, Double> calculLigne) {
        Commande c = this.normaliser();
        final String HLINE = "+------------+------------+-----+------------+--------+------------+\n";
        StringBuilder str = new StringBuilder();
        str.append("\n\nCommande\n");
        str.append(HLINE);
        str.append("+ nom        + prix       + qté + prix ht    + tva    + prix ttc   +\n");
        str.append(HLINE);
        for (Paire<Produit, Integer> ligne : c.lignes) {
            str.append(String.format("+ %10s + %10.2f + %3d + %10.2f + %5.2f%% + %10.2f +\n", ligne.fst(), // nom
                    ligne.fst().prix(), // prix unitaire
                    ligne.snd(), // qté
                    ligne.fst().prix() * ligne.snd(), // prix ht
                    ligne.fst().cat().tva() * 100, // tva
                    calculLigne.apply(ligne)));
        }
        str.append(HLINE);
        str.append(String.format("Total : %10.2f", c.cout(calculLigne)));
        return str.toString();
    }

}
