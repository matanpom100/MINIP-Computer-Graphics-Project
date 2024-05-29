package geometrics;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Plane class represents a plane in 3D space.
 * A plane is defined by a point q and a normal vector.
 */
public class Plane implements Geometry {

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
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
