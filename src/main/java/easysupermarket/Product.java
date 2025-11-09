package easysupermarket;

public abstract class Product {
    private final int ID;
    private final String name;
    private final double pricePerUnit;
    private double quantity;

    public Product(int ID, String name, double pricePerUnity, double quantity) {
        this.ID = ID;
        this.name = name;
        this.pricePerUnit = pricePerUnity;
        this.quantity = quantity;
    }

    //il db ha solo id - nome - tipologia - prezzo

    public double getTotalPrice() {
        return this.quantity * this.pricePerUnit;
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

    @Override
    public abstract boolean equals(Object o);
}
