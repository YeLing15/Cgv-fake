import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login1 extends JFrame {
    

    private Connection connection;

    public Login1() {
        showLoginWindow();
    }

    private void initializeMainApp() {
        System.out.println("Main application initialized!");
    }

    private void showLoginWindow() {
        connectToDatabase(); 

        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); 
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                if (authenticate(username, password)) {
                    loginFrame.dispose();
                    initializeMainApp();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private void connectToDatabase() {
        try {
  
            String url = "jdbc:mysql://localhost:3306";
            String user = "host";
            String password = "Dalinh31321";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, char[] password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, new String(password));

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
