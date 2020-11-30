package td3.commandes;

import static td3.commandes.Categorie.INTERMEDIAIRE;
import static td3.commandes.Categorie.NORMAL;
import static td3.commandes.Categorie.REDUIT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import td3.paires.Paire;

public class DAO {
    private List<Commande> commandes;

    private DAO(Commande c1, Commande ...cs) {
        commandes = new ArrayList<>();
        commandes.add(c1);
        commandes.addAll(List.of(cs));
    }

    public static DAO instance = null;

    public static final DAO instance() {
        if (instance == null) {
            Produit camembert = new Produit("Camembert", 4.0, NORMAL);
            Produit yaourts = new Produit("Yaourts", 2.5, INTERMEDIAIRE);
            Produit masques = new Produit("Masques", 25.0, REDUIT);
            Produit gel = new Produit("Gel", 5.0, REDUIT);
            Produit tournevis = new Produit("Tournevis", 4.5, NORMAL);
            //
            Commande c1 = new Commande()
                .ajouter(camembert, 1)
                .ajouter(yaourts, 6);
            Commande c2 = new Commande()
                .ajouter(masques, 2)
                .ajouter(gel, 10)
                .ajouter(camembert, 2)
                .ajouter(masques, 3);
            //
            instance = new DAO(c1,c2);
        }
        return instance;
    }

    /**
     * liste de toutes les commandes
     */
    public List<Commande> commandes() { return commandes; }

    /**
     * ensemble des différents produits commandés
     */
    public Set<Produit> produits() {
        // return commandes.stream()
        //         .flatMap(c -> c.lignes().stream())
        //         .map(Paire::fst)
        //         .collect(Collectors.toSet());
        Set<Produit> prds = new HashSet<>();
        for(Commande c: commandes) {
            for(Paire<Produit, Integer> p: c.lignes()) {
                prds.add(p.fst());
            }
        }

        return prds;
    }

    /**
     * liste des commandes vérifiant un prédicat
     */
    public List<Commande> selectionCommande(Predicate<Commande> p) {
        // return commandes.stream()
        //     .filter(p)
        //     .collect(Collectors.toList());
        List<Commande> cmds = new ArrayList<>();
        for(Commande c: commandes) {
            if(p.test(c)) {
                cmds.add(c);
            }
        }
        return cmds;
    }

    /**
     * liste des commandes dont au moins une ligne vérifie un prédicat
     */
    public List<Commande> selectionCommandeSurExistanceLigne(Predicate<Paire<Produit,Integer>> p) {
        // return commandes.stream()
        //     .filter(c -> c.lignes().stream().anyMatch(p))
        //     .collect(Collectors.toList());

        List<Commande> cmds = new ArrayList<>();
        for(Commande c: commandes) {
            boolean flag = false;
            for(Paire<Produit, Integer> pp: c.lignes()) {
                if(p.test(pp)) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
               cmds.add(c);
            }
        }

        return cmds;
    }

    /**
     * ensemble des différents produits commandés vérifiant un prédicat
     */
    public Set<Produit> selectionProduits(Predicate<Produit> p) {
        // return produits()
        //     .stream()
        //     .filter(p)
        //     .collect(Collectors.toSet());

        Set<Produit> prds = new HashSet<>();
        for(Produit pp: produits()) {
            if(p.test(pp)) {
                prds.add(pp);
            }
        }
        return prds;
    }

}
