package org.example;

public abstract class Product {
    private final int ID;
    private final String name;
    private final double pricePerUnity;
    private double quantity;

    private double totalPrice;

    public Product(int ID, String name, double pricePerUnity, double quantity) {
        this.ID = ID;
        this.name = name;
        this.pricePerUnity = pricePerUnity;
        this.quantity = quantity;

        this.updatePrice();
    }

    //il db ha solo id - nome - tipologia - prezzo

    private void updatePrice() {
        this.setTotalPrice(this.quantity * this.pricePerUnity);
    }

    private void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnity() {
        return pricePerUnity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        this.updatePrice();
    }

    @Override
    public abstract boolean equals(Object o);
}
