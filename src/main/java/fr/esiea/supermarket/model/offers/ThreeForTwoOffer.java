package fr.esiea.supermarket.model.offers;

import fr.esiea.supermarket.model.Discount;
import fr.esiea.supermarket.model.Product;

public class ThreeForTwoOffer implements Offer{


    private final Product p;
    private final double argument;

    public ThreeForTwoOffer(Product p,double argument){
        this.p = p;
        this.argument = argument;
    }


    @Override
    public Discount getDiscount(Product p, double quantity, double unitPrice) {
        Discount discount = null;

        int numberOfXs = (int) quantity / 3;

        if ((int) quantity > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + (int) quantity % 3 * unitPrice);
            discount = new Discount(p, "3 for 2", discountAmount);
        }

        return discount;
    }
}
