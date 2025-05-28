import java.util.*;

public abstract class Player {
    protected String name;
    protected String symbol;
    protected Set<Integer> availableArmies;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.availableArmies = new HashSet<>();
        for (int i = 1; i <= 8; i++) {
            availableArmies.add(i);
        }
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Set<Integer> getAvailableArmies() {
        return availableArmies;
    }

    public void removeArmy(int value) {
        availableArmies.remove(value);
    }

    public abstract int chooseArmy(Board board);

    public abstract int choosePosition(Board board);

    public abstract int chooseDestruction(Board board);

    public boolean hasAvailableArmies() {
        return !availableArmies.isEmpty();
    }
}