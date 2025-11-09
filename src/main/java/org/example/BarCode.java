package org.example;

public class BarCode {
    private final int ID;
    private final double quantity;

    public BarCode(int ID, int quantity) {
        this.ID = ID;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public double getQuantity() {
        return quantity;
    }
}
