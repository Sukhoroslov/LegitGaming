import java.util.*;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(String name, String symbol, Scanner scanner) {
        super(name, symbol);
        this.scanner = scanner;
    }

    @Override
    public int chooseArmy(Board board) {
        System.out.print("Available armies: ");
        for (int a : availableArmies) System.out.print(a + " ");
        System.out.println();
        int army;
        while (true) {
            System.out.print("Choose an army to deploy: ");
            try {
                army = Integer.parseInt(scanner.nextLine());
                if (availableArmies.contains(army)) {
                    return army;
                } else {
                    System.out.println("Invalid army. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    @Override
    public int choosePosition(Board board) {
        int pos;
        while (true) {
            System.out.print("Choose an empty position (0-15): ");
            try {
                pos = Integer.parseInt(scanner.nextLine());
                if (pos >= 0 && pos < 16 && board.isEmpty(pos)) {
                    return pos;
                } else {
                    System.out.println("Invalid or occupied position. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    @Override
    public int chooseDestruction(Board board) {
        while (true) {
            System.out.print("Enter position of enemy army to destroy (0-15): ");
            try {
                int pos = Integer.parseInt(scanner.nextLine());
                if (pos >= 0 && pos < 16 && board.canBeDestroyed(pos, this)) {
                    return pos;
                } else {
                    System.out.println("Invalid target. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}