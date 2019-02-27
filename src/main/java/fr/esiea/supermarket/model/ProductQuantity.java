package fr.esiea.supermarket.model;

import java.util.Objects;

public class ProductQuantity {
    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double weight) {
        this.product = product;
        this.quantity = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantity productQuantity = (ProductQuantity) o;
        return Objects.equals(product, productQuantity.product) &&
            quantity == productQuantity.quantity;
    }
}
