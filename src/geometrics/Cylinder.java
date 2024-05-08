package geometrics;

import primitives.Vector;
import primitives.Ray;
import primitives.Point;

// Cylinder class represents a cylinder in 3D Cartesian coordinate system
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
     * @return null
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
