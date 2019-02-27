package fr.esiea.supermarket.webApp;

import fr.esiea.supermarket.ReceiptPrinter;
import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.model.offers.SpecialOfferType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SpringWebApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApplicationController.class);
    private final AtomicInteger sequenceGenerator = new AtomicInteger();
    private final SupermarketCatalog databaseProduct = new FakeCatalog();
    private final HashMap<Integer,ShoppingCart> databaseClients = new HashMap<>();
    private final Teller marketTeller = new Teller(databaseProduct);


    @RequestMapping("/create_product")
    public String createProduct(@RequestParam("name") String firstName,
                                @RequestParam("price")String price, @RequestParam("productUnit") String productUnit){
        Product product = null;
        if(productUnit.contains("Each")){
            product = new Product(firstName, ProductUnit.Each);
        }else{
            product = new Product(firstName, ProductUnit.Kilo);
        }
        double value = Double.parseDouble(price);
        databaseProduct.addProduct(product,value);
        LOGGER.info("NB PRODUCTS "+this.databaseProduct.getProducts().size());
        return this.databaseProduct.getProducts().entrySet().toString();
    }

    @RequestMapping("/")
    public String index(){
        return "---------HELLO MARKETPLACE-------";
    }

    @RequestMapping("/delete_product")
    public String deleteProduct(@RequestParam("name") String productName) {
        this.databaseProduct.deleteProduct(productName);
        LOGGER.info("NB PRODUCTS "+this.databaseProduct.getProducts().size());
        return this.databaseProduct.getProducts().entrySet().toString();
    }

    @RequestMapping("/list_product")
    public List<Product> listProducts() {
        List<Product>  products = new ArrayList<Product>(this.databaseProduct.getProducts().values());
        LOGGER.info(products.toString());
        return products;
    }
    public static void main(String[] args) {

        SpringApplication.run(SpringWebApplicationController.class);
    }

    @RequestMapping("/on_offer")
    public String activeOffre(@RequestParam("nameProduct")String nameProduct
        , @RequestParam("typeOffer") String typeOffer, @RequestParam("argOffer") String argOffer) {
        Product product= this.databaseProduct.getProduct(nameProduct);
        double argumentOffer= Double.valueOf(argOffer);
        if(typeOffer.compareTo("ThreeForTwo")==0){
            this.marketTeller.addSpecialOffer(SpecialOfferType.ThreeForTwo, product, argumentOffer);
        }else if(typeOffer.compareTo("TenPercentDiscount")==0){
            this.marketTeller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product, argumentOffer);
        }else{
            this.marketTeller.addSpecialOffer(SpecialOfferType.FiveForAmount, product, argumentOffer);
        }
        LOGGER.info("NB OFFERS "+this.marketTeller.getOffers().size());
        return this.marketTeller.getOffers().entrySet().toString();
    }

    @RequestMapping("/off_offer")
    public String deactivationOffre(@RequestParam("nameProduct")String nameProduct) {
        Product product= this.databaseProduct.getProduct(nameProduct);
        this.marketTeller.deleteSpecialOffer(product);
        LOGGER.info("NB OFFERS "+this.marketTeller.getOffers().size());
        return this.marketTeller.getOffers().toString();
    }

    @RequestMapping("/create_client")
    public String createClient(@RequestParam("idClient") String idClient) {
        int id = Integer.valueOf(idClient);
        this.databaseClients.put(id,new ShoppingCart());
        LOGGER.info("NB CLIENTS "+this.databaseClients.entrySet().size());
        return this.databaseClients.entrySet().toString();
    }

    @RequestMapping("/client_add_product")
    public String clientAddProduct(@RequestParam("idClient") String idClient,
                                   @RequestParam("nameProduct") String nameProduct) {
        int id = Integer.valueOf(idClient);
        Product product= this.databaseProduct.getProduct(nameProduct);
        this.databaseClients.get(id).addItem(product);
        LOGGER.info("Shoppingcard client "+this.databaseClients.get(id).
            productQuantities().
            entrySet().
            toString());
        return this.databaseClients.get(id).
            productQuantities().
            entrySet().
            toString();
    }

    @RequestMapping("/client_delete_product")
    public String clientDeleteProduct(@RequestParam("idClient") String idClient,
                                      @RequestParam("nameProduct") String nameProduct,
                                      @RequestParam("nbItems") String nbItems ) {
        double nbItem= Double.valueOf(nbItems);
        int id = Integer.valueOf(idClient);
        Product product = this.databaseProduct.getProduct(nameProduct);
        this.databaseClients.get(id).deleteItemQuantity(product,nbItem);
        LOGGER.info("Shoppingcard client "+this.databaseClients.get(id).
            productQuantities().
            entrySet().
            toString());
        return this.databaseClients.get(id).
            productQuantities().
            entrySet().
            toString();
    }

    @RequestMapping("/check_print")
    public String checkCartPrinClient(@RequestParam("idClient") String idClient) {
        int id = Integer.valueOf(idClient);
        Receipt receipt =  this.marketTeller.checksOutArticlesFrom(this.databaseClients.get(id));
        ReceiptPrinter printerDefault = new ReceiptPrinter();
        LOGGER.info("CHECK PRINT "+printerDefault.printReceipt(receipt));
        return printerDefault.printReceipt(receipt).toString();
    }

    @RequestMapping("/check_price")
    public String checkCartPriceClient(@RequestParam("idClient") String idClient) {
        int id = Integer.valueOf(idClient);
        Receipt receipt =  this.marketTeller.checksOutArticlesFrom(this.databaseClients.get(id));
        LOGGER.info("PRICE "+receipt.getTotalPrice());
        return receipt.getTotalPrice().toString();
    }
}

