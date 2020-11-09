package td2.ex1.client;

import td2.ex1.api.Somme;

public class App {
    public static void main(String[] args) {
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
    }
}
