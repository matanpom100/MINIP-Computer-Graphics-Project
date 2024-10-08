package geometrics;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class represents a plane in 3D space.
 * A plane is defined by a point q and a normal vector.
 */
public class Plane extends Geometry {

    final Point q;
    final Vector normal;

    /**
     * Constructor for Plane class.
     *
     * @param q      Point on the plane.
     * @param normal Normal vector to the plane.
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal;
        calculateBoundingBox();
    }

    /**
     * Constructor for Plane class.
     *
     * @param p1 Point on the plane.
     * @param p2 Point on the plane.
     * @param p3 Point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q = p1;
        this.normal = p2.subtract(p1).crossProduct(p3.subtract(p1)).normalize();
        calculateBoundingBox();
    }


    @Override
    public Vector getNormal(Point point) {
        return normal;
    }


    /**
     * Getter for the point on the plane.
     *
     * @return Point on the plane.
     */
    public Vector getNormal() {
        return normal;
    }


    @Override
    protected void calculateBoundingBox() {
        box = new BoundingBox(new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY),
                new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        Point head = ray.getHead();
        Vector direction = ray.getDirection();

        if (head.equals(q)) {//if the head of the ray is on the plane
            return null;
        }

        if (isZero(direction.dotProduct(normal))) {//if the ray is parallel to the plane
            return null;
        }

        double t = normal.dotProduct(q.subtract(head)) / normal.dotProduct(direction);//calculating the intersection point
        if (alignZero(t) <= 0) {//if the intersection point is behind the head of the ray
            return null;
        }

        return List.of(new GeoPoint(this, ray.getPoint(t)));//returning the intersection point


    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();

        if (head.equals(q)) {//if the head of the ray is on the plane
            return null;
        }

        if (isZero(direction.dotProduct(normal))) {//if the ray is parallel to the plane
            return null;
        }

        double t = normal.dotProduct(q.subtract(head)) / normal.dotProduct(direction);//calculating the intersection point

        if(alignZero(t) <= 0) {//if the intersection point is behind the head of the ray
            return null;
        }

        if (alignZero(t - maxDistance) > 0) {//if the intersection point is behind the head of the ray
            return null;
        }

        return List.of(new GeoPoint(this, ray.getPoint(t)));//returning the intersection point
    }
}
