package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Triangle}.
 *
 * @author Matan and Eitan
 */
class TriangleTest {

    final Point p100 = new Point(1, 0, 0);
    final Point p010 = new Point(0, 1, 0);
    final Point p001 = new Point(0, 0, 1);

    /**
     * Test method for {@link geometrics.Triangle#getNormal(primitives.Point)}   .
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Triangle t = new Triangle(new Point(0, 0, 0), p100, p010);
        assertEquals(new Vector(0, 0, 1).normalize(), t.getNormal(new Point(0, 0, 0)), "Bad normal to triangle");
        // TC02: Simple test
        t = new Triangle(new Point(0, 0, 0), p010, p100);
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

        Triangle t = new Triangle(p100, new Point(0, 0.87, 0), new Point(1, 1, 0));

        Point p11 = new Point(0.5, 0.5, 0);


        List<Point> result;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the triangle (1 point)
        result = t.findIntersections(new Ray(new Point(0, 0, 0.5), new Vector(0.5, 0.5, -0.5)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p11), result, "Wrong intersection point");

        // TC02: Ray does not intersect the triangle in-front of an edge (0 points)
        result = t.findIntersections(new Ray(new Point(0, 0, -0.5), new Vector(0.4, 0.25, 0.5)));
        assertNull(result, "Ray intersects the triangle");

        // TC03: Ray does not intersect the triangle in-front of a vertex (0 points)
        result = t.findIntersections(new Ray(new Point(0, 0, 0.5), new Vector(-0.37, 0.87, -0.5)));
        assertNull(result, "Ray intersects the triangle");

        // =============== Boundary Values Tests ==================

        // TC04: Ray intersects the triangle on an edge (0 points)
        result = t.findIntersections(new Ray(new Point(0, 0, 0.5), new Vector(0.42, 0.5, -0.5)));
        assertNull(result, "Ray intersects the triangle");

        // TC05: Ray intersects the triangle on a vertex (0 points)
        result = t.findIntersections(new Ray(new Point(0, 0, 0.5), new Vector(1, 0, -0.5)));
        assertNull(result, "Ray intersects the triangle");

        // TC06: Ray intersects the triangle on the edge's continuation (0 points)
        result = t.findIntersections(new Ray(new Point(0, 0, -0.2), new Vector(-0.1, 0.87, 0.2)));
        assertNull(result, "Ray intersects the triangle");
    }
}