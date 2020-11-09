package td2.ex2.api;

import java.util.List;
import java.util.function.Predicate;

public class Filtrage<T> {
    public boolean filtragePredicatif(List<Predicate<T>> liste, T element) {
        boolean rtr = true;
        for(Predicate<T> pre: liste ){
            if(!pre.test(element)) {
                rtr = false;
                break;
            }
        }
        return rtr;
    }
}
