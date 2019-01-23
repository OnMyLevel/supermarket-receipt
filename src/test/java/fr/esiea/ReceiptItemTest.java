package fr.esiea;

import fr.esiea.supermarket.model.Product;
import fr.esiea.supermarket.model.ProductUnit;
import fr.esiea.supermarket.model.ReceiptItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReceiptItemTest {


    @Test
    public void getPriceTest(){

        Product p = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p, 2, 4.11, 8.22 );
        //System.out.println(ri.getPrice());
        Assertions.assertThat(4.11).isEqualTo(ri.getPrice());

    }


    @Test
    public void getProductTest(){

        Product p = new Product("Salade", ProductUnit.Kilo);
        ReceiptItem ri = new ReceiptItem(new Product("Salade", ProductUnit.Kilo), 1, 0.50, 1 );
        Assertions.assertThat(p).isEqualTo(ri.getProduct());

    }



    @Test
    public void getTotalPrice(){

        Product p = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p, 2, 4.11, 8.22 );
        //System.out.println(ri.getPrice());
        Assertions.assertThat(8.22).isEqualTo(ri.getTotalPrice());

    }

    @Test
    public void getQuantityTest(){

        Product p = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p, 2, 4.11, 8.22 );
        //System.out.println(ri.getPrice());
        Assertions.assertThat(2.0).isEqualTo(ri.getQuantity());

    }

    @Test
    public void CompareReceiptItemTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        Product p2 = new Product("salade", ProductUnit.Each);


        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        ReceiptItem ri2 = new ReceiptItem(p2,2,4,8);

        Assertions.assertThat(false).isEqualTo(ri.equals(ri2));
        Assertions.assertThat(ri.hashCode()).isNotEqualTo(ri2.hashCode());

    }
}
