package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.ProductDBInterrogation;

import java.util.Objects;

public abstract class Product {
    private final int ID;
    private double quantity;

    ProductInterrogation productInterrogation;

    private final String name;
    private final double pricePerUnit;

    public Product(int ID, double quantity) {
        this.ID = ID;
        this.quantity = quantity;

        this.productInterrogation = new ProductDBInterrogation();

        this.name = productInterrogation.getNameFromDB(ID);
        this.pricePerUnit = productInterrogation.getPricePerUnitFromDB(ID);
    }

    //il db ha solo id - nome - tipologia - prezzo

    public double getTotalPrice() {
        return this.quantity * this.pricePerUnit;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean haveTheSameQuantity(Product p2) {
        return Double.compare(this.getQuantity(), p2.getQuantity()) == 0;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public int hashCode() {
        return Objects.hash(ID, quantity);
    }
}
