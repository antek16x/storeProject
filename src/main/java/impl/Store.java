package impl;

import api.ProductRepository;
import finances.Price;

public class Store {

    private ProductRepository repository;

    public Store(ProductRepository repository) {
        this.repository = repository;
    }

    public void productDelivery(String name, Price price, int amount) {
        if (repository.exists(name)) {
            repository.findByName(name).setAmount(repository.findByName(name).getAmount() + amount);
        } else {
            repository.create(new Product(name, price, amount));
        }
    }

    //wyrzuca wyjatek jesli brak (nie ma produktu, lub za malo)
    public void productSale(String name, int amount) {
        Product p = repository.findByName(name);
        if (p != null) {
            if (p.getAmount() < amount)
                throw new IllegalArgumentException("nie można sprzedać: za mało produktu");
            else
                repository.update(new Product(name, p.getPrice(), p.getAmount() - amount));
        } else
            throw new IllegalArgumentException("nie można sprzedać: nie ma takiego produktu");
    }

    //zwraca null, jeśli brak
    public Price priceToPay(String name, int amount) {
        if (!repository.exists(name)) {
            return null;
        } else {
            return repository.findByName(name).getPrice().multiply(amount);
        }
    }

    //zwraca null, jesli brak
    public Price productPrice(String name) {
        if (!repository.exists(name)) {
            return null;
        } else {
            return repository.findByName(name).getPrice();
        }
    }

    //zwraca 0, jesli brak
    public int productAmount(String name) {
        if (!repository.exists(name)) {
            return 0;
        } else {
            return repository.findByName(name).getAmount();
        }
    }

    // info o każdym produkcie w oddzielnej linii
    public String toString() {
        String string = "";
        for (Product p : repository.findAll()) {
            string += p.toString() + "\n";
        }
        return string;
    }

}
