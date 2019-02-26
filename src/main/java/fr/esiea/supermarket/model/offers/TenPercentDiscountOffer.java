package fr.esiea.supermarket.model.offers;

import fr.esiea.supermarket.model.Discount;
import fr.esiea.supermarket.model.Product;

public class TenPercentDiscountOffer implements Offer {

    private final Product p;
    private final double argument;

    public TenPercentDiscountOffer(Product p, double argument) {
        this.p = p;
        this.argument = argument;
    }

    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice) {
        return new Discount(p, argument + "% off", quantity * unitPrice * argument / 100.0);
    }
}


