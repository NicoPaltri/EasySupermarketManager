package org.example;

public class BarCode {
    private final int ID;
    private final int quantity;

    public BarCode(int ID, int quantity) {
        this.ID = ID;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public int getQuantity() {
        return quantity;
    }
}
