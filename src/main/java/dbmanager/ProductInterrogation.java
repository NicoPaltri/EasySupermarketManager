package dbmanager;

import easysupermarket.ProductTypology;

public interface ProductInterrogation {
    boolean doMyProductExists(int ID);

    ProductTypology getTypologyFromSource(int ID);

    String getNameFromSource(int ID);

    double getPricePerUnitFromSource(int ID);
}
