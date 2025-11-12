package easysupermarket;

import java.util.Objects;

public class UnitProduct extends Product {
    public UnitProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    public boolean hasIntegerQuantity() {
        return (int) this.getQuantity() == this.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID());
    }
}
