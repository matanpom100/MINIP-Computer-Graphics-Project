package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Cylinder}.
 */
class PolygonTest {

    /**
     * Test method for {@link geometrics.Polygon#getNormal(primitives.Point)}   .
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Polygon polygon = new Polygon(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        assertEquals(new Vector(0, 0, 1), polygon.getNormal(new Point(0, 0, 0)), "Bad normal to polygon");
        // TC02: Simple test
        polygon = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        assertEquals(new Vector(0, 0, -1).normalize(), polygon.getNormal(new Point(0, 0, 0)), "Bad normal to triangle");

        // =============== Boundary Values Tests ==================
        try {
            polygon = new Triangle(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9));
            assertEquals(new Vector(3, -6, 3).normalize(), polygon.getNormal(new Point(1, 2, 3)), "Bad normal to triangle");
            fail("Constructed a triangle with 0 area");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }




    }
}