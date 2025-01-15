/**
 * The Value class defines a set of constant parameters used for configuring and limiting
 * various aspects of the simulation, such as projectile properties, impact points,
 * launcher positioning, speed, and radar settings.
 */
public class Value {
    public final static int step = 1; //Defines the incremental step for computations.

    //Projectile Settings:
    public final static int projectileXMin = 1, projectileXMax = 1000; //The minimum and maximum allowable X-coordinate for the projectile.
    public final static int projectileYMin = 50, projectileYMax = 800; //The minimum and maximum allowable Y-coordinate for the projectile.
    public final static int projectileXDefault = 100, projectileYDefault = 800; //Default starting coordinates for the projectile.

    //Impact Point Settings:
    public final static int impactXMin = 1, impactXMax = 1000; //The range of allowable X-coordinates for the impact point.
    public final static int impactXDefault = 400; //Default X-coordinate for the impact point.

    //Launcher Settings:
    public final static int launcherXMin = 1, launcherXMax = 1000; //The range of allowable X-coordinates for the launcher.
    public final static int launcherXDefault = 200; //Default X-coordinate for the launcher.

    //Speed Settings:
    public final static int speedMin = 10, speedMax = 1000; //The minimum and maximum allowable speed
    public final static int speedDefault = 100; //Default speed for the projectile.

    //Radar Settings:
    public final static double radarStep = 0.1; //Defines the incremental step for radar operations.
    public final static double radarMin = 0.1, radarMax = 2; //The minimum and maximum allowable radar scale factors.
    public final static double radarDefault = 1; //Default radar scale factor.
}
