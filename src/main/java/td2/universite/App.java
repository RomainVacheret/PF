package td2.universite;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
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

        System.out.println(moyenne.moyenne(e1));

        // Q5
        Predicate<Etudiant> naPasLaMoyennev1 = (Etudiant x) -> {
            return moyenne.moyenne(x) > 10;
        };
              
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
}
