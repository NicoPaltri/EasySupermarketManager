package dbmanager;

public interface ProductInterrogation {
    boolean doMyProductExists(int ID);

    String getTypologyFromDB(int ID);

    String getNameFromDB(int ID);

    double getPricePerUnitFromDB(int ID);
}
