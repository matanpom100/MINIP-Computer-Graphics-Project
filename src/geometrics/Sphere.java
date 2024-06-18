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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (ray.getHead().equals(center)) { //if the ray starts at the center of the sphere
            return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
        }
        Vector u = center.subtract(ray.getHead());//vector from the head of the ray to the center of the sphere
        double tm = ray.getDirection().dotProduct(u);//the projection of u on the ray

        if (ray.getHead().equals(center))
            return List.of(new GeoPoint(this, ray.getPoint(this.radius))); //if the ray starts at the center of the sphere


        double d = Math.sqrt(u.lengthSquared() - tm * tm);//the distance between the center of the sphere and the ray

        if (d >= radius) return null;//if the ray doesn't intersect the sphere

        double th = Math.sqrt(radius * radius - d * d);//the height of the triangle between the center of the sphere, the ray and the intersection points
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));//if the ray intersects the sphere in two points
        else if (t1 > 0) return List.of(new GeoPoint(this, ray.getPoint(t1)));//if the ray intersects the sphere in one point
        else if (t2 > 0) return List.of(new GeoPoint(this, ray.getPoint(t2)));//if the ray intersects the sphere in one point
        return null;//if the ray doesn't intersect the sphere
    }

}
