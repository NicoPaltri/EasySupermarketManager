package easysupermarket;

import java.util.Objects;

public class UnitProduct extends Product {
    public UnitProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    public boolean hasIntegerQuantity() {
        return hasIntegerQuantity(this.getQuantity());
    }

    public static boolean hasIntegerQuantity(double quantity) {
        return (int) quantity == quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID());
    }
}
