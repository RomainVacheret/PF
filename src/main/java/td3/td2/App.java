/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package td3.td2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import td2.universite.Annee;
import td2.universite.Etudiant;
import td2.universite.Matiere;
import td2.universite.UE;

public class App {

    //
    // QUESTION 1.1
    //

    public static void question1_1() {
        Somme<Integer> sommeInteger = (x, y) -> x + y;
        Somme<Double> sommeDouble = (x, y) -> x + y;
        Somme<Long> sommeLong = (x, y) -> x + y;
        Somme<String> sommeString = (x, y) -> x + y;
        System.out.println(sommeInteger.somme(1, 2));
        System.out.println(sommeDouble.somme(1.0, 2.0));
        System.out.println(sommeLong.somme(1L, 2L));
        System.out.println(sommeString.somme("1", "2"));
    }

    //
    // QUESTION 1.2
    //

    public static void question1_2() {
        final List<String> l1 = List.of("Hello", "World");
        final Map<String, Integer> m1 = Map.of("Alice", 19, "Bob", 21, "Charles", 17);
        ToString<List<String>> l2s = l -> {
            StringBuffer rtr = new StringBuffer();
            boolean premier = true;
            for (String e : l) {
                if (!premier)
                    rtr.append(", ");
                else
                    premier = false;
                rtr.append(e);
            }
            return rtr.toString();
        };
        ToString<Map<String, Integer>> m2s = m -> {
            final StringBuffer rtr = new StringBuffer();
            boolean premier = true;
            for (final Entry<String, Integer> e : m.entrySet()) {
                if (!premier)
                    rtr.append(", ");
                else
                    premier = false;
                rtr.append(String.format("%s: %d", e.getKey(), e.getValue()));
            }
            return rtr.toString();
        };
        System.out.println(l2s.convert(l1));
        System.out.println(m2s.convert(m1));
    }

    //
    // QUESTION 2
    //

    public static class Paire<T,U> {
        public T fst;
        public U snd;
        public Paire(T fst, U snd) {
            this.fst = fst;
            this.snd = snd;
        }
        @Override public String toString() { return String.format("(%s,%s)",fst.toString(),snd.toString()); }
    }

    public static <T> Predicate<T> conjonction(List<Predicate<T>> conditions) {
        Predicate<T> pred = x -> true;
        for (Predicate<T> condition : conditions) {
            pred = pred.and(condition);
        }
        return pred;
    }

    public static <T> Predicate<T> disjunction(List<Predicate<T>> conditions) {
        Predicate<T> pred = x -> false;
        for (Predicate<T> condition : conditions) {
            pred = pred.or(condition);
        }
        return pred;
    }

    // rappel : on n'a pas encore vu les Stream (on reprendra la méthode plus tard)
    public static <T> List<T> filtragePredicatif(List<Predicate<T>> conditions, List<T> elements) {
        List<T> rtr = new ArrayList<>();
        // construction du prédicat conjonctif
        Predicate<T> pred = x -> true; // pas de conditions = true
        for (Predicate<T> condition : conditions) {
            pred = pred.and(condition);
        }
        // on peut aussi utiliser :
        // Predicate<T> pred = conjunction(conditions);
        //
        // filtrage des clients
        for (T element : elements) {
            if(pred.test(element)) {
                rtr.add(element);
            }
        }
        return rtr;
    }

