import java.util.*;

public class Game {
    private Board board;
    private Player human, computer;
    private Player current, other;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        human = new HumanPlayer("White (You)", "W", scanner);
        computer = new ComputerPlayer("Black (Computer)", "B");
    }

    public void start() {
        System.out.println("Welcome to Deploy-Destroy!");
        System.out.println("Tossing coin to decide who starts...");
        if (new Random().nextBoolean()) {
            current = human;
            other = computer;
            System.out.println("You start first!");
        } else {
            current = computer;
            other = human;
            System.out.println("Computer starts first!");
        }
        deploymentPhase();
        destructionPhase();
        determineWinner();
    }

    private void deploymentPhase() {
        System.out.println("\n--- Deployment Phase ---");
        while (!board.isFull()) {
            board.display();
            System.out.println(current.getName() + "'s turn to deploy.");
            System.out.println("Available armies: " + current.getAvailableArmies());
            int army = current.chooseArmy(board);
            int pos = current.choosePosition(board);
            board.placeArmy(pos, new Army(army, current));
            current.removeArmy(army);
            swapPlayers();
        }
    }

    private void destructionPhase() {
        System.out.println("\n--- Destruction Phase ---");
        boolean anyoneCanDestroy = true;
        while (anyoneCanDestroy) {
            anyoneCanDestroy = false;
            for (int i = 0; i < 2; i++) {
                board.display();
                List<Integer> destroyable = board.getDestroyablePositions(current);
                if (!destroyable.isEmpty()) {
                    anyoneCanDestroy = true;
                    System.out.println(current.getName() + "'s turn to destroy.");
                    int pos;
                    if (current instanceof HumanPlayer) {
                        pos = current.chooseDestruction(board);
                    } else {
                        pos = current.chooseDestruction(board);
                        System.out.println("Computer destroys at position: " + pos);
                    }
                    board.removeArmy(pos);
                }
                swapPlayers();
            }
        }
    }

    private void swapPlayers() {
        Player temp = current;
        current = other;
        other = temp;
    }

    private void determineWinner() {
        board.display();
        int humanCount = board.countArmies(human);
        int computerCount = board.countArmies(computer);
        System.out.println("\nGame Over!");
        System.out.println("Your armies left: " + humanCount + " (sum: " + board.sumArmies(human) + ")");
        System.out.println("Computer armies left: " + computerCount + " (sum: " + board.sumArmies(computer) + ")");
        if (humanCount > computerCount) {
            System.out.println("Congratulations! You win!");
        } else if (computerCount > humanCount) {
            System.out.println("Computer wins!");
        } else {
            int humanSum = board.sumArmies(human);
            int computerSum = board.sumArmies(computer);
            if (humanSum > computerSum) {
                System.out.println("Congratulations! You win (by sum)!");
            } else if (computerSum > humanSum) {
                System.out.println("Computer wins (by sum)!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}