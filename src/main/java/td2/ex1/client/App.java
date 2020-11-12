package td2.ex1.client;

import java.util.List;
import java.util.Map;

import td2.ex1.api.Somme;
import td2.ex1.api.ToString;

public class App {
    public static void main(String[] args) {
        //Q1
        // Version longue
        Somme<Integer> sommeInteger = new Somme<Integer>() {
            @Override
			public Integer somme(Integer a, Integer b) {
                return a + b;
            }
        };

        Somme<Double> sommeDouble = new Somme<Double>() {
            @Override
			public Double somme(Double a, Double b) {
                return a + b;
            }
        };

        Somme<Long> sommeLong = new Somme<Long>() {
            @Override
			public Long somme(Long a, Long b) {
                return a + b;
            }
        };

        Somme<String> sommeString = new Somme<String>() {
            @Override
			public String somme(String a, String b) {
                return String.format("%s%s", a, b);
            }
        };

        int a = sommeInteger.somme(1, 2);
        double b = sommeDouble.somme(1.1, 2.2);
        long c = sommeLong.somme((long) 1, (long) 2);
        String d = sommeString.somme("A", "B");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        // Version courte
        Somme<Integer> sommeInteger2 = (x, y) -> x + y;
        Somme<Double> sommeDouble2 = (x, y) -> x + y;
        Somme<Long> sommeLong2 = (x, y) -> x + y;
        Somme<String> sommeString2 = (x, y) -> x + y;
        
        int a2 = sommeInteger2.somme(1, 2);
        double b2 = sommeDouble2.somme(1.1, 2.2);
        long c2 = sommeLong2.somme((long) 1, (long) 2);
        String d2 = sommeString2.somme("A", "B");

        System.out.println(a2);
        System.out.println(b2);
        System.out.println(c2);
        System.out.println(d2);


        // Q2

        // Function<List<String>, String> fString= new ToString<List<String>>() {
        //      @Override public String toString(List<String> l) {
        //          StringBuilder sb = new StringBuilder();
        //          l.forEach(l2 -> sb.append(l2).append(" "));
        //          return sb.toString();
        //      }
        // }

        // ToString<List<String>> fString = (liste) -> {
        //     StringBuilder sb = new StringBuilder();
        //     l.forEach(l2 -> sb.append(l2).append(" "));
        //     return sb.toString();
        // };

        // ToString<Map<String, Integer>> fMap = (map) -> {
        //     StringBuilder sb = new StringBuilder();
        //     map.entrySet().forEach(l2 -> sb.append(l2.getKey()).append(": ").append(l2.getValue()).append(" "));
        //     return sb.toString();
        // };

        
    }
}
