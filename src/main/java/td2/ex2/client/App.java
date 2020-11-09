package td2.ex2.client;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import td2.ex2.api.Paire;

public class App {

    public static void main(String[] args) {
        Predicate<Integer> tailleTropPetite = taille -> taille < 100;
        Predicate<Integer> tailleTropGrande = taille -> taille > 200;
        Predicate<Integer> tailleIncorrecte = taille -> tailleTropPetite.or(tailleTropGrande).test(taille);
        Predicate<Integer> tailleCorrecte = taille -> tailleTropPetite.negate().and(tailleTropGrande.negate()).test(taille);

        Predicate<Double> poidsTropLourd = poids -> poids > 150;
        Predicate<Double> poidsCorrecte = poids -> poidsTropLourd.negate().test(poids);

        // BiPredicate<Integer, Double> accesAutorise = (taille, poids) -> poidsCorrecte.test(poids).and(tailleCorrecte.test(taille));
        
        // Tests

        Paire<Integer, Double> p1 = new Paire<Integer, Double>(90, 140.0);

        System.out.println(tailleTropPetite.test(p1.fst));
        System.out.println(tailleTropGrande.test(p1.fst));
        System.out.println();
        System.out.println(tailleIncorrecte.test(p1.fst));
        System.out.println(tailleCorrecte.test(p1.fst));
        System.out.println();
        System.out.println(poidsTropLourd.test(p1.snd));
        System.out.println(poidsCorrecte.test(p1.snd));

    }
}
