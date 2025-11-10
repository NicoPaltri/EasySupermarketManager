package easysupermarket;

public class UnityProduct extends Product {
    public UnityProduct(int ID, double quantity) {
        super(ID, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getID() == ((UnityProduct) o).getID();
    }
}
