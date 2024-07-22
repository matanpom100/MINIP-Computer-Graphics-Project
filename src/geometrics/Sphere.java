package geometrics;


import primitives.Point;
import primitives.Ray;
import primitives.Util;
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
        calculateBoundingBox();
    }


    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();//return the normal vector
    }


    @Override
    protected void calculateBoundingBox() {
        double minX = center.getX() - radius;
        double minY = center.getY() - radius;
        double minZ = center.getZ() - radius;
        double maxX = center.getX() + radius;
        double maxY = center.getY() + radius;
        double maxZ = center.getZ() + radius;
        box = new BoundingBox(
                new Point(minX, minY,minZ),
                new Point(maxX, maxY,maxZ)
        );
    }

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

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point head = ray.getHead();
        if(ray.getHead().equals(center)){ // if the ray starts at the center of the sphere
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        Vector u = center.subtract(head); // u = O - P0
        double tm = ray.getDirection().dotProduct(u); // tm = V . u
        if (Util.isZero(tm)) { // if the sphere is behind the ray
            if( u.length() >= radius){
                return null;
            }
            double t = Math.sqrt(radius * radius - u.lengthSquared()); // t = sqrt(R^2 - ||u||^2)
            return List.of(new GeoPoint(this,ray.getPoint(t))); // P = P0 + t * V
        }


        double d = Math.sqrt(u.lengthSquared() - tm * tm); // d = sqrt(||u||^2 - tm^2)
        if (d >= radius) {
            return null;
        }
        double th = Math.sqrt(radius * radius - d * d); // th = sqrt(R^2 - d^2)


        double t1 = tm - th; // t1 = tm - th
        double t2 = tm + th; // t2 = tm + th
        if (t1 > 0 && t2 > 0 && Util.alignZero(t1 - maxDistance) <= 0 && Util.alignZero(t2 - maxDistance) <= 0) { // if the sphere is behind the ray
            Point p1 = ray.getPoint(t1); // P1 = P0 + t1 * V
            Point p2 = ray.getPoint(t2); // P2 = P0 + t2 * V
            if(p1.subtract(head).length() > p2.subtract(head).length()){
                return List.of( new GeoPoint(this, p1), new GeoPoint(this, p2));
            }
            return List.of( new GeoPoint(this, p1),new GeoPoint(this, p2));
        }
        if (t1 > 0 && Util.alignZero(t1 - maxDistance) <= 0) { // if the sphere is behind the ray
            Point p1 = ray.getPoint(t1); // P1 = P0 + t1 * V
            return List.of( new GeoPoint(this, p1));
        }
        if (t2 > 0 && Util.alignZero(t2 - maxDistance) <= 0) { // if the sphere is behind the ray
            Point p2 = ray.getPoint(t2); // P2 = P0 + t2 * V
            return List.of(new GeoPoint(this, p2));
        }
        return null;
    }


}
