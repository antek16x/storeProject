package finances;

import impl.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void testKonstruktoraGdyGroszeUjemne(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Price(2,-3));
    }

    @Test
    void testKonstruktoraGdyZloteUjemne(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Price(-2,3));
    }

    @Test
    void testKonstruktoraNormalnie(){
        Price price = new Price(3,60);
        assertEquals(3, price.getZlote());
        assertEquals(60, price.getGrosze());
    }

    @Test
    void testKonstruktoraGdyGroszeJednocyfrowe(){
        Price price = new Price(3,6);
        assertEquals("3,06", price.toString());
    }

    @Test
    void testToString() {
        Price price = new Price(4,50);
        assertEquals("4,50", price.toString());
    }

    @Test
    void add() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(4,30);
        assertEquals("6,50", price1.add(price2).toString());
    }

    @Test
    void multiply() {
        Price price = new Price(2,20);
        Price expected = new Price(11,0);
        assertEquals(expected, price.multiply(5));
    }

    @Test
    void testEqualsWhenEqual() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(2,20);
        assertEquals(true, price1.equals(price2));
    }

    @Test
    void testEqualsWhenNotEqual() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(3,20);
        assertEquals(false, price1.equals(price2));

    }

    @Test
    void compareToWhenBigger() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(1,20);
        assertEquals(1, price1.compareTo(price2));

    }

    @Test
    void compareToWhenEqual() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(2,20);
        assertEquals(0, price1.compareTo(price2));
    }

    @Test
    void compareToWhenLess() {
        Price price1 = new Price(2,20);
        Price price2 = new Price(3,20);
        assertEquals(-1, price1.compareTo(price2));
    }
}