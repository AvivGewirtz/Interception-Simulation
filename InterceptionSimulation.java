import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the Interception Simulation, which handles the graphical
 * rendering and the logic for simulating projectile and launcher interactions.
 */
public class InterceptionSimulation extends JPanel implements ActionListener {

    private Timer timer; // Timer to trigger the action events at fixed intervals
    private Projectile projectile; // The projectile object in the simulation
    private Launcher launcher; // The launcher object in the simulation
    private double time; // Time elapsed in the simulation
    private boolean running; // Flag to indicate if the simulation is running
    private Interception interception; // The interception logic for the projectile and launcher
    private double radarTime; // Time at which to calculate the radar intercept

    /**
     * Constructor initializes the simulation with default values and sets up the timer.
     */
    public InterceptionSimulation() {
        setPreferredSize(new Dimension(800, 600)); // Set the preferred size of the panel
        setBackground(Color.LIGHT_GRAY); // Set the background color of the panel
        timer = new Timer(16, this); // Timer to call actionPerformed every 16ms (~60fps)
        resetSimulation(); // Reset the simulation to its initial state
    }

    /**
     * Paints the graphical components of the simulation.
     * It includes drawing the projectile, launcher, impact lines, and grid.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass to ensure proper painting
        Graphics2D g2d = (Graphics2D) g;

        int panelHeight = getHeight();
        g2d.translate(0, panelHeight); // Adjust coordinate system so y increases downwards
        g2d.scale(1, -1); // Flip the y-axis

        // Draw the projectile and launcher on the panel
        projectile.draw(g2d);
        launcher.draw(g2d);

        // Draw the impact line from projectile to impact point
        g.setColor(Color.WHITE);
        g.drawLine((int) projectile.getX(), (int) projectile.getY(), (int) projectile.getTargetX(), 0);
        // Draw the interception line from launcher to interception point
        g.drawLine((int) launcher.getX(), 0, (int) interception.getIX(), (int) interception.getIY());

        // Draw grid lines
        for (int i = 1; i < 20; i++) {
            g.drawLine((int) i * 100, 0, (int) i * 100, (int) 1000);
        }
        for (int i = 1; i < 10; i++) {
            g.drawLine(0, i * 100, 2000, i * 100);
        }
    }

    /**
     * This method is called every time the timer ticks (approximately every 16 ms).
     * It updates the state of the simulation, checks for interception or bounds, and repaints the panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return; // Exit if the simulation is not running

        time += 0.016; // Increment the time by the elapsed frame time (~16 ms)
        projectile.update(time); // Update the projectile's position

        // If the time exceeds the radar time, calculate interception and update launcher
        if (time >= radarTime) {
            if (!interception.hasInterceptionCalculated) {
                interception.calcInterceptionPoint(radarTime);
                interception.print(); // Optionally print the interception information
            }
            interception.hasInterceptionCalculated = true;

            // If interception angle is too small or interception is impossible, stop simulation
            if (Math.toDegrees(interception.getLaunchAngle()) < 20 || (interception.getIX() == projectile.getX() && interception.getIY() == projectile.getY())) {
                running = false;
                timer.stop(); // Stop the timer
                JOptionPane.showMessageDialog(this, "Cannot intercept! Projectile is too fast");
                return;
            }
            launcher.update(time - radarTime, interception.getLaunchAngle()); // Update launcher position
        }

        // Check if the launcher intercepts the projectile
        if (launcher.intercepts(projectile)) {
            running = false; // Stop the simulation on successful interception
            timer.stop(); // Stop the timer
            JOptionPane.showMessageDialog(this, "Interception Successful!"); // Display success message
        }
        // Check if the projectile goes out of bounds
        else if (projectile.isOutOfBounds()) {
            running = false; // Stop the simulation on failure
            timer.stop(); // Stop the timer
            JOptionPane.showMessageDialog(this, "Interception Failed!"); // Display failure message
        }

        repaint(); // Repaint the panel to reflect the updated simulation state
    }

    /**
     * Starts the simulation with given parameters.
     * @param projectileX Initial x-coordinate of the projectile
     * @param projectileY Initial y-coordinate of the projectile
     * @param impactX x-coordinate where the projectile is aimed
     * @param launcherX x-coordinate of the launcher
     * @param projectileSpeed speed of the projectile
     * @param launcherSpeed speed of the launcher
     * @param radar time for the radar to calculate the interception
     */
    public void startSimulation(double projectileX, double projectileY, double impactX, double launcherX, double projectileSpeed, double launcherSpeed, double radar) {
        resetSimulation(); // Reset simulation before starting
        radarTime = radar; // Set radar time
        projectile = new Projectile(projectileX, projectileY, projectileSpeed, impactX); // Initialize projectile
        launcher = new Launcher(launcherX, launcherSpeed); // Initialize launcher
        interception = new Interception(projectile, launcher); // Initialize interception logic
        running = true; // Set the simulation state to running
        timer.start(); // Start the timer to begin the simulation
    }

    /**
     * Resets the simulation to its initial state.
     */
    public void resetSimulation() {
        projectile = new Projectile(); // Reset the projectile to default values
        launcher = new Launcher(); // Reset the launcher to default values
        interception = new Interception(projectile, launcher); // Reset the interception object
        time = 0; // Reset time to 0
        running = false; // Set simulation to not running
        timer.stop(); // Stop the timer
        repaint(); // Repaint the panel to reset the display
    }
}
