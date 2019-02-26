package fr.esiea.supermarket.model;

import java.util.Map;

public interface SupermarketCatalog {
    void addProduct(Product product, double price);
    double getUnitPrice(Product product);
    Map<String,Product> getProducts();
    void deleteProduct(String nameProduct);
    Product getProduct(String nameProduct);
}
