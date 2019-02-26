package fr.esiea.supermarket.model.offers;

import fr.esiea.supermarket.model.Discount;
import fr.esiea.supermarket.model.Product;

public class FiveForAmountOffer implements Offer {

    private final Product p;
    private final double argument;


    public FiveForAmountOffer(Product p, double argument) {
        this.p = p;
        this.argument = argument;

    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice) {

        Discount discount = null;

        int numberOfXs = (int) quantity / 5;

        if ((int) quantity >= 5) {
            double discountTotal = unitPrice * quantity - (argument * numberOfXs + (int) quantity % 5 * unitPrice);
            discount = new Discount(p, 5 + " for " + argument, discountTotal);
        }

        return discount;
    }
}
