package td1.ex3.generalise;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import td1.ex3.generalise.api.general.*;
import td1.ex3.generalise.api.sommes.*;

public class AppTest {
    public static final Arbre<Entier> arbre0Entier() {
        return new Noeud(new ArrayList<Arbre<Entier>>());
    }

    public static final Arbre<Entier> arbre1Entier() {
        final Arbre<Entier> f1 = new Feuille<>(new Entier(1));
        final Arbre<Entier> f2 = new Feuille<>(new Entier(2));
        final Arbre<Entier> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Entier> f3 = new Feuille<>(new Entier(3));
        final Arbre<Entier> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Entier> arbre2Entier() {
        final Arbre<Entier> f1 = new Feuille<>(new Entier(2));
        final Arbre<Entier> f2 = new Feuille<>(new Entier(3));
        final Arbre<Entier> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Entier> f3 = new Feuille<>(new Entier(1));
        final Arbre<Entier> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Entier> arbre3Entier() {
        final Arbre<Entier> f1 = new Feuille<>(new Entier(2));
        final Arbre<Entier> f2 = new Feuille<>(new Entier(1));
        final Arbre<Entier> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Entier> f3 = new Feuille<>(new Entier(3));
        final Arbre<Entier> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Chaine> arbre0Chaine() {
        return new Noeud(new ArrayList<Arbre<Chaine>>());
    }

    public static final Arbre<Chaine> arbre1Chaine() {
        final Arbre<Chaine> f1 = new Feuille<>(new Chaine("1"));
        final Arbre<Chaine> f2 = new Feuille<>(new Chaine("2"));
        final Arbre<Chaine> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Chaine> f3 = new Feuille<>(new Chaine("3"));
        final Arbre<Chaine> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Chaine> arbre2Chaine() {
        final Arbre<Chaine> f1 = new Feuille<>(new Chaine("2"));
        final Arbre<Chaine> f2 = new Feuille<>(new Chaine("3"));
        final Arbre<Chaine> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Chaine> f3 = new Feuille<>(new Chaine("1"));
        final Arbre<Chaine> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Chaine> arbre3Chaine() {
        final Arbre<Chaine> f1 = new Feuille<>(new Chaine("2"));
        final Arbre<Chaine> f2 = new Feuille<>(new Chaine("1"));
        final Arbre<Chaine> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Chaine> f3 = new Feuille<>(new Chaine("3"));
        final Arbre<Chaine> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }


    @Test public void testSizeEntier() {
        assertEquals(0, arbre0Entier().taille());
        assertEquals(3, arbre1Entier().taille());
        assertEquals(3, arbre2Entier().taille());
        assertEquals(3, arbre3Entier().taille());
    }

    @Test public void testContientEntier() {
        assertFalse(arbre0Entier().contient(new Entier(1)));
        assertFalse(arbre0Entier().contient(new Entier(2)));
        assertFalse(arbre0Entier().contient(new Entier(3)));
        assertFalse(arbre0Entier().contient(new Entier(4)));
        assertTrue(arbre1Entier().contient(new Entier(1)));
        assertTrue(arbre2Entier().contient(new Entier(1)));
        assertTrue(arbre3Entier().contient(new Entier(1)));
        assertTrue(arbre1Entier().contient(new Entier(2)));
        assertTrue(arbre2Entier().contient(new Entier(2)));
        assertTrue(arbre3Entier().contient(new Entier(2)));
        assertTrue(arbre1Entier().contient(new Entier(3)));
        assertTrue(arbre2Entier().contient(new Entier(3)));
        assertTrue(arbre3Entier().contient(new Entier(3)));
        assertFalse(arbre1Entier().contient(new Entier(4)));
        assertFalse(arbre2Entier().contient(new Entier(4)));
        assertFalse(arbre3Entier().contient(new Entier(4)));
    }

    @Test public void testValeursEntier() {
        final Set<Entier> contenu = Set.of(new Entier(1),new Entier(2),new Entier(3));
        assertEquals(Set.of(), arbre0Entier().valeurs());
        assertEquals(contenu, arbre1Entier().valeurs());
        assertEquals(contenu, arbre2Entier().valeurs());
        assertEquals(contenu, arbre3Entier().valeurs());
    }


    @Test public void testSizeChaine() {
        assertEquals(0, arbre0Entier().taille());
        assertEquals(3, arbre1Entier().taille());
        assertEquals(3, arbre2Entier().taille());
        assertEquals(3, arbre3Entier().taille());
    }

    @Test public void testContientChaine() {
        assertFalse(arbre0Chaine().contient(new Chaine("1")));
        assertFalse(arbre0Chaine().contient(new Chaine("2")));
        assertFalse(arbre0Chaine().contient(new Chaine("3")));
        assertFalse(arbre0Chaine().contient(new Chaine("4")));
        assertTrue(arbre1Chaine().contient(new Chaine("1")));
        assertTrue(arbre2Chaine().contient(new Chaine("1")));
        assertTrue(arbre3Chaine().contient(new Chaine("1")));
        assertTrue(arbre1Chaine().contient(new Chaine("2")));
        assertTrue(arbre2Chaine().contient(new Chaine("2")));
        assertTrue(arbre3Chaine().contient(new Chaine("2")));
        assertTrue(arbre1Chaine().contient(new Chaine("3")));
        assertTrue(arbre2Chaine().contient(new Chaine("3")));
        assertTrue(arbre3Chaine().contient(new Chaine("3")));
        assertFalse(arbre1Chaine().contient(new Chaine("4")));
        assertFalse(arbre2Chaine().contient(new Chaine("4")));
        assertFalse(arbre3Chaine().contient(new Chaine("4")));
    }

    @Test public void testValeursChaine() {
        final Set<Chaine> contenu = Set.of(new Chaine("1"), new Chaine("2"), new Chaine("3"));
        assertEquals(Set.of(), arbre0Chaine().valeurs());
        assertEquals(contenu, arbre1Chaine().valeurs());
        assertEquals(contenu, arbre2Chaine().valeurs());
        assertEquals(contenu, arbre3Chaine().valeurs());
    }

    // @Test public void testValeur() {
    //     assertEquals(null, arbre0().somme());
    //     assertEquals((Entier) 6, arbre1().somme());
    //     assertEquals((Entier) 6, arbre2().somme());
    //     assertEquals((Entier) 6, arbre3().somme());
    // }

    // @Test public void testMin() {
    //     assertEquals(null, arbre0().min());
    //     assertEquals((Entier) 1, arbre1().min());
    //     assertEquals((Entier) 1, arbre2().min());
    //     assertEquals((Entier) 1, arbre3().min());
    // }

    // @Test public void testMax() {
    //     assertEquals(null, arbre0().max());
    //     assertEquals((Entier) 3, arbre1().max());
    //     assertEquals((Entier) 3, arbre2().max());
    //     assertEquals((Entier) 3, arbre3().max());
    // }

    // @Test public void testEstTrie() {
    //     assertTrue(arbre0().estTrie());
    //     assertTrue(arbre1().estTrie());
    //     assertFalse(arbre2().estTrie());
    //     assertFalse(arbre3().estTrie());
    // }
}
