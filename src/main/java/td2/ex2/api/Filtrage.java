package td2.ex2.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filtrage<T> {
    public boolean filtragePredicatif(List<Predicate<T>> liste, T element) {
        Predicate<T> composition = x -> true;
        for(Predicate<T> pre: liste ){
            composition = composition.and(pre);
        }
        return composition.test(element);
    }

    public List<T> filtragePredicatifGeneral(List<Predicate<T>> predicats, List<T> liste) {
        // List<T> rtr = new ArrayList<T>();
        // for(T el: liste) {
        //     if(filtragePredicatif(predicats, el)) {
        //         rtr.append(el);
        //     }
        // }
        return liste.stream().filter(x -> filtragePredicatif(predicats, x)).collect(Collectors.toList());
    }
}
