package impl;

import finances.Price;

public class Product {
    private String name;
    private Price price;
    private int amount;

    public Product(String name, Price price, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    //metody typu get do wszystkich pol
    //metody typu set do price i amount
    //konstruktor z 3 argumentami
    //toString
    //equals i hasCode (dwa produkty sa rowne, jesli maja takie same name)
}

