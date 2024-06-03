package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Cylinder}.
 *
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

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometrics.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {


        Sphere sphere = new Sphere(1d, p100);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        List<Point> result;


        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        result = sphere.findIntersections(new Ray(p01, v110));
        assertNull(result, "Ray's line out of sphere");


        // TC02: Ray starts before and crosses the sphere (2 points)
        result = sphere.findIntersections(new Ray(p01, v310)).stream()
                .sorted(Comparator.comparingDouble(p -> p.distance(p01))) // Corrected lambda syntax
                .collect(Collectors.toList());
        ;
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(0.5, -1, 0)));
        assertEquals(1, result.size(), "Ray from inside sphere");
        assertEquals(List.of(new Point(1, -1, 0)), result, "Ray crosses sphere");

        // TC04: Ray starts after the sphere and if it will change direction it will touch the sphere (0 point)
        result = sphere.findIntersections(new Ray(new Point(0, -1, 0), new Vector(-0.22, -0.16, 0)));
        assertNull(result, "Ray from outside sphere");


        // =============== Boundary Values Tests ==================

        // Ray not crossing the center of the sphere and starts on the sphere edge
        // TC05: Ray starts on the sphere edge and crosses it (1 point)

        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-1, 1, 0)));
        assertEquals(1, result.size(), "Ray from sphere edge");
        assertEquals(List.of(new Point(1, 1, 0)), result, "Ray crosses sphere");

        // TC06: Ray starts on the sphere edge and goes to the opposite direction (0 points)
        result = sphere.findIntersections(new Ray(new Point(1.17, -0.99, 0), new Vector(0, -0.24, 0)));
        assertNull(result, "Ray from sphere edge");

        // The ray tangent the sphere edge
        // TC07: Ray tangent the sphere edge (0 point)
        result = sphere.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0)));
        assertNull(result, "Ray tangent sphere edge");

        // TC08: Ray starts at the sphere edge and won't touch again (0 point)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, -2, 0)));
        assertNull(result, "Ray from sphere edge");

        // TC09: Ray starts outside the sphere and goes to the opposite direction if the ray will change direction it will tangent the sphere edge (0 point)
        result = sphere.findIntersections(new Ray(new Point(2, -0.4, 0), new Vector(0, -0.5, 0)));
        assertNull(result, "Ray from outside sphere");


        // Ray is orthogonal to the sphere center
        // TC10: Ray is orthogonal to the sphere center and starts before the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(2.5, 0, 0), new Vector(0, 0.5, 0)));
        assertNull(result, "Ray orthogonal to sphere center");

        // TC11: Ray is orthogonal to the sphere center and starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Ray orthogonal to sphere center");
        assertEquals(List.of(new Point(1.5, 0.8660254037844386, 0)), result, "Ray crosses sphere");

        // The ray starts at the sphere center
        // TC12: Ray starts at the sphere center and goes outside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Ray from sphere center");
        assertEquals(List.of(new Point(1, 1, 0)), result, "Ray crosses sphere");

        // TC13: Ray starts on the sphere edge in the opposite direction and if it will change direction it will touch the sphere center (0 point)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray from sphere edge");

        // TC14: Ray starts in the sphere and if it will change direction it will touch the sphere center (1 point)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Ray from sphere edge");
        assertEquals(List.of(new Point(2, 0, 0)), result, "Ray crosses sphere");

        // TC15: Ray starts outside the sphere and if it will change direction it will touch the sphere center (0 point)
        result = sphere.findIntersections(new Ray(new Point(2.5, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray from outside sphere");

        // TC16: Ray starts at the sphere edge and crosses it (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, -2, 0)));
        assertEquals(1, result.size(), "Ray from sphere edge");
        assertEquals(List.of(new Point(1, -1, 0)), result, "Ray crosses sphere");

        // TC17: Ray starts outside the sphere and crosses it (2 points)
        result = sphere.findIntersections(new Ray(new Point(1, 1.5, 0), new Vector(0, -2.5, 0))).stream()
                .sorted(Comparator.comparingDouble(p -> p.distance(p01))) // Corrected lambda syntax
                .collect(Collectors.toList());
        assertEquals(2, result.size(), "Ray from outside sphere");
        assertEquals(List.of(new Point(1, 1, 0), new Point(1, -1, 0)), result, "Ray crosses sphere");


    }
}