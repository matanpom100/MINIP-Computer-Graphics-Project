package geometrics;

import primitives.Point;

import primitives.Vector;

/**
 * RadialGeometry is an abstract class that represents a radial geometry in 3D Cartesian coordinate
 * system
 *
 * @author Me
 */
public class RadialGeometry implements Geometry {
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
        this.radius = radius;
    }


    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
