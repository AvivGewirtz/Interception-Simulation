import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ControlPanel class extends JPanel and provides a user interface
 * for controlling the InterceptionSimulation. It includes buttons to start
 * and reset the simulation, as well as configurable parameters for the simulation settings.
 */
public class ControlPanel extends JPanel {

    /**
     * Constructor to initialize the control panel with buttons and parameters.
     *
     * @param simulation The simulation object that the control panel interacts with.
     */
    public ControlPanel(InterceptionSimulation simulation) {
        setLayout(new FlowLayout()); // Set the layout to FlowLayout for arranging components.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create the Start Simulation and Reset buttons
        JButton startButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");

        // Disable focus traversal for buttons to prevent accidental key focus
        startButton.setFocusable(false);
        resetButton.setFocusable(false);

        // Create Parameter objects for various simulation settings
        Parameter projectileX = new Parameter(Value.projectileXDefault, Value.projectileXMin, Value.projectileXMax, Value.step, "Projectile x:");
        Parameter projectileY = new Parameter(Value.projectileYDefault, Value.projectileYMin, Value.projectileYMax, Value.step, "Projectile y:");
        Parameter projectileSpeed = new Parameter(Value.speedDefault, Value.speedMin, Value.speedMax, Value.step, "Projectile speed:");
        Parameter impactX = new Parameter(Value.impactXDefault, Value.impactXMin, Value.impactXMax, Value.step, "Impact x:");
        Parameter launcherX = new Parameter(Value.launcherXDefault, Value.launcherXMin, Value.launcherXMax, Value.step, "Launcher x:");
        Parameter launcherSpeed = new Parameter(Value.speedDefault, Value.speedMin, Value.speedMax, Value.step, "Launcher speed:");
        Parameter radar = new Parameter(Value.radarDefault, Value.radarMin, Value.radarMax, Value.radarStep, "Radar time:");

        // Add ActionListener to the Start Simulation button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the simulation with values from the parameter spinners
                simulation.startSimulation((int) projectileX.spinner.getValue(),
                        (int) projectileY.spinner.getValue(),
                        (int) impactX.spinner.getValue(),
                        (int) launcherX.spinner.getValue(),
                        (int) projectileSpeed.spinner.getValue(),
                        (int) launcherSpeed.spinner.getValue(),
                        (double) radar.spinner.getValue());
            }
        });

        // Add ActionListener to the Reset button to reset the simulation
        resetButton.addActionListener(e -> simulation.resetSimulation());

        // Add the parameters to the control panel
        addParameter(projectileX);
        addParameter(projectileY);
        addParameter(impactX);
        addParameter(launcherX);
        addParameter(projectileSpeed);
        addParameter(launcherSpeed);
        addParameter(radar);

        // Add the buttons to the control panel
        add(startButton);
        add(resetButton);

        // Set the preferred size of the control panel
        setPreferredSize(new Dimension(120, 50));
    }

    /**
     * Adds a parameter to the control panel.
     *
     * @param p The Parameter object to add, including its label and spinner.
     */
    private void addParameter(Parameter p) {
        add(p.label);   // Add the parameter's label
        add(p.spinner); // Add the parameter's spinner
    }
}
