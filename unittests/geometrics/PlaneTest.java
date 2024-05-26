package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Plane}
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

        try {
            p = new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0));
            fail("Failed constructing a plane with two parallel vectors");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}