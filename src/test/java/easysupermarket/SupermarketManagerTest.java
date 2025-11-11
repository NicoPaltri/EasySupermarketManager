package easysupermarket;

import easysupermarket.concreteresources.BarCode;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketManagerTest {
    BarCode b1 = new BarCode(1, 1);
    BarCode b2 = new BarCode(1, 2);

    BarCode b3 = new BarCode(2, 2);
    BarCode b4 = new BarCode(2, 3);

    SupermarketManager supermarketManager = new SupermarketManager();

    @Test
    void insertProductInMyList() {
        assertDoesNotThrow(() -> {
            supermarketManager.insertProductInMyList(b1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            supermarketManager.insertProductInMyList(b2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            supermarketManager.insertProductInMyList(b3);
        });
    }

    @Test
    void deleteProductFromMyList() throws Exception {
        supermarketManager.insertProductInMyList(b1);
        supermarketManager.deleteProductFromMyList(b1);
        assertEquals(supermarketManager.getProductList().stream().toList(), List.of());

        supermarketManager.insertProductInMyList(b1);
        supermarketManager.insertProductInMyList(b1);
        supermarketManager.deleteProductFromMyList(b1);

        assertEquals(1, supermarketManager.getProductList().getFirst().getQuantity());

        supermarketManager.deleteProductFromMyList(b2);
        assertEquals(supermarketManager.getProductList().stream().toList(), List.of());

        supermarketManager.insertProductInMyList(b3);
        assertThrows(IllegalArgumentException.class, () -> {
            supermarketManager.deleteProductFromMyList(b4);
        });
    }
}