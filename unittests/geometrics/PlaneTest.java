package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Plane}
 * Matan and Eitan
 */
class PlaneTest {

    /**
     * Test method for {@link geometrics.Plane#getNormal(primitives.Point)}   .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        assertEquals(p.getNormal(new Point(1, 2, 3)), new Vector(new Double3(1, 1, 1)).normalize(), "Bad normal to plane");

        // =============== Boundary Values Tests ==================
        // TC02: if the two vectors are parallel, the normal is the cross product of the two vectors

        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)), "Bad normal to plane");
    }


    /**
     * Test method for {@link geometrics.Plane#findIntersections(primitives.Ray)}.
     */

    @Test
    void testFindIntersections() {


        Plane p = new Plane(new Point(3, 0, 0), new Point(0, 3, 0), new Point(0, 0, 3));
        Plane p2 = new Plane(new Point(0, 2, 0), new Point(2, 0, 0), new Point(1, 1, 2));

        List<Point> result;

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane with one intersection point (1 point)
        // p
        result = p.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 0, 2)));
        assertEquals(1, result.size(), "Bad intersection to plane");
        assertEquals(List.of(new Point(0, 1, 2)), result, "Bad intersection to plane");

        // TC02: Ray not intersects the plane at all (0 points)
        result = p.findIntersections(new Ray(new Point(0, 1, 1.5), new Vector(0, 0, -1.5)));
        assertNull(result, "Bad intersection to plane");

        // =============== Boundary Values Tests ==================
        // p2
        // TC03: Ray is on the plane (paralel to the plane) (0 points)
        result = p2.findIntersections(new Ray(new Point(1.5, 0.5, 0.17), new Vector(0, 0, 2.09)));
        assertNull(result, "Bad intersection to plane");

        // TC04: Ray is not on the plane and parallel to the plane (0 point)
        result = p2.findIntersections(new Ray(new Point(2, 0, 0), new Vector(2, 2, 0)));
        assertNull(result, "Bad intersection to plane");

        // Ray is orthogonal to the plane

        // TC05: Ray is orthogonal to the plane and before the plane (1 point)
        result = p2.findIntersections(new Ray(new Point(0, -3, 0), new Vector(3,3,0)));
        assertEquals(1, result.size(), "Bad intersection to plane");
        assertEquals(List.of(new Point(2.5, -0.5, 0)), result, "Bad intersection to plane");

        // TC06: Ray is orthogonal to the plane and after the plane (0 points)
        result = p2.findIntersections(new Ray(new Point(3, 0, 0), new Vector(3,3,0)));
        assertNull(result, "Bad intersection to plane");

        // TC07: Ray is orthogonal to the plane and starts on the plane (0 points)
        result = p2.findIntersections(new Ray(new Point(2.5, -0.5, 0), new Vector(3,3,0)));
        assertNull(result, "Bad intersection to plane");

        // Ray starts on the plane but not orthogonal or parallel to the plane

        // TC08: Ray starts on the plane and intersects the plane (0 point)
        result = p2.findIntersections(new Ray(new Point(2, 0, 2), new Vector(1, 0, -2)));
        assertNull(result, "Bad intersection to plane");

        // TC09: Ray starts on the plane's edge (0 points)
        result = p2.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Bad intersection to plane");


    }
}