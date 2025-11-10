package easysupermarket;

public class UnitProduct extends Product {
    public UnitProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getID() == ((UnitProduct) o).getID();
    }
}
