package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a SpotLight.
 *
 * @Author Matan and Eitan
 */
public class SpotLight extends PointLight {

    /**
     * The direction of the light.
     */
    Vector direction;

    /**
     * The narrow beam factor
     */
    protected double narrowBeam = 1;


    /**
     * Set the narrow beam factor
     *
     * @param narrowBeam the narrow beam factor
     * @return this
     */
    public PointLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }



    /**
     * Get the direction of the light.
     *
     * @param kC
     * @return
     */
    public SpotLight setkC(double kC) {
        super.setkC(kC);
        return this;
    }

    /**
     * Set the direction of the light.
     *
     * @param kL
     * @return
     */
    public SpotLight setkL(double kL) {
        super.setkL(kL);
        return this;
    }

    /**
     * Set the direction of the light.
     *
     * @param kQ
     * @return
     */
    public SpotLight setkQ(double kQ) {
        super.setkQ(kQ);
        return this;
    }

    /**
     * Constructor for the Light class.
     *
     * @param intensity The intensity of the light.
     * @param position  The position of the light.
     * @param direction The direction of the light.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Get the intensity of the light.
     *
     * @param p The point to get the intensity at.
     * @return The intensity of the light.
     */
    @Override
    public Color getIntensity(Point p) {
        if(narrowBeam == 1)
            return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));
        return super.getIntensity(p).scale(Math.pow(Math.max(0, direction.dotProduct(getL(p))), narrowBeam)); // intensity of the light * max(0, direction of the light * direction from the light to the point)^the narrow beam factor
    }

    /**
     * Get the direction of the light.
     * @param p The point to get the direction at.
     * @return The direction of the light.
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

}
