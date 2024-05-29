package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Cylinder}.
 * @author Matan and Eitan
 */
class SphereTest {

    /**
     * Test method for {@link geometrics.Sphere#getNormal(primitives.Point)}   .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple single test here
        Sphere s = new Sphere(1, new Point(0, 0, 0));
        assertEquals(new Point(1, 0, 0), s.getNormal(new Point(1, 0, 0)), "Bad normal to sphere");

        // =============== Boundary Values Tests ==================
        // TC02: if the pont is outside the sphere
        assertThrows(IllegalArgumentException.class, () -> new Sphere(1, new Point(0, 0, 0)).getNormal(new Point(2, 0, 0)), "Failed constructing a sphere with a point outside the sphere");
    }

    /**
     * Test method for {@link geometrics.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Sphere s = new Sphere(1, new Point(0, 0, 0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray starts inside the sphere and crosses it (1 point)
        List<Point> result = s.findIntersections(new Ray(new Point(0.19968,-0.60222,0), new Vector(0.67, 0.67, 0.49)));
        assertEquals(1, result.size(), "Bad intersection to sphere");

        // TC02: Ray starts outside the sphere and crosses it (2 points)
        result = s.findIntersections(new Ray(new Point(-0.50985,-1.49606,0), new Vector(1.22, 1.74, 0.66)));
        assertEquals(2, result.size(), "Bad intersection to sphere");

        // TC03: Ray starts outside the sphere and goes to the opposite direction (0 points)
        result = s.findIntersections(new Ray(new Point(1.5,0,0), new Vector(0.34, -0.41, 0)));
        assertNull(result, "Bad intersection to sphere");

        // TC04: Ray won't intersect the sphere (0 points)
        result = s.findIntersections(new Ray(new Point(1.31,0,0), new Vector(-0.04, 0.4, 0.58)));
        assertNull(result, "Bad intersection to sphere");

        // =============== Boundary Values Tests ==================

        // Ray not crossing the center of the sphere and starts on the sphere edge

        // TC05: Ray starts on the sphere edge and crosses it (1 point)
        result = s.findIntersections(new Ray(new Point(-1,0,0), new Vector(2.49, 0, 0)));
        assertEquals(1, result.size(), "Bad intersection to sphere");

        // TC06: Ray starts on the sphere edge and goes to the opposite direction (0 points)
        result = s.findIntersections(new Ray(new Point(0,-1,0), new Vector(0, -1, 0)));
        assertNull(result, "Bad intersection to sphere");

        // The ray tangent the sphere edge

        // TC07: Ray tangent the sphere edge (0 point)
        result = s.findIntersections(new Ray(new Point(1,0,0), new Vector(0, 1, 0)));
        assertNull(result, "Bad intersection to sphere");

        // TC08: Ray starts at the sphere edge and won't touch again (0 point)
        result = s.findIntersections(new Ray(new Point(0.69,-0.73,0), new Vector(1.11, 0.73, 0)));
        assertNull(result, "Bad intersection to sphere");

        // TC09: Ray starts outside the sphere and goes to the opposite direction if the ray will change direction it will tangent the sphere edge (0 point)
        result = s.findIntersections(new Ray(new Point(1.4,0,0), new Vector(0.7, 0.7, -0.12)));
        assertNull(result, "Bad intersection to sphere");

        // Ray is orthogonal to the sphere center

        // TC10: Ray is orthogonal to the sphere center and starts before the sphere (0 points)
        result  = s.findIntersections (new Ray(new Point(-1,0,0), new Vector(0, 0, 1)));
        assertNull(result, "Bad intersection to sphere");

        // TC11: Ray is orthogonal to the sphere center and starts inside the sphere (1 point)
        result = s.findIntersections(new Ray(new Point(0.5,0,0), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Bad intersection to sphere");

        // The ray starts at the sphere center

        // TC12: Ray starts at the sphere center and goes outside the sphere (1 point)
        Point p1 = new Point(1, 1, 0);
        result = s.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "There should be one intersection");
        assertEquals(List.of(p1), result, "Incorrect intersection point");

        // TC13: Ray starts on the sphere edge in the opposite direction and if it will change direction it will touch the sphere center (0 point)
        result = s.findIntersections(new Ray(new Point(1,0,0), new Vector(1, 0, 0)));
        assertNull(result, "Bad intersection to sphere");

        // TC14: Ray starts at the sphere and if it will change direction it will touch the sphere center (1 point)
        result = s.findIntersections(new Ray(new Point(0.5,0,0), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Bad intersection to sphere");

        // TC15: Ray starts outside the sphere and if it will change direction it will touch the sphere center (0 point)
        result = s.findIntersections(new Ray(new Point(1.5,0,0), new Vector(1, 0, 0)));
        assertNull(result, "Bad intersection to sphere");

        // TC16: Ray starts at the sphere edge and crosses it (1 point)
        result = s.findIntersections(new Ray(new Point(-1,0,0), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Bad intersection to sphere");

        // TC17: Ray starts outside the sphere and crosses it (2 points)
        result = s.findIntersections(new Ray(new Point(-1.5,0,0), new Vector(1, 0, 0)));
        assertEquals(2, result.size(), "Bad intersection to sphere");

    }
}