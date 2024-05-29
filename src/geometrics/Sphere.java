package geometrics;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate system
 *
 * @author Matan and Eitan
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

    /**
     * @param point
     * @return the normal vector to the sphere at the given point
     * @throws IllegalArgumentException if the point is not on the sphere
     */
    @Override
    public Vector getNormal(Point point) {
        if (point.distance(center) != radius) //if the point is not on the sphere
            throw new IllegalArgumentException("The point is not on the sphere");
        return point.subtract(center).normalize();//return the normal vector
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getHead();
        Vector direction = ray.getDirection();

    }

}
