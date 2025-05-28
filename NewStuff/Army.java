public class Army {
    private final int value;
    private final Player owner;

    public Army(int value, Player owner) {
        this.value = value;
        this.owner = owner;
    }

    public int getValue() {
        return value;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return owner.getSymbol() + value;
    }
}
