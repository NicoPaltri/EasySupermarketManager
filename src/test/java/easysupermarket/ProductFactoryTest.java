package easysupermarket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
    ProductFactory productFactory = new ProductFactory();
    Product p1 = productFactory.createProduct(1, 1);
    Product p2 = productFactory.createProduct(2, 1);

    @Test
    void createProduct() {
        assertEquals(UnitProduct.class, p1.getClass());
        assertEquals(WeightedProduct.class, p2.getClass());

        assertThrows(IllegalArgumentException.class, () -> {
            productFactory.createProduct(3, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            productFactory.createProduct(1, 1.5);
        });
    }


}