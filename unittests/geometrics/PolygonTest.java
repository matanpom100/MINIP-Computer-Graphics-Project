package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import primitives.Ray;


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
        // TC02: Points on the same line
        assertThrows(IllegalArgumentException.class, () -> new Polygon(new Point(0, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)));
        // TC03: Test if the points not on the same plane
        assertThrows(IllegalArgumentException.class, () -> new Polygon(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9)));
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
        assertThrows(IllegalArgumentException.class, () -> new Polygon(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 9)).getNormal(new Point(1, 2, 3)));
    }


    /**
     * Test method for {@link geometrics.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {

        Polygon p = new Polygon(new Point(0, 2, 0), new Point(2, 0, 0), new Point(3, 0, 0), new Point(0,3,0));
        List<Point> result;


        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the polygon (1 point)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(1.1, 1.15, -1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1.1, 1.15,0)), result);

        // TC02: Ray does not intersect the polygon in-front of an edge (0 points)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(2.5, -0.5, -1)));
        assertNull(result, "Ray intersects the triangle");

        // TC03: Ray does not intersect the polygon in-front of a vertex (0 points)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(1.63, -0.16, -1)));
        assertNull(result, "Ray intersects the triangle");


        // =============== Boundary Values Tests ==================

        // TC04: Ray intersects the polygon on an edge (0 points)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(2.5, 0, -1)));
        assertNull(result, "Ray intersects the triangle");

        // TC05: Ray intersects the polygon on a vertex (0 points)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(0, 2, -1)));
        assertNull(result, "Ray intersects the triangle");

        // TC06: Ray intersects the polygon on the edge's continuation (0 points)
        result = p.findIntersections(new Ray(new Point(0,0,1), new Vector(1.37, 0, -1)));
        assertNull(result, "Ray intersects the triangle");





    }

}