package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Triangle}.
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
        // TC03: Constructed a triangle with vector head is zero
        try {
            t = new Triangle(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9));
            assertEquals(new Vector(3, -6, 3).normalize(), t.getNormal(new Point(1, 2, 3)), "Bad normal to triangle");
            fail("Constructed a triangle with 0 area");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }
}