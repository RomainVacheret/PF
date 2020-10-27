package td1.ex3.generalise.client;

import td1.ex3.generalise.api.sommes.Chaine;
import td1.ex3.generalise.api.sommes.Entier;

public class App {
    // final Arbre<Integer> f1 = new Feuille<>(1);
    // final Arbre<Integer> f2 = new Feuille<>(2);
    // final Arbre<Integer> n1 = new Noeud<>(List.of(f1,f2));
    // final Arbre<Integer> f3 = new Feuille<>(3);
    // final Arbre<Integer> n2 = new Noeud<>(List.of(n1,f3));

    // final Arbre<String> f12 = new Feuille<>("1");
    // final Arbre<String> f22 = new Feuille<>("2");
    // final Arbre<String> n12 = new Noeud<>(List.of(f12,f22));
    // final Arbre<String> f32 = new Feuille<>("3");
    // final Arbre<String> n22 = new Noeud<>(List.of(n12,f32));

    public static void main(String[] args) {

        Entier e1 = new Entier(1);
        Entier e2 = new Entier(2);
        Entier e3 = new Entier(3);
        System.out.println(e1.sommer(e2.sommer(e3)));

        Chaine c1 = new Chaine("a");
        Chaine c2 = new Chaine("b");
        Chaine c3 = new Chaine("c");
        System.out.println(c1.sommer(c2.sommer(c3)));
    }


}
