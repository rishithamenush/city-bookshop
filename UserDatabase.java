import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase { 
    private String filename;
    private Map<String, User> users;

    public UserDatabase(String filename) {
        this.filename = filename;
        this.users = new HashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                String role = parts[2];
                User user;
                if (role.equals("Cashier")) {
                    user = new Cashier(username, password, new Database("db.txt"));
                } else if (role.equals("Manager")) {
                    user = new Manager(username, password, new Database("db.txt"));
                } else {
                    continue; // Skip invalid role
                }
                users.put(username, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void register(User user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }

    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (User user : users.values()) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
