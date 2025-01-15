import java.awt.*;

/**
 * The Launcher class represents a launcher that moves at a defined speed and angle
 * to intercept a projectile. It includes functionality for updating its position,
 * checking interception, and drawing itself on a graphical interface.
 */
public class Launcher {
    private double x, y;           // Initial position of the launcher
    private double currentX, currentY; // Current position of the launcher during the simulation
    private double speed;         // Speed of the launcher

    /**
     * Constructor to initialize the launcher with specified starting position and speed.
     *
     * @param x     The initial X-coordinate of the launcher.
     * @param speed The speed of the launcher.
     */
    public Launcher(double x, double speed) {
        this.x = x;
        this.y = 0;               // Launcher always starts at Y = 0
        this.speed = speed;       // Set the speed of the launcher
        currentX = x;             // Initialize the current X position
        currentY = y;             // Initialize the current Y position
    }

    /**
     * Default constructor to initialize the launcher using default values from the Value class.
     */
    public Launcher() {
        this.x = Value.launcherXDefault; // Default X-coordinate
        this.y = 0;                      // Launcher always starts at Y = 0
        this.speed = Value.speedDefault; // Default speed
        currentX = x;                    // Initialize the current X position
        currentY = y;                    // Initialize the current Y position
    }

    /**
     * Updates the current position of the launcher based on time and launch angle.
     *
     * @param time        The time elapsed since the simulation started.
     * @param launchAngle The angle of the launch in radians.
     */
    public void update(double time, double launchAngle) {
        // Update the current X and Y positions based on speed, time, and angle
        currentX = x + speed * time * Math.cos(launchAngle);
        currentY = y + speed * time * Math.sin(launchAngle);
    }

    /**
     * Gets the initial X-coordinate of the launcher.
     *
     * @return The initial X-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the initial Y-coordinate of the launcher.
     *
     * @return The initial Y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the current X-coordinate of the launcher.
     *
     * @return The current X-coordinate.
     */
    public double getCurrentX() {
        return currentX;
    }

    /**
     * Gets the speed of the launcher.
     *
     * @return The speed of the launcher.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Checks if the launcher intercepts the projectile.
     *
     * @param projectile The projectile to check against.
     * @return True if the launcher is within 20 units of the projectile; otherwise, false.
     */
    public boolean intercepts(Projectile projectile) {
        // Calculate the distance between the launcher and the projectile
        return Math.hypot(currentX - projectile.getCurrentX(), currentY - projectile.getCurrentY()) < 20;
    }

    /**
     * Draws the launcher on the graphical interface.
     *
     * @param g The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);  // Set the color to blue
        // Draw the launcher as a filled oval at its current position
        g.fillOval((int) currentX, (int) currentY, 15, 15);
    }
}
