package finances;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// należy zaimplementowac interfejs Comparable
public class Price implements Comparable<Price> {

    private int zlote;
    private int grosze;
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public Price(int zlote, int grosze) {
        if (zlote < 0 || grosze < 0) {
            logger.error("Próba utworzenia kwoty z wartości ujemnych");
            throw new IllegalArgumentException();
        } else {
            if (grosze > 99) {
                int ile = grosze / 100;
                grosze = grosze - 100 * ile;
                zlote = zlote + 1 * ile;
            }
            logger.info("Utworzono kwatę {}, {}", zlote, grosze);
            this.zlote = zlote;
            this.grosze = grosze;
        }
    }

    public int getZlote() {
        return zlote;
    }

    public int getGrosze() {
        return grosze;
    }

    public Price() {
    }

    @Override
    public String toString() {
        if (grosze > 9) {
            return zlote + "," + grosze;
        } else {
            return zlote + ",0" + grosze;
        }
    }

    public Price add(Price price) {
        int zl = this.zlote + price.zlote;
        int gr = this.grosze + price.grosze;
        if (gr > 99) {
            zl++;
            gr = gr - 100;
        }
        return new Price(zl, gr);
    }

    public Price multiply(int ile) {
        int gr = (grosze + zlote * 100) * ile;
        return new Price(gr / 100, gr % 100);
    }

    public int compareTo(Price price) {
        if (this.zlote != price.zlote) {
            return this.zlote - price.zlote;
        }
        //liczby zl takie same
        return this.grosze - price.grosze;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price Price = (Price) o;

        if (zlote != Price.zlote) return false;
        return grosze == Price.grosze;
    }

    @Override
    public int hashCode() {
        int result = zlote;
        result = 31 * result + grosze;
        return result;
    }

}
