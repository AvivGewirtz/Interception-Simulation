import javax.swing.*;
import java.awt.*;

/**
 * The main application class for the Interception Simulation.
 * It sets up the user interface and initializes the simulation.
 */
class InterceptionSimulationApp {

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure GUI creation runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main application frame
            JFrame frame = new JFrame("Interception Simulation");

            // Initialize the simulation and control panel
            InterceptionSimulation simulation = new InterceptionSimulation();
            ControlPanel controlPanel = new ControlPanel(simulation);

            // Create the main panel and set its layout
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            // Create a ground panel to represent the bottom area (e.g., ground in the simulation)
            JPanel ground = new JPanel();
            ground.setBackground(Color.GREEN); // Set the ground color to green
            ground.setPreferredSize(new Dimension(100, 120)); // Set the preferred size for the ground panel

            // Add the simulation display to the center and the ground to the south of the main panel
            mainPanel.add(simulation, BorderLayout.CENTER);
            mainPanel.add(ground, BorderLayout.SOUTH);

            // Configure the frame layout
            frame.setLayout(new BorderLayout());
            frame.add(mainPanel, BorderLayout.CENTER); // Add the main panel to the center of the frame
            frame.add(controlPanel, BorderLayout.WEST); // Add the control panel to the bottom of the frame

            // Set frame properties
            frame.setSize(800, 800); // Set the frame size
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when the frame is closed
            frame.setVisible(true); // Make the frame visible
        });
    }
}

