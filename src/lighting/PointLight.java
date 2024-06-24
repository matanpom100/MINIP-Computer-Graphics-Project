package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a point light.
 *
 * @Author Matan and Eitan
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position of the light.
     */
    Point position;

    /**
     * The attenuation factors of the light.
     */
    double kC = 1;
    double kL = 0;
    double kQ = 0;

    /**
     * Get the position of the light.
     *
     * @param kC
     * @return The position of the light.
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Set the position of the light.
     *
     * @param kL
     * @return
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Set the position of the light.
     *
     * @param kQ
     * @return
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Constructor for the Light class.
     *
     * @param intensity The intensity of the light.
     * @param position  The position of the light.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Get the intensity of the light.
     *
     * @param p The point to get the intensity at.
     * @return The intensity of the light.
     */
    @Override
    public Color getIntensity(Point p) {
        return intensity.reduce((kC + kL * p.distance(position) + kQ * p.distanceSquared(position)));
    }

    /**
     * Get the direction of the light.
     * @param p The point to get the direction at.
     * @return The direction of the light.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
