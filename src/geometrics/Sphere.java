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

    /**
     * @param ray
     * @return a list of intersection points between the ray and the sphere
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        Vector u = center.subtract(ray.getHead());
        double tm = ray.getDirection().dotProduct(u);

        if (ray.getHead().equals(center)) return List.of(ray.getPoint(radius)); //if the ray starts at the center of the sphere





        double d = Math.sqrt(u.lengthSquared() - tm * tm);



        if (d >= radius) return null;

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) return List.of(ray.getPoint(t1), ray.getPoint(t2));
        else if (t1 > 0) return List.of(ray.getPoint(t1));
        else if (t2 >0) return List.of(ray.getPoint(t2));
        return null;




    }

}
