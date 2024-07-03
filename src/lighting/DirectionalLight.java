package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
    * Light class represents a light in the scene.
    * @Author Matan and Eitan
 */
public class DirectionalLight extends Light implements LightSource{

    /**
    * The direction of the light.
     */
    Vector direction;



    /**
     * Constructor for the Light class.
     * @param direction The direction of the light.
     * @param intensity The intensity of the light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * Get the intensity of the light.
     * @param p The point to get the intensity at.
     * @return The intensity of the light.
        */
    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    /**
     * Get the direction of the light.
     * @param p The point to get the direction at.
     * @return The direction of the light.
     */
    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    /**
     * Get the distance from the light source to a specific point.
     * @param point The point to get the distance to.
     * @return The distance from the light source to the point.
     */

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
