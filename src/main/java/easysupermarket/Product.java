package easysupermarket;

import dbmanager.ProductInterrogation;
import dbmanager.DBProductInterrogation;

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

    //il db ha solo id - nome - tipologia - prezzo

    public double getTotalPrice() {
        return this.getQuantity() * this.getPricePerUnit();
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
    public final boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this.getID() != ((Product) o).getID()) {
            return false;
        }

        return specificEquals((Product) o);
    }

    protected boolean specificEquals(Product product) {
        return true;
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return "Product: ID" + ID +
                " [ name='" + name +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                " ]";
    }
}
