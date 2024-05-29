package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

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
        Plane p = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane with one intersection point

        assertEquals(p.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-0.45, 1.05, 0.39))).size(), 1, "Bad intersection to plane");

        // TC02: Ray does not intersect the plane

        assertNull(p.findIntersections(new Ray(new Point(0, 0, 1.5), new Vector(1, 0, 1.5))),
                "ERROR: findIntersections() did not return null when the ray does not intersect the plane");
        // =============== Boundary Values Tests ==================


        //Ray is paralel to the plane- included to the plane

        assertNull(p.findIntersections((new Ray(new Point(1, 0, 0), new Vector(1, 1, 0)))), "Bad intersection to plane");

        //Ray is paralel to the plane- not included to the plane

          assertNull(p.findIntersections((new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)))), "Bad intersection to plane");

        //Ray is orthogonal to the plane - before the plane

        assertEquals(p.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))).size(), 1, "Bad intersection to plane");

        //Ray is orthogonal to the plane - in the plane

        assertNull(p.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))), "Bad intersection to plane");

        //Ray is orthogonal to the plane - after the plane

        assertNull(p.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))), "Bad intersection to plane");


        //Ray is neither orthogonal nor parallel to the plane - intersects the plane

        assertEquals(p.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 1))).size(), 1, "Bad intersection to plane");

        //Ray is neither orthogonal nor parallel to the plane - but starts at the plane (q)
        assertNull(p.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 1, 1))), "Bad intersection to plane");


    }
}