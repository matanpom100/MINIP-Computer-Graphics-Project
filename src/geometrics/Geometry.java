package geometrics;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface is the basic interface for all geometric shapes
 */
public interface Geometry extends Intersectable {

    /**
     * @param point
     * @return the normal vector to the geometry at the point
     */
    public Vector getNormal(Point point);
}
