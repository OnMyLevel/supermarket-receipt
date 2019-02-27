package fr.esiea.supermarket.model.offers.bundles;

import fr.esiea.supermarket.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BundlePercentOffer implements BundleOffer {

    private Map<Product, Double> productQuantities;
    private double valPro;

    private List<ProductQuantity> listProductQuantities;


    public BundlePercentOffer(List<ProductQuantity> listProductQuantities)
    {
        this.listProductQuantities = listProductQuantities;
    }


    public BundlePercentOffer(Map<Product, Double> pq, double valPro){

        this.productQuantities = pq;
        this.valPro = valPro;
    }


    @Override
    public Map<Product, Double> getProductQuantities() {
        return this.productQuantities;
    }


    @Override
    public boolean compareBundle(Map<Product, Double> theProductQuantities)
    {
        if (this.productQuantities.size() == theProductQuantities.size())
        {
            for (Product p : this.productQuantities.keySet()) {
                if(theProductQuantities.containsKey(p))
                    theProductQuantities.remove(p);

                else
                    return false;


            }
            if(theProductQuantities.isEmpty())
                return true;
        }
        return false;
    }



    @Override
    public double getPromo(Map<Product, Double> productQuantities, SupermarketCatalog catalog, double valeurPromo) {

        double nouveauPrix = 0;
        double totalPrice = 0;

        List<Product> products = new ArrayList<>();


        for (Product p : this.productQuantities.keySet()) {
            products.add(p);

            double quantity = this.getProductQuantities().get(p);
            double unitPrice = catalog.getUnitPrice(p);

            System.out.println(p + " quantité du produit : " + quantity);
            System.out.println(p + " prix à l'unicité : " + unitPrice);

            totalPrice += unitPrice * quantity;
        }
        nouveauPrix = totalPrice * valeurPromo/100;


        return nouveauPrix;
    }


    @Override
    public String toString() {

        String s = "";

        for (Product p : productQuantities.keySet()) {

            s += "Nom du produit : " + p.getName() + "; ";
        }

        return s;

    }




}
