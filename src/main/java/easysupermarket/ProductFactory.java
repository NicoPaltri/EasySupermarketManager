package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.ProductQuery;

public class ProductFactory {
    ProductInterrogation productInterrogation;

    public ProductFactory() {
        productInterrogation = new ProductQuery();
    }

    public Product createProduct(int ID, double quantity, String typology) {
        String name = productInterrogation.getNameFromDB(ID);
        double pricePerUnity = productInterrogation.getPricePerUnitFromDB(ID);

        return switch (typology.toLowerCase()) {
            case "unityproduct" -> {
                if ((int) quantity != quantity) {
                    throw new IllegalArgumentException("BarCode information error: double quantity for a unityProduct");
                }

                yield new UnityProduct(ID, name, pricePerUnity, quantity);
            }
            case "weightedproduct" -> new WeightedProduct(ID, name, pricePerUnity, quantity);
            default -> throw new IllegalArgumentException("Product typology not supported");
        };

    }
}
