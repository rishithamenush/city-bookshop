import java.io.*;
import java.util.ArrayList;

public class Database {
    private String filename;
    private ArrayList<Book> books;

    public Database(String filename) {
        this.filename = filename;
        this.books = new ArrayList<>();
        loadBooks();
    }

private void loadBooks() {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if(parts.length < 4) {
                //System.err.println("Invalid line:  ");
                continue;
            }
            String title = parts[0];
            String category = parts[1];
            double price = Double.parseDouble(parts[2]);
            int stock = Integer.parseInt(parts[3]);
            books.add(new Book(title, category, price, stock));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void viewAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getCategory() + "," + book.getPrice() + "," + book.getStock());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
