import java.awt.*;

/**
 * The Projectile class represents a projectile that moves toward a target location.
 * It includes functionality for updating its position, checking if it is out of bounds,
 * and drawing itself on a graphical interface.
 */
public class Projectile {
    private double x, y;             // Initial position of the projectile
    private double targetX, targetY; // Target position of the projectile
    private double currentX, currentY; // Current position of the projectile during simulation
    private double speed;           // Speed of the projectile

    /**
     * Constructor to initialize the projectile with specified starting position, speed, and target.
     *
     * @param x       The initial X-coordinate of the projectile.
     * @param y       The initial Y-coordinate of the projectile.
     * @param speed   The speed of the projectile.
     * @param targetX The target X-coordinate for the projectile.
     */
    public Projectile(double x, double y, double speed, double targetX) {
        this.x = x;                   // Set initial X position
        this.y = y;                   // Set initial Y position
        this.speed = speed;           // Set the speed of the projectile
        this.targetX = targetX;       // Set the target X position
        this.targetY = 0;             // Projectiles always aim for Y = 0
        currentX = x;                 // Initialize the current X position
        currentY = y;                 // Initialize the current Y position
    }

    /**
     * Default constructor to initialize the projectile using default values from the Value class.
     */
    public Projectile() {
        this.x = Value.projectileXDefault; // Default X-coordinate
        this.y = Value.projectileYDefault; // Default Y-coordinate
        this.speed = Value.speedDefault;   // Default speed
        this.targetX = Value.impactXDefault; // Default target X-coordinate
        this.targetY = 0;                  // Projectiles always aim for Y = 0
        currentX = x;                      // Initialize the current X position
        currentY = y;                      // Initialize the current Y position
    }

    // Getter methods for the initial and current positions, target, and speed
    public double getX() { return x; }
    public double getY() { return y; }
    public double getTargetX() { return targetX; }
    public double getTargetY() { return targetY; }
    public double getCurrentX() { return currentX; }
    public double getCurrentY() { return currentY; }
    public double getSpeed() { return speed; }

    /**
     * Updates the current position of the projectile based on the elapsed time.
     *
     * @param time The time elapsed since the simulation started.
     */
    public void update(double time) {
        // Calculate the distance between the initial position and the target
        double distance = Math.hypot(targetX - x, targetY - y);

        // Calculate the velocity components in the X and Y directions
        double vx = Utility.calcVelocity(x, targetX, distance, speed);
        double vy = Utility.calcVelocity(y, targetY, distance, speed);

        // Update the current position based on time and velocity
        currentX = x + vx * time;
        currentY = y + vy * time;
    }

    /**
     * Checks if the projectile has moved out of bounds.
     *
     * @return True if the projectile's Y-coordinate is less than 0; otherwise, false.
     */
    public boolean isOutOfBounds() {
        return currentY < 0;
    }

    /**
     * Draws the projectile on the graphical interface.
     *
     * @param g The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.RED); // Set the color to red
        // Draw the projectile as a filled oval at its current position
        g.fillOval((int) currentX, (int) currentY, 15, 15);
    }
}
