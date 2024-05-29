package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Triangle}.
 * @author Matan and Eitan
 */
class TriangleTest {

    /**
     * Test method for {@link geometrics.Triangle#getNormal(primitives.Point)}   .
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Triangle t = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
       assertEquals(new Vector(0, 0, 1).normalize(), t.getNormal(new Point(0, 0, 0)), "Bad normal to triangle");
        // TC02: Simple test
        t = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        assertEquals(new Vector(0, 0, -1).normalize(), t.getNormal(new Point(0, 0, 0)), "Bad normal to triangle");

        // =============== Boundary Values Tests ==================
        // TC03: if the three points are on the same line, the normal is the cross product of the two vectors

        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9)),
                "Constructed a triangle with three points on the same line");

    }

    /**
     * Test method for {@link geometrics.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Triangle t = new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the triangle with two intersections points (2 points)
        List<Point> result = t.findIntersections(new Ray(new Point(0.23523,-0.14861,0), new Vector(0.33, 0.51, 0.07)));
        assertEquals(2, result.size(), "Wrong number of points");

        // TC02: Ray starts inside the triangle (1 point)
        result = t.findIntersections(new Ray(new Point(0.25,0.25,0.25), new Vector(0.22, 0.56, -0.99)));
        assertEquals(1, result.size(), "Wrong number of points");

        // TC03: Ray does not intersect the triangle (no intersections)
        result = t.findIntersections(new Ray(new Point(0.25,0.6,0.99), new Vector(0.22, 0.56, -0.99)));
        assertNull(result, "Wrong number of points");

        // TC04: Ray starts in-front of a vertex of the triangle (2 point)
        result = t.findIntersections(new Ray(new Point(0,1.2,0), new Vector(0.22, -0.57, 0.29)));


        // =============== Boundary Values Tests ==================
        // TC05: Ray starts on the triangle edge and crosses it (1 point)
        result = t.findIntersections(new Ray(new Point(0.4,0,0.28), new Vector(-0.29, 0.63, -0.02)));
        assertEquals(1, result.size(), "Wrong number of points");

        // TC06: Ray starts on the triangle edge and goes to the opposite direction (no intersections)







    }


}