package geometrics;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
     *
     * @param p1 first vertex
     * @param p2 second vertex
     * @param p3 third vertex
     * @throws IllegalArgumentException in any case of illegal combination of vertices
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Find intersections of a ray with the triangle
     *
     * @param ray the ray that intersects the triangle
     * @return a list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> p = plane.findIntersections(ray);
        if (p == null) {//if the ray does not intersect the plane of the triangle
            return null;
        }


        Vector v1 = vertices.get(0).subtract(ray.getHead());//v1 is the vector from the head of the ray to the first vertex of the triangle
        Vector v2 = vertices.get(1).subtract(ray.getHead());//v2 is the vector from the head of the ray to the second vertex of the triangle
        Vector v3 = vertices.get(2).subtract(ray.getHead());//v3 is the vector from the head of the ray to the third vertex of the triangle

        Vector n1 = v1.crossProduct(v2);//n1 is the normal vector to the triangle created by v1 and v2
        Vector n2 = v2.crossProduct(v3);//n2 is the normal vector to the triangle created by v2 and v3
        Vector n3 = v3.crossProduct(v1);//n3 is the normal vector to the triangle created by v3 and v1

        if (ray.getDirection().dotProduct(n1) > 0 && ray.getDirection().dotProduct(n2) > 0 && ray.getDirection().dotProduct(n3) > 0 ||
                ray.getDirection().dotProduct(n1) < 0 && ray.getDirection().dotProduct(n2) < 0 && ray.getDirection().dotProduct(n3) < 0) {
            return p;
        }
        return null;

    }
}
