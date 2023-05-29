import java.util.Scanner;

public class Cashier extends User {
    private Database database; 
    private Scanner scanner;

    public Cashier(String username, String password, Database database) {
        super(username, password);
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Cashier Menu ---");
            System.out.println("1. View all books");
            System.out.println("2. Search books by category");
            System.out.println("3. Search books by title");
            System.out.println("4. Search books by price range");
            System.out.println("5. Update book stock");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    database.viewAllBooks();
                    break;
                case 2:
                    System.out.print("Enter the category: ");
                    String category = scanner.nextLine();
                    searchBooksByCategory(category);
                    break;
                case 3:
                    System.out.print("Enter the title: ");
                    String title = scanner.nextLine();
                    searchBooksByTitle(title);
                    break;
                case 4:
                    System.out.print("Enter the minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter the maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    searchBooksByPriceRange(minPrice, maxPrice);
                    break;
                case 5:
                    System.out.print("Enter the title of the book to update stock: ");
                    String bookTitle = scanner.nextLine();
                    updateBookStock(bookTitle);
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
    public String getRole() {
        return "Cashier";
    }

    private void searchBooksByCategory(String category) {
        for (Book book : database.getBooks()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                System.out.println(book);
            }
        }
    }

    private void searchBooksByTitle(String title) {
        for (Book book : database.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
            }
        }
    }

    private void searchBooksByPriceRange(double minPrice, double maxPrice) {
        for (Book book : database.getBooks()) {
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                System.out.println(book);
            }
        }
    }

    private void updateBookStock(String title) {
        for (Book book : database.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter the new stock quantity: ");
                int newStock = scanner.nextInt();
                book.setStock(newStock);
                database.saveBooks();
                System.out.println("Stock updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
}