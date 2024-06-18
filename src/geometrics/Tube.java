package geometrics;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents a tube in 3D Cartesian coordinate system
 * The tube is represented by a radius and an axis (Ray)
 * The tube is infinite in both directions along the axis
 * The tube is a radial geometry
 * The tube does not have a normal vector
 */
public class Tube extends RadialGeometry {
    protected final Ray axis;


    /**
     * Tube constructor based on a radius and axis
     *
     * @param radius the radius of the tube
     * @param axis   the axis of the tube
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    /**
     * @param point
     * @return the normal vector to the tube at the given point
     */
    public Vector getNormal(Point point) {


        double t = axis.getDirection().dotProduct(point.subtract(axis.getHead()));//t is the projection of the point on the axis
        Point o = axis.getPoint(t);//o is the point on the axis that is closest to the point
        return point.subtract(o).normalize();//the normal vector is the vector from the point to o
    }

    /**
     * Find the intersections of a ray with the tube
     *
     * @param ray The ray to find the intersections with
     * @return A list of points where the ray intersects the tube
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return List.of();
    }
}
