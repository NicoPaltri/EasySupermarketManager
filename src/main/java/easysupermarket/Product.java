package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.DBProductInterrogation;

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

        this.productInterrogation = new DBProductInterrogation();

        this.name = productInterrogation.getNameFromSource(ID);
        this.pricePerUnit = productInterrogation.getPricePerUnitFromSource(ID);
    }

    //The DB contains: id - name - pricePerUnit - typology

    public double getTotalPrice() {
        return this.getQuantity() * this.getPricePerUnit();
    }

    public int getID() {
        return ID;
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
    public final boolean equals(Object o) {

        if (!equalsClass(o)) {
            return false;
        }

        if (this.getID() != ((Product) o).getID()) {
            return false;
        }

        return specificEquals((Product) o);
    }

    public boolean equalsBarCode(int id, double quantity) {
        if (this.getID() != id) {
            return false;
        }

        if (this instanceof UnitProduct unitProduct) {
            return true;
        } else if (this instanceof WeightedProduct weightedProduct) {
            return Double.compare(weightedProduct.getQuantity(), quantity) == 0;
        }

        throw new IllegalArgumentException("Product typology not supported");
    }

    private boolean equalsClass(Object o) {
        return o != null && this.getClass() == o.getClass();
    }

    protected boolean specificEquals(Product product) {
        return true;
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return "Product: ID " + ID +
                " [ name='" + name +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                " ]";
    }
}
