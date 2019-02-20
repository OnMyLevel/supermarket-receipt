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
        Assertions.assertThat(4.11).isEqualTo(ri.getPrice());
    }


    @Test
    public void getProductTest(){
        Product p = new Product("Salade", ProductUnit.Kilo);
        ReceiptItem ri = new ReceiptItem(new Product("Salade", ProductUnit.Kilo), 1, 0.50, 1 );
        Assertions.assertThat(p).isEqualTo(ri.getProduct());
    }

    @Test
    public void equalTest(){
        Product p = new Product("Salade", ProductUnit.Kilo);
        ReceiptItem ri = new ReceiptItem(new Product("Salade", ProductUnit.Kilo), 1, 0.50, 1 );
        Assertions.assertThat(true).isEqualTo(ri.equals(ri));
    }

    @Test
    public void notEqualTest(){
        Product p = new Product("Salade", ProductUnit.Kilo);
        ReceiptItem ri = new ReceiptItem(new Product("Salade", ProductUnit.Kilo), 1, 0.50, 1 );
        ReceiptItem ri2 = new ReceiptItem(new Product("Stylo", ProductUnit.Each), 1, 2.50, 1 );
        Assertions.assertThat(false).isEqualTo(ri.equals(ri2));
    }

    @Test
    public void getTotalPrice(){
        Product p = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p, 2, 4.11, 8.22 );
        Assertions.assertThat(8.22).isEqualTo(ri.getTotalPrice());
    }

    @Test
    public void getQuantityTest(){
        Product p = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p, 2, 4.11, 8.22 );
        Assertions.assertThat(2.0).isEqualTo(ri.getQuantity());
    }

    @Test
    public void compareReceiptItemTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        Product p2 = new Product("Salade", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        ReceiptItem ri2 = new ReceiptItem(p2,2,4,8);
        Assertions.assertThat(false).isEqualTo(ri.equals(ri2));
        Assertions.assertThat(ri.hashCode()).isNotEqualTo(ri2.hashCode());
    }

    @Test
    public void compareNullReceiptItemTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        Assertions.assertThat(false).isEqualTo(ri.equals(null));
        Assertions.assertThat(ri.hashCode()).isNotEqualTo(null);
    }

    @Test
    public void compareTwoReceiptItemTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        ReceiptItem ri2 = new ReceiptItem(p1,2,4,8);
        Assertions.assertThat(true).isEqualTo(ri.equals(ri2));
        Assertions.assertThat(ri.hashCode()).isEqualTo(ri2.hashCode());
    }

    @Test
    public void compareTwoObjectsTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        String ri2 = new String("Recept");
        Assertions.assertThat(false).isEqualTo(ri.equals(ri2));
        Assertions.assertThat(ri.hashCode()).isNotEqualTo(ri2.hashCode());
    }

    @Test
    public void compareTwoObjectsEqualsTest(){
        Product p1 = new Product("Rasoir", ProductUnit.Each);
        ReceiptItem ri = new ReceiptItem(p1,2,4,8);
        String ri2 = new String("Recept");
        Assertions.assertThat(false).isEqualTo(ri.equals(ri2));
        Assertions.assertThat(ri.hashCode()).isNotEqualTo(ri2.hashCode());
    }

}
