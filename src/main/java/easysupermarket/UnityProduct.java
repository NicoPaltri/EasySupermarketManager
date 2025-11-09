package easysupermarket;

public class UnityProduct extends Product {
    public UnityProduct(int ID, String name, double pricePerUnity, double quantity) {
        super(ID, name, pricePerUnity, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getID() == ((UnityProduct) o).getID();
    }
}
