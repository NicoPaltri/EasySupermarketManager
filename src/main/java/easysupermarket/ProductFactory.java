package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.ProductDBInterrogation;

public class ProductFactory {
    private final ProductInterrogation productInterrogation;

    public ProductFactory() {
        productInterrogation = new ProductDBInterrogation();
    }

    public Product createProduct(int ID, double quantity, ProductTypology typology) {
        String name = productInterrogation.getNameFromDB(ID);
        double pricePerUnity = productInterrogation.getPricePerUnitFromDB(ID);

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