    public static void question2() {
        Predicate<Integer> tropPetit = x -> x < 100;
        Predicate<Integer> tropGrand = x -> x > 200;
        Predicate<Integer> mauvaiseTaille = tropPetit.or(tropGrand);
        Predicate<Integer> bonneTaille = mauvaiseTaille.negate();
        Predicate<Double> tropLourd = x -> x > 150.0;
        Predicate<Double> bonPoids = tropLourd.negate(); // not(tropLourd);
        Predicate<Paire<Integer,Double>> accesPossible = x -> bonneTaille.test(x.fst) && bonPoids.test(x.snd);
        //
        Predicate<Paire<Integer,Double>> tropPetit2 = x -> x.fst < 100;
        Predicate<Paire<Integer,Double>>
        tropGrand2 = x -> x.fst > 200;
        Predicate<Paire<Integer,Double>>
        mauvaiseTaille2 = tropPetit2.or(tropGrand2);
        Predicate<Paire<Integer,Double>>
        bonneTaille2 = mauvaiseTaille2.negate();
        Predicate<Paire<Integer,Double>> tropLourd2 = x -> x.snd > 150.0;
        Predicate<Paire<Integer,Double>>
        bonPoids2 = tropLourd2.negate();
        Predicate<Paire<Integer,Double>> accesPossible2 = bonneTaille2.and(bonPoids2);
        //
        List<Paire<Integer,Double>> clients = List.of(
            new Paire<Integer,Double>(95,100.0),
            new Paire<Integer,Double>(205,100.0),
            new Paire<Integer,Double>(150,155.0),
            new Paire<Integer,Double>(150,75.0)
        );
        //
        clients.forEach(client ->
            System.out.println(String.format("%d %f -> %s", client.fst, client.snd, accesPossible.test(client)))
        );
        clients.forEach(client ->
            System.out.println(String.format("%d %f -> %s", client.fst, client.snd, accesPossible2.test(client)))
        );
        //
        List<Predicate<Paire<Integer,Double>>> conditionsAcces = List.of(bonneTaille2, bonPoids2);
        List<Predicate<Paire<Integer,Double>>> conditionsAllegees = List.of(bonneTaille2);
        filtragePredicatif(conditionsAcces, clients).forEach(x -> System.out.println(x));
        filtragePredicatif(conditionsAllegees, clients).forEach(x -> System.out.println(x));
    }

    //
    // QUESTION 3
    //

    public final static String ENTETE_TOUS = "TOUS LES ETUDIANTS";

    public static void afficheSi(String entete, Predicate<Etudiant> filtre, Annee a) {
        System.out.println(String.format("\n**%s\n", entete));
        // on n'a pas vu les Streams (et donc filter) mais on a vu les Consumers
        a.etudiants().forEach(e -> {
            if (filtre.test(e))
                System.out.println(e);
        });
    }

    public static void afficheSiv2(String entete, Predicate<Etudiant> filtre, Annee a,
            Function<Etudiant, String> repr) {
        System.out.println(String.format("\n**%s\n", entete));
        // on n'a pas vu les Streams (et donc filter) mais on a vu les Consumers
        a.etudiants().forEach(e -> {
            if (filtre.test(e))
                System.out.println(repr.apply(e));
        });
    }

    // on reecrira plus tard avec les Streams
    public static final Predicate<Etudiant> aDEF = e -> {
        for (UE ue : e.annee().ues()) {
            for (Matiere m : ue.ects().keySet()) {
                if (!e.notes().containsKey(m)) {
                    return true;
                }
            }
        }
        return false;
    };

    // on reecrira plus tard avec les Streams
    public static final double PLANCHER = 6.0;
    public static final Predicate<Etudiant> aNoteEliminatoire = e -> {
        for (Double n : e.notes().values()) {
            if (n < PLANCHER) {
                return true;
            }
        }
        return false;
    };

    // noter que doit être après aDEF (ou aDEF importé)
    public static final Function<Etudiant, Double> moyenne = e -> {
        if (aDEF.test(e))
            return null; // on verra mieux plus tard
        int credits = 0;
        double somme = 0.0;
        // on verra avec Streams plus tard
        for (UE ue : e.annee().ues()) {
            for (Entry<Matiere, Integer> ects : ue.ects().entrySet()) {
                credits += ects.getValue();
                double note = e.notes().get(ects.getKey());
                somme += ects.getValue() * note;
            }
        }
        return somme / credits;
    };

