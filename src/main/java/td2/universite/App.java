package td2.universite;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
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
        // System.out.println(e1);
        Etudiant e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 14.0);
        e2.noter(m3, 14.0);
        Etudiant e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);

        // Q1
        Predicate<Etudiant> test = (Etudiant x) -> x.annee().etudiants().contains(x);
        App.afficheSI("** TOUS LES ETUDIANTS", test, a1);

        // Q2
        Predicate<Etudiant> aDef = (Etudiant x) -> {
            for (UE ue : x.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if(!x.notes().containsKey(matiere)){
                        return true;
                    }
                }
            }
            return false;
        };

        App.afficheSI("** ETUDIANTS DEFAILLANTS", aDef, a1);

        // Q3
        Predicate<Etudiant> aNoteEliminatoire = (Etudiant x) -> {
            boolean rtr = false;
            for(double note : x.notes().values()){
                if(note < 6) {
                    rtr = true;
                    break;
                }
            }
            return rtr;
        };

        App.afficheSI("** ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire, a1);  

        // Q4
        Moyenne moyenne = new Moyenne(){
            @Override
            public Double moyenne(Etudiant x) {
                Double rtr = null;
                double totalNotes = 0;
                double totalCoeffs = 0;
                if(!aDef.test(x)){
                    for(UE ue: x.annee().ues()){
                        for(Entry<Matiere, Integer> ects : ue.ects().entrySet()) {
                            Matiere matiere = ects.getKey();
                            Integer credits = ects.getValue();
                            totalNotes += x.notes().get(matiere) * credits;
                            totalCoeffs += credits;
                        }
                    }
                    if(totalCoeffs != 0) {
                        rtr = totalNotes / totalCoeffs;
                    }
                }
                return rtr;
            }
        }; 


        // Q5
        // Il ne faut pas l'utiliser sur un etudiant defaillant puisque ce cas n'est pas gere
        // Un NullPointerException sera leve
        Predicate<Etudiant> naPasLaMoyennev1 = (Etudiant x) -> {
            return moyenne.moyenne(x) < 10;
        };

        // Q6
        Predicate<Etudiant> naPasLaMoyennev2 = (Etudiant x) -> {
            boolean rtr = false;
            if(aDef.test(x)){
                rtr = true;
            } else {
                rtr = naPasLaMoyennev1.test(x);
            }
            return rtr;
        };

        App.afficheSI("** ETUDIANTS SOUS LA MOYENNE (v2)", naPasLaMoyennev2, a1);
            
        // Q7
        // Il faut que la verification de la defaillance soit faite avant celle de la moyenne puisqu'elle ne 
        // prend pas en compte ce cas ci
        Predicate<Etudiant> session2v1 = (Etudiant x) -> aDef.or(naPasLaMoyennev1).or(aNoteEliminatoire).test(x);

        App.afficheSI("** ETUDIANTS EN SESSION 2", session2v1, a1);

        // Q8
        Affichage aff1 = new Affichage(){
            @Override
            public String affichage(Etudiant x) {
                return x.toString();
            }
        };

        Affichage aff2 = new Affichage(){
            @Override
            public String affichage(Etudiant x) {
                Double moy = moyenne.moyenne(x);
                return String.format("%s %s : %s", x.prenom(), x.nom(), moy != null ? moy.toString() : "défaillant");
            }
        };

        App.afficheSIv2("** TOUS LES ETUDIANTS (v2)", test, a1, aff1);
        App.afficheSIv2("** TOUS LES ETUDIANTS (v3)", test, a1, aff2);


        // Q9

        Moyenne moyenneIndicative = new Moyenne(){
            @Override
            public Double moyenne(Etudiant x) {
                Double rtr = null;
                double totalNotes = 0;
                double totalCoeffs = 0;
                for(UE ue: x.annee().ues()){
                    for(Entry<Matiere, Integer> ects : ue.ects().entrySet()) {
                        Matiere matiere = ects.getKey();
                        Integer credits = ects.getValue();
                        if(x.notes().keySet().contains(matiere)){
                            totalNotes += x.notes().get(matiere) * credits;
                        } 
                            totalCoeffs += credits;
                        
                    }
                }
                if(totalCoeffs != 0) {
                    rtr = totalNotes / totalCoeffs;
                }
                return rtr;
            }
        };

        Affichage aff3 = new Affichage(){
            @Override
            public String affichage(Etudiant x) {
                Double moy = moyenneIndicative.moyenne(x);
                return String.format("%s %s : %.2f", x.prenom(), x.nom(), moy);
            }
        };
        
        App.afficheSIv2("** TOUS LES ETUDIANTS (v4)", x -> true, a1, aff3);

        
    }

    public static void afficheSI(String enTete, Predicate<Etudiant> predicat, Annee annee){
        // Boucle for
        // System.out.println(enTete);
        // for(Etudiant e: annee.etudiants()) {
        //     if(predicat.test(e, annee)){
        //         System.out.println(e.toString());
        //     }
        // }

        // forEach
        System.out.println(enTete);
        annee.etudiants().forEach(e -> {
            if(predicat.test(e)){
                System.out.println(e.toString());
            }
        });
        
    }

    public static void afficheSIv2(String enTete, Predicate<Etudiant> predicat, Annee annee, Affichage a) {
        System.out.println(enTete);
        annee.etudiants().forEach(e -> {
            if(predicat.test(e)){
                System.out.println(a.affichage(e));
            }
        });
    }
}
