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
        try {
            s.getNormal(new Point(0, 2, 0));
            fail("Point out of sphere should throw exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}