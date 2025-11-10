package easysupermarket;

public class WeightedProduct extends Product {
    public WeightedProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return this.getID() == ((WeightedProduct) o).getID() && this.haveTheSameQuantity((WeightedProduct) o);
    }
}
