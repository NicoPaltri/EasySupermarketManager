package easysupermarket;

public enum ProductTypology {
    UNITY_PRODUCT(1),
    WEIGHTED_PRODUCT(2);

    private final int value;

    ProductTypology(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
