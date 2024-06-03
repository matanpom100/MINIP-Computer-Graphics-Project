package geometrics;

import primitives.Vector;
import primitives.Ray;
import primitives.Point;
import primitives.Util;

/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate system
 *
 * @author Matan and Eitan
 */
public class Cylinder extends Tube {
    final double height;

    /**
     * Cylinder constructor based on a radius, axis and height
     *
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

        Vector v = axis.getDirection();//the direction of the axis
        Point p = axis.getHead();//the head of the axis

        if (point.equals(p))
            return v.scale(-1).normalize();//if the point is the head of the axis, the normal is the opposite direction of the axis

        double t = v.dotProduct(point.subtract(p));//the projection of the point on the axis

        if (Util.isZero(t))
            return v.scale(-1).normalize();//if the projection is 0, the normal is the opposite direction of the axis

        if (Util.isZero(height - t))
            return v.normalize();//if the projection is the height of the cylinder, the normal is the direction of the axis

        return super.getNormal(point);//if the point is on the surface of the cylinder, the normal is the same as the tube


    }
}
