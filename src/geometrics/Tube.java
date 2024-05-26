package geometrics;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    public Vector testGetNormal(Point point) {

        if (point.equals(axis.getHead())) throw new IllegalArgumentException("Point on the tube's axis");

        double t = axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return point.subtract(o).normalize();
    }

}
