package geometrics;

import primitives.Point;

import primitives.Util;
import primitives.Vector;

/**
 * RadialGeometry is an abstract class that represents a radial geometry in 3D Cartesian coordinate
 * system
 *
 * @author Me
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * The radius of the radial geometry
     */
    protected final double radius;


    /**
     * RadialGeometry constructor based on a radius
     *
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        if (Util.alignZero(radius) <= 0)//if the radius is negative or zero
            throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
    }


    @Override
    public abstract Vector getNormal(Point point);
}
