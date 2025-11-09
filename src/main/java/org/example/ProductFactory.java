package org.example;

public class ProductFactory {
    public ProductFactory() {
    }

    public Product createProduct(int ID, double quantity, String typology) {
        String name = getNameFromDB(ID);
        double pricePerUnity = getPricePerUnityFromDB(ID);

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

    private String getNameFromDB(int ID) {
        return "Stringa";
    }

    private double getPricePerUnityFromDB(int ID) {
        return 1;
    }
}
