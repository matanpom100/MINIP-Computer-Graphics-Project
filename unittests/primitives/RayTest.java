package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RayTest {

    /**
     * Test method for {@link primitives.Ray#Ray(primitives.Point, primitives.Vector)}.
     */
    @Test
    void testConstructor() {

        // =============== Boundary Values Tests ==================
        // TC01: Test zero vector
        assertThrows(IllegalArgumentException.class, () -> new Ray(new Point(0, 0, 0), new Vector(0, 0, 0)), "Constructed a ray with zero vector");
    }

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test

    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Ray r = new Ray(new Point(1, 1, 1), new Vector(2, 0, 0));

        assertEquals(new Point(2, 1, 1), r.getPoint(1), "Bad point");

        // =============== Boundary Values Tests ==================
        // TC02: Test zero vector
        assertEquals(new Point(1, 1, 1), r.getPoint(0), "Bad point");

    }

    /**
     * Test method for {@link Ray#getDirection()}.
     */

    @Test
    void testGetDirection() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Ray r = new Ray(new Point(1, 1, 1), new Vector(2, 0, 0));

        assertEquals(new Vector(2, 0, 0).normalize(), r.getDirection(), "Bad direction");

    }


    /**
     * Test method for {@link Ray#getHead()} (Object)}.
     */

    @Test
    void testGetHead() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Ray r = new Ray(new Point(1, 1, 1), new Vector(2, 0, 0));

        assertEquals(new Point(1, 1, 1), r.getHead(), "Bad head");

    }

    /**
     * Test method for {@link Ray#findClosestPoint(List)}
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Ray r = new Ray(new Point(1, 1, 1), new Vector(2, 0, 0));
        List<Point> points = List.of( new Point(3, 1, 1),new Point(2, 1, 1), new Point(4, 1, 1));

        assertEquals(new Point(2, 1, 1), r.findClosestPoint(points), "Bad point");

        // =============== Boundary Values Tests ==================

        // TC02: Test empty list
        assertNull(r.findClosestPoint(List.of()), "Bad point");

        // TC03: Test first point is the closest
        points = List.of(new Point(2, 1, 1), new Point(3, 1, 1), new Point(4, 1, 1));
        assertEquals(new Point(2, 1, 1), r.findClosestPoint(points), "Bad point");

        // TC04: Test last point is the closest
        points = List.of(new Point(4, 1, 1), new Point(3, 1, 1), new Point(2, 1, 1));
        assertEquals(new Point(2, 1, 1), r.findClosestPoint(points), "Bad point");



    }
}
