package api;

import impl.Product;

import java.util.Set;

public interface ProductRepository {

    //zwraca null jesli brak
    Product findByName(String name);

    boolean exists(String name);

    //wyrzuca IllegalStateException, gdy byl w bazie
    Product create(Product product);

    //jesli brak, dodaje do bazy,
    //jesli jest, zmienia price i amount
    Product update(Product product);

    // jesli brak, nie robi nic
    void delete(String name);

    Set<Product> findAll();
}