package geometrics;


import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate system
 *
 * @author Me
 */
public class Sphere extends RadialGeometry {
    final Point center;

    /**
     * Sphere constructor based on a radius and center point
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
