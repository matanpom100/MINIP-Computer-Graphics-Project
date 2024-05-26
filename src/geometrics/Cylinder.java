package geometrics;

import primitives.Vector;
import primitives.Ray;
import primitives.Point;

/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate system
 * @author Me
 */
public class Cylinder extends Tube {
    final double height;

    /**
     *  Cylinder constructor based on a radius, axis and height
     * @param radius
     * @param axis
     * @param height
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        this.height = height;
    }


    /**
     * @param point
     * @return The normal to the cylinder at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = axis.getDirection();
        Point p0 = axis.getHead();

        if(point.equals(p0)) return v.scale(-1);

        double t = v.dotProduct(point.subtract(p0));

        if (t == 0) return v.scale(-1);

        if(t == height) return v;

        return super.getNormal(point);


    }
}
