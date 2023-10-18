import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaTicketBookingApp extends JFrame {

    private int total = 0;
    private final int VIP_PRICE = 20;
    private final int NORMAL_PRICE = 10;
    public CinemaTicketBookingApp() {
        // Set the layout manager for the frame
        setLayout(new GridLayout(4, 4, 20, 20)); // 4 rows, 4 columns, 20 pixels horizontal and vertical gap

        // Create a JLabel to represent the cinema screen and merge all columns
        JLabel screenLabel = new JLabel("Cinema Screen");
        screenLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
        screenLabel.setVerticalAlignment(JLabel.CENTER); // Center the text vertically
        screenLabel.setForeground(Color.WHITE); // Set text color to white

        // Set background color for the cinema screen label
        screenLabel.setOpaque(true);
        screenLabel.setBackground(Color.BLACK);

        // Merge all columns in the first row
        add(screenLabel);
        add(new JLabel()); // Empty placeholder
        add(new JLabel()); // Empty placeholder
        add(new JLabel()); // Empty placeholder


        // Create buttons and add them to the frame
        for (int row = 1; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JButton button = new JButton("Seat " + ((row - 1) * 4 + col + 1));
                button.setMargin(new Insets(10, 10, 10, 10)); // Add space around the button text

                // Set background color for VIP seats (center seats)
                if (row == 2 && (col == 1 || col == 2)) {
                    button.setBackground(Color.YELLOW);
                }
                // Add ActionListener to toggle the color of the seat when clicked
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
                            }
                            else {
                                total -= NORMAL_PRICE;
                            }

                        } else {
                            // Mark the seat as taken
                            button.setBackground(Color.RED);
                            button.setEnabled(true);
                            if(originalColor == Color.YELLOW) {
                                total += VIP_PRICE;
                            }
                            else {
                                total += NORMAL_PRICE;
                            }
                        }
                        isTaken = !isTaken;
                        System.out.println("Total Price: $" + total);

                    }
                });

                add(button);
            }
        }


        // Set frame properties
        setTitle("Cinema Ticket Booking");
        setSize(500, 400); // Increased frame size to accommodate additional space
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

}
