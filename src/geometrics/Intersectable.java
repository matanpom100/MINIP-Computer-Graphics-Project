package geometrics;
import primitives.Point;
import primitives.Ray;
import java.util.List;

public interface Intersectable {

    /**
     * Find the intersections of a ray with the geometry.
     *
     * @param ray The ray to find the intersections with.
     * @return A list of points where the ray intersects the geometry.
     */
    List<Point> findIntersections(Ray ray);
}
