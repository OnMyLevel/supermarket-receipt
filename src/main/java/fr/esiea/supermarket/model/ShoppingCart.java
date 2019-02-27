package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.offers.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public void deleteItem(Product product) {
        this.deleteItemQuantity(product, 1.0);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    public void deleteItemQuantity(Product product, double quantity) {
        if (productQuantities.containsKey(product) &&
            productQuantities.get(product) > 0 && productQuantities.get(product) >= quantity) {
            if(productQuantities.get(product) - quantity == 0) {
                productQuantities.remove(product);
            }else {
                productQuantities.put(product, productQuantities.get(product) - quantity);
            }
        } else {
            System.out.println("Ce produits n'est dans le panier");
        }
    }

    public Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog){
        for(Product p : productQuantities().keySet()){
            double quantity = productQuantities.get(p);
            if(offers.containsKey(p)){
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                Discount discount = offer.getDiscount(p,quantity,unitPrice);
                if (discount != null){
                    receipt.addDiscount(discount);
                }
            }
        }
    }
}

