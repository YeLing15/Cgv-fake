import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaTicketBookingApp extends JFrame {

    private int total = 0;
    private final int VIP_PRICE = 20;
    private final int NORMAL_PRICE = 10;
    private JLabel totalLabel;
    private JLabel screen;
    private JButton check;
    private int handleDoneButtonClick() {
        // Do whatever you want with the total value when the Done button is clicked
        return total;
    }
    public int getTotal() {
        return total;
    }
    public CinemaTicketBookingApp() {
        // Set the layout manager for the frame
        setLayout(new GridLayout(6, 5, 20, 20)); // Increased rows to accommodate the total label

        screen = new JLabel("Cinema Screen");
        screen.setHorizontalAlignment(JLabel.CENTER);
        add(new JLabel());
        add(new JLabel());
        add(screen);
        add(new JLabel()); // Empty placeholder
        add(new JLabel()); // Empty placeholder

        int dem = 1;
        // Create buttons and add them to the frame
        for (int row = 1; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton("Seat " + dem);
                dem++;
                button.setMargin(new Insets(10, 10, 10, 10)); // Add space around the button text

                // Set background color for VIP seats (center seats)
                if ((row == 2 && (col == 1 || col == 2 || col == 3))  || (row == 3 && (col == 1 || col == 2 || col == 3))) {
                    button.setBackground(Color.YELLOW);
                }

                // Add ActionListener to toggle the color of the seat and calculate the total price
                button.addActionListener(new ActionListener() {
                    private boolean isTaken = false;
                    private Color originalColor = button.getBackground();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isTaken) {
                            // If already taken, reset to original color
                            button.setBackground(originalColor);
                            button.setEnabled(true);
                            if (originalColor == Color.YELLOW) {
                                total -= VIP_PRICE;
                            } else {
                                total -= NORMAL_PRICE;
                            }
                        } else {
                            // Mark the seat as taken
                            button.setBackground(Color.RED);
                            button.setEnabled(true);
                            if (originalColor == Color.YELLOW) {
                                total += VIP_PRICE;
                            } else {
                                total += NORMAL_PRICE;
                            }
                        }
                        isTaken = !isTaken;

                        // Update the total label
                        totalLabel.setText("Total Price: $" + total);
                    }
                });

                add(button);
            }
        }

        // Create the total label
        totalLabel = new JLabel("Total Price: $" + total);
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        add(totalLabel);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());

        check = new JButton("Done");
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a separate method to handle the action and return the total
                int result = handleDoneButtonClick();
                System.out.println("Total Price: $" + result);
            }
        });
        add(check);

        // Set frame properties
        setTitle("Cinema Ticket Booking");
        setSize(700, 500); // Increased frame size to accommodate additional space for the total label
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        ImageIcon image = new ImageIcon("MU.png");
        setIconImage(image.getImage());

    }

}
