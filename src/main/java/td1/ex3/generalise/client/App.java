package td1.ex3.generalise.client;

import java.util.List;

import td1.ex3.generalise.api.general.Arbre;
import td1.ex3.generalise.api.general.Feuille;
import td1.ex3.generalise.api.general.Noeud;
import td1.ex3.generalise.api.sommes.Chaine;
import td1.ex3.generalise.api.sommes.Entier;

public class App {
    public static void main(String[] args) {

        Entier e1 = new Entier(1);
        Entier e2 = new Entier(2);
        Entier e3 = new Entier(3);
        System.out.println(e1.sommer(e2.sommer(e3)));

        Chaine c1 = new Chaine("a");
        Chaine c2 = new Chaine("b");
        Chaine c3 = new Chaine("c");
        System.out.println(c1.sommer(c2.sommer(c3)));

        final Arbre<Entier> f1 = new Feuille<>(e1);
        final Arbre<Entier> f2 = new Feuille<>(e2);
        final Arbre<Entier> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Entier> f3 = new Feuille<>(e3);
        final Arbre<Entier> n2 = new Noeud<>(List.of(n1,f3));

        System.out.println(n2.somme());
    }

}
