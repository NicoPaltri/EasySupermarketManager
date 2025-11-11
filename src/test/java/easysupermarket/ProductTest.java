package easysupermarket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    ProductFactory productFactory = new ProductFactory();
    Product p1 = productFactory.createProduct(2, 1);
    Product p2 = productFactory.createProduct(2, 1.000000000000000000000000000000000000000000001);

    @Test
    void getTotalPrice() {
        assertEquals(0.15, p1.getTotalPrice());
    }

    @Test
    void haveTheSameQuantity() {
        assertTrue(p1.haveTheSameQuantity(p2));
    }
}