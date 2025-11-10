package dbmanager;

import easysupermarket.ProductTypology;

public interface ProductInterrogation {
    boolean doMyProductExists(int ID);

    ProductTypology getTypologyFromDB(int ID);

    String getNameFromDB(int ID);

    double getPricePerUnitFromDB(int ID);
}
