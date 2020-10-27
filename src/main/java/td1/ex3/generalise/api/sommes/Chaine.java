package td1.ex3.generalise.api.sommes;

public class Chaine implements Sommable<Chaine> {

    private String s;

    public Chaine(String s) { this.s = s; }

    @Override
    public Chaine sommer(Chaine v) {  return new Chaine(this.getS() + v.getS()); }
    
    public String getS() { return s; }

    public void setS(String s) { this.s = s; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((s == null) ? 0 : s.hashCode());
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
        Chaine other = (Chaine) obj;
        if (s == null) {
            if (other.s != null)
                return false;
        } else if (!s.equals(other.s))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Chaine [s=" + s + "]";
    }
    
}
