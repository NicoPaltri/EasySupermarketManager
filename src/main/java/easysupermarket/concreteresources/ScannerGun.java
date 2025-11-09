package easysupermarket.concreteresources;

public class ScannerGun {
    public ScannerGun() {
    }

    public int obtainIDFromBarcode(BarCode barCode) {
        return barCode.getID();
    }

    public double obtainQuantityFromBarcode(BarCode barCode) {
        return barCode.getQuantity();
    }
}
