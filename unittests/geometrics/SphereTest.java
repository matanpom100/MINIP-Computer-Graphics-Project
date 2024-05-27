package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;

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
}