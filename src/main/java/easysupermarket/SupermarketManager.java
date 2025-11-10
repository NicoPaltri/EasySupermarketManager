package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.DBProductInterrogation;
import easysupermarket.concreteresources.BarCode;
import easysupermarket.concreteresources.ScannerGun;

import java.util.ArrayList;
import java.util.Iterator;
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
        this.productInterrogation = new DBProductInterrogation();
    }

    public final void insertProductInMyList(BarCode barCode) throws Exception {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        Optional<Product> product = isAKindProductAlreadyInMyList(ID);

        if (product.isPresent()) {
            Product existingProduct = product.get();

            if (existingProduct instanceof UnitProduct unitProduct) {
                unitProduct.setQuantity(unitProduct.getQuantity() + 1);
            } else if (existingProduct instanceof WeightedProduct weightedProduct) {
                productList.add(new WeightedProduct(ID, quantity));
            } else {
                throw new IllegalArgumentException("Product typology not supported");
            }

        } else {
            if (!productInterrogation.doMyProductExists(ID)) {
                throw new IllegalArgumentException("The read product is not present in the DB");
            }

            productList.add(productFactory.createProduct(ID, quantity, productInterrogation.getTypologyFromSource(ID)));
        }

    }

    public final void deleteProductFromMyList(BarCode barCode) throws Exception {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        Iterator<Product> removingProductIterator = productList.iterator();
        while (removingProductIterator.hasNext()) {
            Product p = removingProductIterator.next();

            if (!productEqualsBarcode(p, ID, quantity)) {
                continue;
            }

            if (p instanceof UnitProduct unitProduct) {
                unitProduct.setQuantity(unitProduct.getQuantity() - 1);
                if (unitProduct.getQuantity() <= 0) {
                    removingProductIterator.remove();
                }
            } else if (p instanceof WeightedProduct) {
                removingProductIterator.remove();
            } else {
                throw new IllegalArgumentException("Product typology not supported");
            }
        }

    }

    private Optional<Product> isAKindProductAlreadyInMyList(int ID) {
        return productList.stream().filter(p -> p.getID() == ID).findFirst();
    }

    private boolean productEqualsBarcode(Product product, int ID, double quantity) {
        if (product.getID() != ID) {
            return false;
        }

        if (product instanceof UnitProduct unitProduct) {
            return true;
        }

        if (product instanceof WeightedProduct weightedProduct) {
            return weightedProduct.haveTheSameQuantity(product);
        }

        throw new IllegalArgumentException("Product typology not supported");
    }
}
