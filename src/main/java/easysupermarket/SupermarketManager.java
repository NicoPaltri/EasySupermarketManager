package easysupermarket;

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

    public SupermarketManager() {
        this.scannerGun = new ScannerGun();
        this.productList = new ArrayList<>();
        this.productFactory = new ProductFactory();
    }

    public void insertProductInMyList(BarCode barCode) {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        Optional<Product> product = isAKindProductAlreadyInMyList(ID);

        if (product.isPresent()) {
            Product existingProduct = product.get();

            if (existingProduct instanceof UnitProduct unitProduct) {
                if (!UnitProduct.hasIntegerQuantity(quantity)) {
                    throw new IllegalArgumentException("Barcode error: double quantity for UnityProduct");
                }
                unitProduct.setQuantity(unitProduct.getQuantity() + quantity);

                return;
            }

            if (existingProduct instanceof WeightedProduct weightedProduct) {
                productList.add(productFactory.createProduct(ID, quantity, weightedProduct.getName(), weightedProduct.getPricePerUnit(), ProductTypology.WEIGHTED_PRODUCT));

                return;
            }

            throw new IllegalArgumentException("Product typology not supported");
        } else {
            productList.add(productFactory.createProduct(ID, quantity));
        }
    }

    public void deleteProductFromMyList(BarCode barCode) {
        int ID = scannerGun.obtainIDFromBarcode(barCode);
        double quantity = scannerGun.obtainQuantityFromBarcode(barCode);

        Iterator<Product> removingProductIterator = productList.iterator();
        while (removingProductIterator.hasNext()) {
            Product p = removingProductIterator.next();

            if (!p.equalsBarCode(ID, quantity)) {
                continue;
            }

            if (p instanceof UnitProduct unitProduct) {
                unitProduct.setQuantity(unitProduct.getQuantity() - quantity);
                if (unitProduct.getQuantity() <= 0) {
                    removingProductIterator.remove();
                }

                return;
            }

            if (p instanceof WeightedProduct) {
                removingProductIterator.remove();

                return;
            }

            throw new IllegalArgumentException("Product typology not supported");
        }

        throw new IllegalArgumentException("Read a BarCode which was not in the list");
    }


    private Optional<Product> isAKindProductAlreadyInMyList(int ID) {
        return productList.stream().filter(p -> p.getID() == ID).findFirst();
    }

    public List<Product> getProductList() {
        return productList;
    }
}
