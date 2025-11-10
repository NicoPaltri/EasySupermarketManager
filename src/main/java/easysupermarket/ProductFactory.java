package easysupermarket;

public class ProductFactory {
    public ProductFactory() {
    }

    public Product createProduct(int ID, double quantity, ProductTypology typology) {
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
