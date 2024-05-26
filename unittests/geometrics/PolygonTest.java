package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Cylinder}.
 * @author Matan and Eitan
 */
class PolygonTest {

    /**
     * Test method for {@link geometrics.Polygon#Polygon(primitives.Point[])}   .
     */
    @Test
    void testPolygon() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple test of regular polygon
        assertEquals(new Vector(0, 0, 1), new Polygon(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)).getNormal(new Point(0, 0, 0)), "Bad normal to polygon");


        // ===================== Boundary Values Tests ==================
        // TC0: Points on the same line
        try {
            new Polygon(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9));
            fail("Constructed a polygon with three points on the same line");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        // TC0: Test if the points not on the same plane
        try {
            new Polygon(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9), new Point(10, 11, 12));
            fail("Constructed a polygon with points not on the same plane");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }



    /**
     * Test method for {@link geometrics.Polygon#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {



        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Polygon polygon = new Polygon(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        assertEquals(new Vector(0, 0, 1), polygon.getNormal(new Point(0, 0, 0)), "Bad normal to polygon");
        // TC02: Simple test
        polygon = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        assertEquals(new Vector(0, 0, -1).normalize(), polygon.getNormal(new Point(0, 0, 0)), "Bad normal to triangle");


        // =============== Boundary Values Tests ==================

        // TC03: Test if the points not on the same plane
        try {
            new Polygon(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9)).getNormal(new Point(1, 2, 3));
            fail("Constructed a polygon with points not on the same plane");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
}
}