package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeometriesTests {

    Triangle triangle = new Triangle(new Point(0, 0, 5.08), new Point(4.36,3.47,0), new Point(1,1,0));
    Sphere sphere = new Sphere(1, new Point(3, 0, 0));
    Polygon polygon = new Polygon(new Point(6.55, -0.69, 0), new Point(6.7, -1.96, 0), new Point(6.32, -3.19, 0), new Point(5, -5, 0), new Point(2.39, -6.27,0), new Point(1.1, -2.4,0), new Point(2.99, -1.47,0));
    Plane plane = new Plane(new Point(3.16, 4.49, 0), new Point(-1.62, 1.79, 0), new Point(0, 0, 5));

    Geometries emptyGeometries = new Geometries();
    Geometries geometries = new Geometries(triangle, sphere, polygon, plane);


    List<Point> result = new LinkedList<>();


    /**
     * Test method for {@link geometrics.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Some of the geometries have intersections
        result = geometries.findIntersections(new Ray(new Point(4.47, 2.58, 0), new Vector(-3.42, -0.22, 1.73)));
        assertEquals(2, result.size(), "Some of the geometries have intersections");

        // =============== Boundary Values Tests ==================

        // TC02: An empty collection (for not doing the set again)
        assertNull(emptyGeometries.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))), "An empty collection");
        // TC03: None of the geometries have intersections
        result = geometries.findIntersections(new Ray(new Point(-1.43, -3.57, 0), new Vector(-1.06, -0.67, 0)));
        assertNull(result, "None of the geometries have intersections");
        // TC04: Only one of the geometries has an intersection
        result = geometries.findIntersections(new Ray(new Point(-1.72, 0.36, 0), new Vector(-1.87, -0.27, 1.12)));
        assertEquals(1, result.size(), "Only one of the geometries has an intersection");
        // TC05: All geometries have intersections


        result = geometries.findIntersections(new Ray(new Point(5,-3,-0.3), new Vector(-4.21, 5.86, 1.19)));

        assertEquals(5, result.size(), "All geometries have intersections");



    }

}
