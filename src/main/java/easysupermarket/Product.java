package easysupermarket;

public abstract class Product {
    private final int ID;
    private double quantity;
    private final String name;
    private final double pricePerUnit;

    public Product(int ID, double quantity, String name, double pricePerUnit) {
        this.ID = ID;
        this.quantity = quantity;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    //The DB contains: id - name - pricePerUnit - typology

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
            if (!unitProduct.hasIntegerQuantity()) {
                throw new IllegalArgumentException("Barcode error: double quantity for UnityProduct");
            }
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
