package org.example;

public class ScannerGun {
    public ScannerGun() {
    }

    public int obtainIDFromBarcode(BarCode barCode) {
        return barCode.getID();
    }

    public double obtainQuantityFromBarcod(BarCode barCode) {
        return barCode.getQuantity();
    }
}