    public static final Function<Etudiant, Double> moyenneIndicative = e -> {
        int credits = 0;
        double somme = 0.0;
        // on verra avec Streams plus tard
        for (UE ue : e.annee().ues()) {
            for (Entry<Matiere, Integer> ects : ue.ects().entrySet()) {
                credits += ects.getValue();
                double note;
                if (e.notes().containsKey(ects.getKey()))
                    note = e.notes().get(ects.getKey());
                else
                    note = 0.0;
                somme += ects.getValue() * note;
            }
        }
        return somme / credits;
    };

    // plante si moyenne ne peut pas être calculée (on réécrira plus tard)
    public static final Predicate<Etudiant> naPasLaMoyennev1 = e -> moyenne.apply(e) < 10.0;

    // ne plante pas mais teste explicitement null (on réécrira plus tard)
    public static final Predicate<Etudiant> naPasLaMoyennev2 = e -> {
        Double m = moyenne.apply(e);
        return ((m == null) || (m < 10.0));
    };

    // permet différents calculs de moyenne
    public static final Function<Function<Etudiant, Double>, Predicate<Etudiant>> naPasLaMoyenneGeneralise = f -> e -> {
        Double m = f.apply(e);
        return ((m == null) || (m < 10.0));
    };

    // plante si la moyenne ne peut pas être calculée (naPasLaMoyenne évalué avant
    // aDef)
    public static final Predicate<Etudiant> session2v1 = naPasLaMoyennev1.or(aDEF).or(aNoteEliminatoire);

    // ne plante pas (aDEF évalué avant naPasLaMoyenne)
    public static final Predicate<Etudiant> session2v2 = aDEF.or(aNoteEliminatoire).or(naPasLaMoyennev1);

    public static void question3() {
        Matiere m1 = new Matiere("MAT1");
        Matiere m2 = new Matiere("MAT2");
        UE ue1 = new UE("UE1", Map.of(m1, 2, m2, 2));
        Matiere m3 = new Matiere("MAT3");
        UE ue2 = new UE("UE2", Map.of(m3, 1));
        Annee a1 = new Annee(Set.of(ue1, ue2));
        Etudiant e1 = new Etudiant("39001", "Alice", "Merveille", a1);
        e1.noter(m1, 12.0);
        e1.noter(m2, 14.0);
        e1.noter(m3, 10.0);
        System.out.println(e1);
        Etudiant e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 14.0);
        e2.noter(m3, 14.0);
        Etudiant e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);
        afficheSi(ENTETE_TOUS, e -> true, a1);
        afficheSi("ETUDIANTS DEF", aDEF, a1);
        afficheSi("ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire, a1);
        // afficheSi("ETUDIANTS SOUS LA MOYENNE (v1)", naPasLaMoyennev1, a1);
        afficheSi("ETUDIANTS SOUS LA MOYENNE (v2)", naPasLaMoyennev2, a1);
        // afficheSi("ETUDIANTS EN SESSION 2 (v1)", session2v1, a1);
        afficheSi("ETUDIANTS EN SESSION 2 (v2)", session2v2, a1);
        //
        afficheSiv2(ENTETE_TOUS, e -> true, a1, Etudiant::toString);
        afficheSiv2(ENTETE_TOUS, e -> true, a1, e -> {
            Double m = moyenne.apply(e);
            String ms = (m == null) ? "défaillant" : String.format("%.2f", m);
            return String.format("%s %s : %s", e.prenom(), e.nom(), ms);
        });
        afficheSiv2(ENTETE_TOUS, e -> true, a1,
                e -> String.format("%s %s : %.2f", e.prenom(), e.nom(), moyenneIndicative.apply(e)));
        afficheSiv2("TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralise.apply(moyenneIndicative),
                a1, e -> String.format("%s %s : %.2f", e.prenom(), e.nom(), moyenneIndicative.apply(e)));
    }

    public static void main(String[] args) {
        question1_1();
        question1_2();
        question2();
        question3();
    }
}
