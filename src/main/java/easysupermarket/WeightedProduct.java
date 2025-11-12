package easysupermarket;

import java.util.Objects;

public class WeightedProduct extends Product {
    public WeightedProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    @Override
    protected boolean specificEquals(Product product) {
        return super.haveTheSameQuantity(product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID(), this.getQuantity());
    }
}
