package geometrics;

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
     * @return null
     */
    public Vector getNormal(Vector point) {
        return null;
    }
}
