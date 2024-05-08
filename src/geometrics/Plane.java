package geometrics;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

     final Point q;
    final Vector  normal;


    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal;
    }

    public Plane(Point p1, Point p2, Point p3) {
        this.q = p1;
        this.normal = null;
    }




    @Override
    public Vector getNormal(Point point) {

        return normal;
    }


    public Vector getNormal() {
        return normal;
    }
}
