package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.ProductDBInterrogation;

public class ProductFactory {
    ProductInterrogation productInterrogation;

    public ProductFactory() {
        productInterrogation = new ProductDBInterrogation();
    }

    public Product createProduct(int ID, double quantity, String typology) {
        String name = productInterrogation.getNameFromDB(ID);
        double pricePerUnity = productInterrogation.getPricePerUnitFromDB(ID);

        return switch (typology.toLowerCase()) {
            case "unityproduct" -> {
                if ((int) quantity != quantity) {
                    throw new IllegalArgumentException("BarCode information error: double quantity for a unityProduct");
                }

                yield new UnityProduct(ID, quantity);
            }
            case "weightedproduct" -> new WeightedProduct(ID, quantity);
            default -> throw new IllegalArgumentException("Product typology not supported");
        };

    }
}
