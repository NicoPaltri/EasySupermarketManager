package easysupermarket;

import java.util.Objects;

public class UnitProduct extends Product {
    public UnitProduct(int ID, double quantity, String name, double pricePerUnit) {
        super(ID, quantity, name, pricePerUnit);
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
