import java.util.Scanner;

public class Manager extends User {
    private Database database;
    private Scanner scanner;

    public Manager(String username, String password, Database database) {
        super(username, password); 
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View all books");
            System.out.println("2. Search books by category");
            System.out.println("3. Search books by title");
            System.out.println("4. Search books by price range");
            System.out.println("5. Add a new book");
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
                    addNewBook();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
    @Override
    public String getRole() {
        return "Manager";
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

    private void addNewBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the category of the book: ");
        String category = scanner.nextLine();
        System.out.print("Enter the price of the book: ");
        double price = scanner.nextDouble();
        System.out.print("Enter the stock quantity of the book: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book book = new Book(title, category, price, stock);
        database.addBook(book);
        System.out.println("Book added successfully.");
    }

    
}