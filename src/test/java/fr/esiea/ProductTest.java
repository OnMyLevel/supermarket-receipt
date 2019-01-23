package fr.esiea;

import fr.esiea.supermarket.model.Product;
import fr.esiea.supermarket.model.ProductUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void  equalTest(){

        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Assertions.assertThat(rasoir.hashCode()).isEqualTo(rasoir.hashCode());
        Assertions.assertThat(true).isEqualTo(rasoir.equals(rasoir));
    }

    @Test
    public void  notEqualTest(){

        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Product pain = new Product("Pain", ProductUnit.Kilo);
        Assertions.assertThat(false).isEqualTo(pain.equals(rasoir));
        Assertions.assertThat(rasoir.hashCode()).isNotEqualTo(pain.hashCode());
    }

    @Test
    public void  notNullEqualTest(){

        Product rasoir = new Product("Rasoir", ProductUnit.Each);
       // Product pain ;
        Assertions.assertThat(false).isEqualTo(rasoir.equals(null));
        Assertions.assertThat(rasoir.hashCode()).isNotEqualTo(null);
    }

    @Test
    public void  notClasslEqualTest(){

        Product rasoir = new Product("Rasoir", ProductUnit.Each);
         Object pain = new  Product("Rasoir", ProductUnit.Each);;
        Assertions.assertThat(true).isEqualTo(rasoir.equals(pain));
        Assertions.assertThat(rasoir.hashCode()).isNotEqualTo(pain);
    }
}