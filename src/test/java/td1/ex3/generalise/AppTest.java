package td1.ex3.generalise;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import td1.ex3.generalise.api.*;

public class AppTest {
    public static final Arbre<Integer> arbre0Integer() {
        return new Noeud(new ArrayList<Arbre<Integer>>());
    }

    public static final Arbre<Integer> arbre1Integer() {
        final Arbre<Integer> f1 = new Feuille<>(1);
        final Arbre<Integer> f2 = new Feuille<>(2);
        final Arbre<Integer> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Integer> f3 = new Feuille<>(3);
        final Arbre<Integer> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Integer> arbre2Integer() {
        final Arbre<Integer> f1 = new Feuille<>(2);
        final Arbre<Integer> f2 = new Feuille<>(3);
        final Arbre<Integer> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Integer> f3 = new Feuille<>(1);
        final Arbre<Integer> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<Integer> arbre3Integer() {
        final Arbre<Integer> f1 = new Feuille<>(2);
        final Arbre<Integer> f2 = new Feuille<>(1);
        final Arbre<Integer> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<Integer> f3 = new Feuille<>(3);
        final Arbre<Integer> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<String> arbre0String() {
        return new Noeud(new ArrayList<Arbre<String>>());
    }

    public static final Arbre<String> arbre1String() {
        final Arbre<String> f1 = new Feuille<>("1");
        final Arbre<String> f2 = new Feuille<>("2");
        final Arbre<String> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<String> f3 = new Feuille<>("3");
        final Arbre<String> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<String> arbre2String() {
        final Arbre<String> f1 = new Feuille<>("2");
        final Arbre<String> f2 = new Feuille<>("3");
        final Arbre<String> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<String> f3 = new Feuille<>("1");
        final Arbre<String> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }

    public static final Arbre<String> arbre3String() {
        final Arbre<String> f1 = new Feuille<>("2");
        final Arbre<String> f2 = new Feuille<>("1");
        final Arbre<String> n1 = new Noeud<>(List.of(f1,f2));
        final Arbre<String> f3 = new Feuille<>("3");
        final Arbre<String> n2 = new Noeud<>(List.of(n1,f3));
        return n2;
    }


    @Test public void testSizeInteger() {
        assertEquals(0, arbre0Integer().taille());
        assertEquals(3, arbre1Integer().taille());
        assertEquals(3, arbre2Integer().taille());
        assertEquals(3, arbre3Integer().taille());
    }

    @Test public void testContientInteger() {
        assertFalse(arbre0Integer().contient(1));
        assertFalse(arbre0Integer().contient(2));
        assertFalse(arbre0Integer().contient(3));
        assertFalse(arbre0Integer().contient(4));
        assertTrue(arbre1Integer().contient(1));
        assertTrue(arbre2Integer().contient(1));
        assertTrue(arbre3Integer().contient(1));
        assertTrue(arbre1Integer().contient(2));
        assertTrue(arbre2Integer().contient(2));
        assertTrue(arbre3Integer().contient(2));
        assertTrue(arbre1Integer().contient(3));
        assertTrue(arbre2Integer().contient(3));
        assertTrue(arbre3Integer().contient(3));
        assertFalse(arbre1Integer().contient(4));
        assertFalse(arbre2Integer().contient(4));
        assertFalse(arbre3Integer().contient(4));
    }

    @Test public void testValeursInteger() {
        final Set<Integer> contenu = Set.of(1,2,3);
        assertEquals(Set.of(), arbre0Integer().valeurs());
        assertEquals(contenu, arbre1Integer().valeurs());
        assertEquals(contenu, arbre2Integer().valeurs());
        assertEquals(contenu, arbre3Integer().valeurs());
    }


    @Test public void testSizeString() {
        assertEquals(0, arbre0Integer().taille());
        assertEquals(3, arbre1Integer().taille());
        assertEquals(3, arbre2Integer().taille());
        assertEquals(3, arbre3Integer().taille());
    }

    @Test public void testContientString() {
        assertFalse(arbre0String().contient("1"));
        assertFalse(arbre0String().contient("2"));
        assertFalse(arbre0String().contient("3"));
        assertFalse(arbre0String().contient("4"));
        assertTrue(arbre1String().contient("1"));
        assertTrue(arbre2String().contient("1"));
        assertTrue(arbre3String().contient("1"));
        assertTrue(arbre1String().contient("2"));
        assertTrue(arbre2String().contient("2"));
        assertTrue(arbre3String().contient("2"));
        assertTrue(arbre1String().contient("3"));
        assertTrue(arbre2String().contient("3"));
        assertTrue(arbre3String().contient("3"));
        assertFalse(arbre1String().contient("4"));
        assertFalse(arbre2String().contient("4"));
        assertFalse(arbre3String().contient("4"));
    }

    @Test public void testValeursString() {
        final Set<String> contenu = Set.of("1", "2", "3");
        assertEquals(Set.of(), arbre0String().valeurs());
        assertEquals(contenu, arbre1String().valeurs());
        assertEquals(contenu, arbre2String().valeurs());
        assertEquals(contenu, arbre3String().valeurs());
    }

    // @Test public void testValeur() {
    //     assertEquals(null, arbre0().somme());
    //     assertEquals((Integer) 6, arbre1().somme());
    //     assertEquals((Integer) 6, arbre2().somme());
    //     assertEquals((Integer) 6, arbre3().somme());
    // }

    // @Test public void testMin() {
    //     assertEquals(null, arbre0().min());
    //     assertEquals((Integer) 1, arbre1().min());
    //     assertEquals((Integer) 1, arbre2().min());
    //     assertEquals((Integer) 1, arbre3().min());
    // }

    // @Test public void testMax() {
    //     assertEquals(null, arbre0().max());
    //     assertEquals((Integer) 3, arbre1().max());
    //     assertEquals((Integer) 3, arbre2().max());
    //     assertEquals((Integer) 3, arbre3().max());
    // }

    // @Test public void testEstTrie() {
    //     assertTrue(arbre0().estTrie());
    //     assertTrue(arbre1().estTrie());
    //     assertFalse(arbre2().estTrie());
    //     assertFalse(arbre3().estTrie());
    // }
}
