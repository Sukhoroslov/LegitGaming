import java.util.*;

public class Board {
    private Army[] positions = new Army[16];

    public boolean isEmpty(int pos) {
        return positions[pos] == null;
    }

    public void placeArmy(int pos, Army army) {
        positions[pos] = army;
    }

    public Army getArmy(int pos) {
        return positions[pos];
    }

    public List<Integer> getEmptyPositions() {
        List<Integer> empty = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (positions[i] == null) empty.add(i);
        }
        return empty;
    }

    public void removeArmy(int pos) {
        positions[pos] = null;
    }

    public void display() {
        System.out.println("Board:");
        for (int i = 0; i < 16; i++) {
            if (positions[i] == null) {
                System.out.print("[  ] ");
            } else {
                System.out.printf("[%2s] ", positions[i].toString());
            }
            if ((i + 1) % 4 == 0) System.out.println();
        }
    }

    public boolean canBeDestroyed(int pos, Player attacker) {
        Army target = positions[pos];
        if (target == null || target.getOwner().equals(attacker)) return false;
        int left = pos - 1;
        int right = pos + 1;
        int sum = 0;
        if (left >= 0 && positions[left] != null && positions[left].getOwner().equals(attacker))
            sum += positions[left].getValue();
        if (right < 16 && positions[right] != null && positions[right].getOwner().equals(attacker))
            sum += positions[right].getValue();
        return sum > target.getValue();
    }

    public List<Integer> getDestroyablePositions(Player attacker) {
        List<Integer> destroyable = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (canBeDestroyed(i, attacker)) destroyable.add(i);
        }
        return destroyable;
    }

    public int countArmies(Player player) {
        int count = 0;
        for (Army a : positions) {
            if (a != null && a.getOwner().equals(player)) count++;
        }
        return count;
    }

    public int sumArmies(Player player) {
        int sum = 0;
        for (Army a : positions) {
            if (a != null && a.getOwner().equals(player)) sum += a.getValue();
        }
        return sum;
    }

    public boolean isFull() {
        for (Army a : positions) {
            if (a == null) return false;
        }
        return true;
    }
}