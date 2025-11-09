package easysupermarket;

public class WeightedProduct extends Product {
    public WeightedProduct(int ID, String name, double pricePerUnity, double quantity) {
        super(ID, name, pricePerUnity, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getID() == ((WeightedProduct) o).getID() && this.getQuantity() == ((WeightedProduct) o).getQuantity();
    }

}
