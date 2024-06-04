package renderer;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Locale;


public class Camera implements Cloneable{
    Point position;
    Vector right;
    Vector to;
    Vector up;
    double height = 0.0;
    double width = 0.0;
    double distance = 0.0;

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    private Camera(){

    }

    public static Builder getBuilder() {
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
    }











}
