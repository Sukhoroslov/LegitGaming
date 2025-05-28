import java.util.*;

public class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer(String name, String symbol) {
        super(name, symbol);
        this.random = new Random();
    }

    @Override
    public int chooseArmy(Board board) {
        List<Integer> armies = new ArrayList<>(availableArmies);
        return armies.get(random.nextInt(armies.size()));
    }

    @Override
    public int choosePosition(Board board) {
        List<Integer> empty = board.getEmptyPositions();
        return empty.get(random.nextInt(empty.size()));
    }

    @Override
    public int chooseDestruction(Board board) {
        List<Integer> destroyable = board.getDestroyablePositions(this);
        if (destroyable.isEmpty()) return -1;
        return destroyable.get(random.nextInt(destroyable.size()));
    }
}