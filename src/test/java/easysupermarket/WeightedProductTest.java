package easysupermarket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedProductTest {
    ProductFactory productFactory = new ProductFactory();
    Product p1 = productFactory.createProduct(2, 1);
    Product p2 = productFactory.createProduct(2, 1);
    Product p3 = productFactory.createProduct(2, 2.1);
    Product p4 = productFactory.createProduct(1, 1);

    @Test
    void testEquals() {
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p1, p4);
    }
}