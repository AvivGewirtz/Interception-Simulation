/**
 * The Interception class calculates the interception point between a projectile and a launcher.
 * It calculates the required launch angle, velocity, and time to intercept the target.
 */
public class Interception {

    private final Projectile projectile;
    private final Launcher launcher;
    private double launchAngle = 0;
    private double iX, iY; // The interception point's coordinates
    private double distanceFromIdentificationToImpact; // Distance from identification point to impact point
    private double vX; // Velocity of the launcher in the x-direction
    private double vY; // Velocity of the launcher in the y-direction
    private double t; // Time required for interception

    public boolean hasInterceptionCalculated = false; // Flag indicating if interception has been calculated

    /**
     * Constructor to initialize the Interception object with the projectile and launcher.
     * @param projectile The projectile object to be intercepted
     * @param launcher The launcher attempting the interception
     */
    public Interception(Projectile projectile, Launcher launcher) {
        this.projectile = projectile;
        this.launcher = launcher;
    }

    /**
     * Returns the launch angle of the projectile.
     * @return the launch angle in radians.
     */
    public double getLaunchAngle() {
        return launchAngle;
    }

    /**
     * Returns the X coordinate of the interception point.
     * @return the X coordinate of the interception point.
     */
    public double getIX() {
        return iX;
    }

    /**
     * Returns the Y coordinate of the interception point.
     * @return the Y coordinate of the interception point.
     */
    public double getIY() {
        return iY;
    }

    /**
     * Calculates the interception point by determining the required velocity and time.
     * It uses radar time to estimate the position of the projectile and calculates the launcher
     * velocity to reach the interception point.
     * @param radarTime The time at which radar determines the position of the projectile
     */
    public void calcInterceptionPoint(double radarTime) {
        // Calculate the projectile's data (current position, speed, etc.)
        double[] pData = calcProjectileData(radarTime);
        double px = pData[0]; // X position of the projectile
        double py = pData[1]; // Y position of the projectile
        double impactPointX = pData[2]; // Calculated X-coordinate of the impact point
        double projectileSpeed = pData[3]; // Speed of the projectile

        // Calculate the distance from the identification point (radar) to the impact point
        distanceFromIdentificationToImpact = Utility.calcDistance(px, py, impactPointX, projectile.getTargetY());

        // Calculate the required velocities to intercept the projectile
        vX = Utility.calcVelocity(px, impactPointX, distanceFromIdentificationToImpact, projectileSpeed);
        vY = Utility.calcVelocity(py, projectile.getTargetY(), distanceFromIdentificationToImpact, projectileSpeed);

        // Calculate the time required to intercept the projectile
        t = Utility.calcT(px, py, launcher.getX(), launcher.getY(), vX, vY, launcher.getSpeed());

        // Calculate the interception point using the calculated velocity and time
        double[] interceptionPoint = Utility.findCorrectPoint(px, py, vX, vY, t);
        iX = (int) interceptionPoint[0];
        iY = (int) interceptionPoint[1];

        // Calculate the required launch angle
        launchAngle = Math.atan2(iY, iX - launcher.getCurrentX());
    }

    /**
     * Calculates the data necessary to determine the projectile's position and speed
     * at the time of radar detection.
     * @param radarTime The time at which radar determines the position of the projectile
     * @return An array containing the projectile's current position, impact point X, and speed
     */
    private double[] calcProjectileData(double radarTime) {
        double px = projectile.getCurrentX(); // Current X position of the projectile
        double py = projectile.getCurrentY(); // Current Y position of the projectile

        // Calculate the distance between the initial position and current position
        double distanceFromFirstToSecond = Utility.calcDistance(projectile.getX(), projectile.getY(), px, py);

        // Calculate the speed of the projectile based on the distance and radar time
        double projectileSpeed = distanceFromFirstToSecond / radarTime;

        // Calculate the angle of the projectile's movement
        double projectileAngle = Math.asin((px - projectile.getX()) / distanceFromFirstToSecond);

        // Calculate the X-coordinate of the impact point
        double impactPointX = px + py * Math.tan(projectileAngle);

        return new double[] {px, py, impactPointX, projectileSpeed};
    }

    /**
     * Prints the details of the interception, including the projectile's initial position,
     * target position, velocities, time, and interception point.
     */
    public void print() {
        System.out.println("Projectile starts at: (" + projectile.getX() + ", " + projectile.getY() + ") with speed: " + projectile.getSpeed());
        System.out.println("Projectile target at: (" + projectile.getTargetX() + ", " + projectile.getTargetY() + ")");
        System.out.println("Launcher starts at: (" + launcher.getX() + ", 0) with speed: " + launcher.getSpeed());
        System.out.println("Distance from identification to impact: " + distanceFromIdentificationToImpact);
        System.out.println("Calculated velocities -> X: " + vX + ", Y: " + vY);
        System.out.println("Time to intercept: " + t);
        System.out.println("Interception point: (" + iX + ", " + iY + ")");
        System.out.println("Launch angle: " + Math.toDegrees(launchAngle) + " degrees");
    }
}

