package td2.ex2.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Filtrage<T> {
    public boolean filtragePredicatif(List<Predicate<T>> liste, T element) {
        // boolean rtr = true;
        Predicate<T> composition = x -> true;
        // liste.forEach(predicate -> composition = composition.and(predicate));
        for(Predicate<T> pre: liste ){
            // if(!pre.test(element)) {
            //     rtr = false;
            //     break;
            // }
            composition = composition.and(pre);
        }
        // return rtr;
        return composition.test(element);
    }

    public List<T> filtragePredicatifGenerale(List<Predicate<T>> predicats, List<T> liste) {
        // List<T> rtr = new ArrayList<T>();
        // for(T el: liste) {
        //     if(filtragePredicatif(predicats, el)) {
        //         rtr.append(el);
        //     }
        // }
        return (List<T>) liste.stream().filter(x -> filtragePredicatif(predicats, x));
    }
}
