package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource interface represents a light source in the scene
 * @Author Matan and Eitan
 */
public interface LightSource {
    /**
     * Get the intensity of the light at a specific point
     * @param p the point to get the intensity at
     * @return the intensity of the light at the point
     */
    public Color getIntensity(Point p);

    /**
     * Get the vector from the light source to a specific point
     * @param p the point to get the vector to
     * @return the vector from the light source to the point
     */
    public Vector getL(Point p);

    /**
     * Get the distance from the light source to a specific point
     * @param point the point to get the distance to
     * @return the distance from the light source to the point
     */
    public double getDistance(Point point);


}
