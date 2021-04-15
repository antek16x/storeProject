package impl;

import finances.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store;
    private Product product;

    @BeforeEach
    void setUp() {
        store = new Store(new ProductRepositoryImpl());
    }

    @Test
    void productDeliveryIfExists() {
        //given
        store.productDelivery("ziemniaki", new Price(3, 60), 100);
        //when
        store.productDelivery("ziemniaki", new Price(3, 60), 50);
        //then
        String expected = "ziemniaki, 3,60, 150\n";
        assertEquals(expected, store.toString());
    }

    @Test
    void productDeliveryIfNotExists() {
        //given
        //when
        store.productDelivery("ziemniaki", new Price(3, 60), 50);
        //then
        String expected = "ziemniaki, 3,60, 50\n";
        assertEquals(expected, store.toString());
    }

    @Test
    void productSaleIfExceptionNotEnough() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //then
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> store.productSale("produkt", 120));
    }

    @Test
    void productSaleIfExceptionProductDoesNotExists() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //then
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> store.productSale("produkt", 120));
    }

    @Test
    void productSale() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        store.productSale("produkt", 20);
        //then
        assertEquals(80, store.productAmount("produkt"));
    }

    @Test
    void priceToPay() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        Price actual = store.priceToPay("produkt", 2);
        //then
        assertEquals(new Price(5,00), actual);
    }

    @Test
    void priceToPayIfNotExists() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        Price actual = store.priceToPay("produkt2", 2);
        //then
        assertNull(actual);
    }

    @Test
    void productPrice() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        Price actual = store.productPrice("produkt");
        //then
        assertEquals(new Price (2, 50), actual);
    }

    @Test
    void productPriceIfNotExists() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        Price actual = store.productPrice("produkt2");
        //then
        assertNull(actual);
    }

    @Test
    void productAmount() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        int actual = store.productAmount("produkt");
        //then
        assertEquals(100, actual);
    }

    @Test
    void productAmountIfNotExists() {
        //given
        store.productDelivery("produkt", new Price(2, 50), 100);
        //when
        int actual = store.productAmount("produkt2");
        //then
        assertEquals(0, actual);
    }

    @Test
    void testToString() {
        //given
        Product p = new Product("produkt", new Price(2, 50), 70);
        Product q = new Product("inny", new Price(4, 50), 40);
        store.productDelivery(p.getName(), p.getPrice(), p.getAmount());
        store.productDelivery(q.getName(), q.getPrice(), q.getAmount());
        //when
        String actual = store.toString();
        //then
        assertThat(actual).contains(p.toString());
        assertThat(actual).contains(q.toString());
        assertThat(actual.length()).isEqualTo(p.toString().length() + "\n".length() +
                q.toString().length() + "\n".length());
    }
}