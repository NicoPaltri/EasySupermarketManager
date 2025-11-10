package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.ProductDBInterrogation;
import easysupermarket.concreteresources.BarCode;
import easysupermarket.concreteresources.ScannerGun;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupermarketManager {
    private final List<Product> productList;
    private final ScannerGun scannerGun;
    private final ProductFactory productFactory;
    private final ProductInterrogation productInterrogation;

    public SupermarketManager() {
        this.scannerGun = new ScannerGun();
        this.productList = new ArrayList<>();
        this.productFactory = new ProductFactory();
        this.productInterrogation = new ProductDBInterrogation();
    }

    public final void insertProductInMyList(BarCode barCode) throws Exception {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        Optional<Product> product = isProductAlreadyInMyList(ID);

        if (product.isPresent()) {
            Product existingProduct = product.get();

            if (existingProduct instanceof UnityProduct unityProduct) {
                unityProduct.setQuantity(unityProduct.getQuantity() + 1);
            } else if (existingProduct instanceof WeightedProduct weightedProduct) {
                productList.add(new WeightedProduct(ID, quantity));
            } else {
                throw new IllegalArgumentException("Product typology not supported");
            }

        } else {
            if (!productInterrogation.doMyProductExists(ID)) {
                throw new IllegalArgumentException("The read product is not present in the DB");
            }

            String typology = productInterrogation.getTypologyFromDB(ID);

            productList.add(productFactory.createProduct(ID, quantity, typology));
        }

    }

    public final void deleteProductFromMyList(BarCode barCode) throws Exception {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        for (Product p : productList) {
            if (!productEqualsBarcode(p, ID, quantity)) {
                continue;
            }

            if (p instanceof UnityProduct unityProduct) {
                unityProduct.setQuantity(unityProduct.getQuantity() - 1);
                if (Double.compare(unityProduct.getQuantity(), 0) == 0) {
                    productList.remove(unityProduct);
                }
            } else if (p instanceof WeightedProduct weightedProduct) {
                productList.remove(weightedProduct);
            } else {
                throw new IllegalArgumentException("Product typology not supported");
            }
        }
    }

    private Optional<Product> isProductAlreadyInMyList(int ID) {
        for (Product p : productList) {
            if (p.getID() == ID) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    private boolean productEqualsBarcode(Product product, int ID, double quantity) {
        if (product.getID() != ID) {
            return false;
        }

        if (product instanceof UnityProduct unityProduct) {
            return true;
        }

        if (product instanceof WeightedProduct weightedProduct) {
            return Double.compare(product.getQuantity(), quantity) == 0;
        }

        throw new IllegalArgumentException("Product typology not supported");
    }
}
