package geometrics;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    Point q;
    Vector normal;



    @Override
    public Vector getNormal(Vector point) {
        return null;
    }

    public Vector getNormal() {
        return normal;
    }
}
