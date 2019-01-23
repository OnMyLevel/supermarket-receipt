package fr.esiea;

import fr.esiea.supermarket.model.Product;
import fr.esiea.supermarket.model.ProductUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testSomething(){

        Product rasoir = new Product("Rasoir", ProductUnit.Each);
        Assertions.assertThat(true).isEqualTo(rasoir.equals(rasoir));
    }
}
