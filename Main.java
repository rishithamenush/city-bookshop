import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDatabase userDb = new UserDatabase("userDb.txt");
        Database db = new Database("db.txt");
        User currentUser = null;

        while (true) {
            System.out.println("════════════════════════════════════════");
            System.out.println("      Welcome to City Bookshop!          ");
            System.out.println("════════════════════════════════════════\n");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("════════════════════════════════════════\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    currentUser = userDb.getUser(username);

                    if (currentUser != null && currentUser.getPassword().equals(password)) {
                        currentUser.menu();
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    System.out.print("Enter a new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter a new password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter the account type (Cashier/Manager): ");
                    String accountType = scanner.nextLine();

                    if (accountType.equalsIgnoreCase("Cashier")) {
                        userDb.register(new Cashier(newUsername, newPassword, db));
                    } else if (accountType.equalsIgnoreCase("Manager")) {
                        userDb.register(new Manager(newUsername, newPassword, db));
                    } else {
                        System.out.println("Invalid account type.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}