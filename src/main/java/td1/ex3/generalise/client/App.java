package td1.ex3.generalise.client;

import java.util.List;

import td1.ex3.generalise.api.Arbre;
import td1.ex3.generalise.api.Feuille;
import td1.ex3.generalise.api.Noeud;

public class App {
    final Arbre<Integer> f1 = new Feuille<>(1);
    final Arbre<Integer> f2 = new Feuille<>(2);
    final Arbre<Integer> n1 = new Noeud<>(List.of(f1,f2));
    final Arbre<Integer> f3 = new Feuille<>(3);
    final Arbre<Integer> n2 = new Noeud<>(List.of(n1,f3));

    final Arbre<String> f12 = new Feuille<>("1");
    final Arbre<String> f22 = new Feuille<>("2");
    final Arbre<String> n12 = new Noeud<>(List.of(f12,f22));
    final Arbre<String> f32 = new Feuille<>("3");
    final Arbre<String> n22 = new Noeud<>(List.of(n12,f32));


}
