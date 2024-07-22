import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Wallet {
    int bankBalance = 1000, pocketBalance = 0;
    Log log = new Log();
    public int deposit(int amount) {
        if (pocketBalance < amount) {
            return 2;
        } else if (!(amount > 0)) {
            return 3;
        }
        bankBalance += amount;
        pocketBalance -= amount;
        displayStatus();
        log.log.add("Deposit %d lira".formatted(amount));
        return 0;
    }

    public int withdraw(int amount) {
        if (bankBalance < amount) {
            return 1;
        } else if (!(amount > 0)) {
            return 3;
        }
        bankBalance -= amount;
        pocketBalance += amount;
        displayStatus();
        log.log.add("Withdraw %d lira".formatted(amount));
        return 0;
    }

    public void displayStatus() {
        System.out.println("Account balance is: " + bankBalance);
        System.out.printf("You have %d lira in your pocket%n", pocketBalance);
    }
}

class Log {
    List<String> log = new ArrayList<>();
    public int printLog() {
        if (log.isEmpty()){return 4;}
        for (String i : log) {
            System.out.println(i);
        }
        return 0;
    }
}

class Error {
    static String getErrorMessage(int errorCode) {
        return switch (errorCode) {
            case 0 -> "Successful";
            case 1 -> "Insufficient Balance";
            case 2 -> "Insufficient Pocket Money";
            case 3 -> "Must be greater than zero";
            case 4 -> "Empty";
            default -> "";
        };
    }
}

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static void main(String[] args) {
        Wallet ourWallet = new Wallet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select an operation \n d:deposit, w:withdraw, s:status, h:history");
            String input = scanner.next().toLowerCase();
            switch (input) {
                case "d":
                    System.out.print("Enter the amount you wish to deposit: ");
                    System.out.println(Error.getErrorMessage(ourWallet.deposit(scanner.nextInt())));
                    break;
                case "w":
                    System.out.print("Enter the amount you wish to withdraw: ");
                    System.out.println(Error.getErrorMessage(ourWallet.withdraw(scanner.nextInt())));
                    break;
                case "h":
                    System.out.println(Error.getErrorMessage(ourWallet.log.printLog()));
                    break;
                case "s":
                    ourWallet.displayStatus();
                    break;
                default:
                    System.out.println("Unknown request");
            }
            System.out.println();
        }
    }
}