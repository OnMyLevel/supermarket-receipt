package fr.esiea.supermarket.model;

import fr.esiea.supermarket.model.offers.*;
import fr.esiea.supermarket.model.offers.bundles.BundleOffer;
import fr.esiea.supermarket.model.offers.bundles.BundlePercentOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();
    private List<BundleOffer> bundleOffers = new ArrayList<>();


    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public Offer deleteSpecialOffer(Product product){
        return this.offers.remove(product);
    }

    public Map<Product, Offer> getOffers() {
        return offers;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        switch(offerType) {
            case FiveForAmount:
                this.offers.put(product, new FiveForAmountOffer(product, argument));
                break;
            case ThreeForTwo:
                this.offers.put(product, new ThreeForTwoOffer(product, argument));
                break;
            case TwoForAmount:
                this.offers.put(product, new TwoForAmountOffer(product, argument));
                break;
            case TenPercentDiscount:
                this.offers.put(product, new TenPercentDiscountOffer(product, argument));
                break;
        }
    }

    public void addSpecialBundleOffer(SpecialOfferType offerType, Map<Product, Double> productQuantities, double valPro) {

        switch (offerType) {
            case BundlePercentDiscount:
                this.bundleOffers.add(new BundlePercentOffer(productQuantities, valPro) {
                });
                break;
        }

    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }

}
