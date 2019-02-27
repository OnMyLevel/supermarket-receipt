package fr.esiea.supermarket.model.offers.bundles;
import fr.esiea.supermarket.model.Product;
import fr.esiea.supermarket.model.ProductQuantity;
import fr.esiea.supermarket.model.SupermarketCatalog;

import java.util.List;
import java.util.Map;

public interface BundleOffer {

    Map<Product, Double> getProductQuantities();
    boolean compareBundle(Map<Product, Double> theProductQuantities);
    double getPromo(Map<Product, Double> productQuantities, SupermarketCatalog catalog, double valeurPromo);

}
