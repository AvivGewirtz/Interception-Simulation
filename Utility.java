/**
 * Utility class providing mathematical functions for calculations used in the simulation.
 */
public class Utility {

    /**
     * Calculates the position of a point after moving at a specified velocity for a given time.
     *
     * @param x1 Initial X-coordinate.
     * @param y1 Initial Y-coordinate.
     * @param vX Velocity in the X direction.
     * @param vY Velocity in the Y direction.
     * @param t  Time elapsed.
     * @return A double array containing the new X and Y coordinates.
     */
    public static double[] findCorrectPoint(double x1, double y1, double vX, double vY, double t) {
        double x = x1 + vX * t; // Calculate new X-coordinate
        double y = y1 + vY * t; // Calculate new Y-coordinate
        return new double[]{x, y}; // Return the new point as an array
    }

    /**
     * Calculates the time required for a launcher to intercept a projectile based on initial positions, velocities, and launcher speed.
     *
     * @param projectileX    X-coordinate of the projectile.
     * @param projectileY    Y-coordinate of the projectile.
     * @param launcherX      X-coordinate of the launcher.
     * @param launcherY      Y-coordinate of the launcher.
     * @param vX             Velocity of the projectile in the X direction.
     * @param vY             Velocity of the projectile in the Y direction.
     * @param launcherSpeed  Speed of the launcher.
     * @return The time required for interception.
     */
    public static double calcT(double projectileX, double projectileY, double launcherX, double launcherY, double vX, double vY, double launcherSpeed) {
        // Coefficients of the quadratic equation
        double a = -Math.pow(launcherSpeed, 2) + Math.pow(vX, 2) + Math.pow(vY, 2);
        double b = 2 * ((projectileX - launcherX) * vX + (projectileY - launcherY) * vY);
        double c = Math.pow(projectileX - launcherX, 2) + Math.pow(projectileY - launcherY, 2);

        // Solve the quadratic equation for time
        double tP = solveQuadraticPlus(a, b, c);
        double tM = solveQuadraticMinus(a, b, c);

        // Determine the valid time
        if (tP < 0 && tM > 0) {
            return tM; // Return the positive time
        } else if (tM < 0 && tP > 0) {
            return tP; // Return the positive time
        } else {
            return Math.min(tP, tM); // Return the smaller positive time
        }
    }

    /**
     * Solves the quadratic equation using the positive root.
     *
     * @param a Coefficient of the quadratic term.
     * @param b Coefficient of the linear term.
     * @param c Constant term.
     * @return The positive root of the quadratic equation, or 0 if no valid root exists.
     */
    public static double solveQuadraticPlus(double a, double b, double c) {
        double discriminant = Math.pow(b, 2) - 4 * a * c; // Calculate the discriminant

        // Handle special case when the equation is linear
        if (a == 0) {
            return -(c / b);
        }

        // Return 0 if discriminant is negative (no real roots)
        if (discriminant < 0) {
            return 0;
        }

        // Calculate and return the positive root
        return (-b + Math.sqrt(discriminant)) / (2 * a);
    }

    /**
     * Solves the quadratic equation using the negative root.
     *
     * @param a Coefficient of the quadratic term.
     * @param b Coefficient of the linear term.
     * @param c Constant term.
     * @return The negative root of the quadratic equation, or 0 if no valid root exists.
     */
    public static double solveQuadraticMinus(double a, double b, double c) {
        double discriminant = Math.pow(b, 2) - 4 * a * c; // Calculate the discriminant

        // Handle special case when the equation is linear
        if (a == 0) {
            return -(c / b);
        }

        // Return 0 if discriminant is negative (no real roots)
        if (discriminant < 0) {
            return 0;
        }

        // Calculate and return the negative root
        return (-b - Math.sqrt(discriminant)) / (2 * a);
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param x1 X-coordinate of the first point.
     * @param y1 Y-coordinate of the first point.
     * @param x2 X-coordinate of the second point.
     * @param y2 Y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double calcDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // Apply distance formula
    }

    /**
     * Calculates the velocity component in one direction based on the total speed and distance.
     *
     * @param x1      Starting X-coordinate.
     * @param x2      Target X-coordinate.
     * @param distance Total distance to travel.
     * @param speed    Total speed.
     * @return The velocity component in the X direction.
     */
    public static double calcVelocity(double x1, double x2, double distance, double speed) {
        return (speed * (x2 - x1)) / distance; // Scale velocity by distance ratio
    }
}
