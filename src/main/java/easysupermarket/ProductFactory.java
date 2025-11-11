package easysupermarket;

import dbmanager.DBProductInterrogation;
import dbmanager.ProductInterrogation;

public class ProductFactory {
    ProductInterrogation productInterrogation = new DBProductInterrogation();

    public ProductFactory() {
        productInterrogation = new DBProductInterrogation();
    }

    public Product createProduct(int ID, double quantity) {
        ProductTypology typology = productInterrogation.getTypologyFromSource(ID);

        return switch (typology) {
            case UNIT_PRODUCT -> {
                if ((int) quantity != quantity) {
                    throw new IllegalArgumentException(
                            "Barcode error: double quantity for UnityProduct"
                    );
                }
                yield new UnitProduct(ID, quantity);
            }

            case WEIGHTED_PRODUCT -> new WeightedProduct(ID, quantity);
        };
    }
}
