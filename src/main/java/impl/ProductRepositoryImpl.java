package impl;

import api.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductRepositoryImpl implements ProductRepository {

    private Map<String, Product> map;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ProductRepositoryImpl() {
        map = new HashMap();
        logger.info("Utworzono repozytorium!");
    }

    public Product findByName(String name) {
        logger.info("Pobieranie produktu o nazwie " + name);
        return map.get(name);
    }

    public boolean exists(String name) {
        logger.info("Szukanie produktu o nazwie " + name);
        return map.containsKey(name);
    }

    public Product create(Product product) {
        logger.info("Dodawanie do repozytorium");
        if (map.containsKey(product.getName())) {
            logger.warn("Nieudana próba wstawienia do bazy produktu o nazwie " +
                    product.getName());
            throw new IllegalStateException("Duplicate");
        }
        map.put(product.getName(), product);
        logger.debug("Dodano do repozytorium produkt o nazwie {} i cenie {}",
                product.getName(), product.getPrice().toString());
        return map.get(product.getName());
    }

    public Product update(Product product) {
        map.put(product.getName(), product);
        logger.info("Zmodyfikowano w repozytorium produkt o nazwie {}",
                product.getName());
        return map.get(product.getName());
    }

    public void delete(String name) {
        logger.info("Usuwanie z repozytorium produkt o nazwie {}", name);
        map.remove(name);
    }

    public Set<Product> findAll() {
        logger.info("Szukanie wszystkich produktów w repozytorium");
        return new HashSet<Product>(map.values());
    }
}
