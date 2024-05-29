package geometrics;
import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate system
 *
 * @author Me
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor based on three vertices. The vertices must be in the same plane
     * and the order of them according to the edge path should be counter clockwise
     * @param p1 first vertex
     * @param p2 second vertex
     * @param p3 third vertex
     * @throws IllegalArgumentException in any case of illegal combination of vertices
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
