package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Matan and Eitan
 */
class PointTest {


    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}   .
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);


        // TC01: simple test
        assertEquals(27, p1.distanceSquared(p2), "ERROR: Point distanceSquared does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC02: test zero distance
        assertEquals(0, p1.distanceSquared(p1), "ERROR: Point distanceSquared does not work correctly");
    }


    /**
     * Test method for {@link primitives.Point#subtract(Point)}   .
     */
    @Test
    void subtract() {

        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);

        // ============ Equivalence Partitions Tests ==============

        // TC01: simple test

        Vector answer = p2.subtract(p1);

        assertEquals(new Vector(3,3,3), answer, "ERROR: Point subtract does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC02: test zero vector
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "ERROR: Point subtract 0 does not work correctly");

    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}   .
     */
    @Test
    void add() {

        Point p = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test

        Point answer = p.add(new Vector(1, 1, 1));

        assertEquals(new Point (2,3,4), answer, "ERROR: Point + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02: test zero vector
       assertThrows(IllegalArgumentException.class, () -> p.add(new Vector(0,0,0)), "ERROR: Point + 0 does not work correctly");


    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}   .
     */
    @Test
    void distance() {

        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);

        // TC01: simple test

        assertEquals(Math.sqrt(27), p1.distance(p2), "ERROR: Point distance does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC02: test zero distance
        assertEquals(0, p1.distance(p1), "ERROR: Point distance does not work correctly");




    }
}