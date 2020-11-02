package td1.ex3.generalise.api.sommes;

public class Entier implements Sommable<Entier> {

    private int val;

    public Entier(int val) {
        this.val = val;
    }

    @Override
    public Entier sommer(Entier v) {
        return new Entier(v.getVal() + this.getVal());
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Entier getInstance() {
        return new Entier(0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + val;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entier other = (Entier) obj;
        if (val != other.val)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Entier [val=" + val + "]";
    }

    @Override
    public int compareTo(Entier o) { return Integer.compare(this.val, o.getVal()); }

    
}
