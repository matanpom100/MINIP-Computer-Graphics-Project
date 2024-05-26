package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 * @author Matan and Eitan
 */

class VectorTest {


    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)} (primitives.Vector)}   .
     */


    @Test

    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        try {
            new Vector(1, 2, 3);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Vector");
        }



        // TC02: Test negative vector
        try {
            new Vector(-1, -2, -3);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Vector");
        }

        // =============== Boundary Values Tests ==================

        // TC03: Test zero vector
        try {
            new Vector(0, 0, 0);
            fail("Constructed a zero vector");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }



    /**
     * Test method for {@link primitives.Vector#add(Vector)} (primitives.Vector)}   .
     */
    @Test
    void add() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 2, 3), new Vector(1, 1, 1).add(new Vector(0, 1, 2)), "add() wrong result");

        // TC02: Test negative vector
        assertEquals(new Vector(-1, -2, 3), new Vector(-1, -1, 1).add(new Vector(0, -1, 2)), "add() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: Test oppsites vectors

        try {
            new Vector(1, 1, 1).add(new Vector(-1, -1, -1));
            fail("add() for opposite vectors does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }


    /**
     * Test method for {@link primitives.Vector#scale(double)} (Vector)} (primitives.Vector)}   .
     */

    @Test
    void scale() {

// ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2), "scale() wrong result");

        // TC02: Test negative vector
        assertEquals(new Vector(-1, -2, -3), new Vector(1, 2, 3).scale(-1), "scale() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: Test zero scalar
        try {
            new Vector(1, 2, 3).scale(0);
            fail("scale() for zero scalar does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)} (Vector)} (primitives.Vector)}   .
     */

    @Test
    void dotProduct() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(20, new Vector(1, 2, 3).dotProduct(new Vector(2, 3, 4)), "dotProduct() wrong result");

        // TC02: Test negative vector
        assertEquals(-20, new Vector(1, 2, 3).dotProduct(new Vector(-2, -3, -4)), "dotProduct() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: Test orthogonal vectors
        assertEquals(0, new Vector(1, 0, 0).dotProduct(new Vector(0, 0, 1)), "dotProduct() for orthogonal vectors is not zero");

        // TC04: Test one normalized vector

        assertEquals(1, new Vector(1, 0, 0).dotProduct(new Vector(1, 0, 0)), "dotProduct() for normalized vector is not 1");


    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)} (Vector)} (primitives.Vector)}   .
     */

    @Test
    void crossProduct() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test


        assertEquals(new Vector(-1, 2, -1), new Vector(1, 2, 3).crossProduct(new Vector(2, 3, 4)), "crossProduct() wrong result");

        // TC02: Test negative vector

        assertEquals(new Vector(1, -2, 1), new Vector(1, 2, 3).crossProduct(new Vector(-2, -3, -4)), "crossProduct() wrong result");

        // =============== Boundary Values Tests ==================

        // TC03: Test same vectors

        try {
            new Vector(1, 2, 3).crossProduct(new Vector(1, 2, 3));
            fail("crossProduct() for same vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // TC04: Test opposite vectors

        try {
            new Vector(1, 2, 3).crossProduct(new Vector(-1, -2, -3));
            fail("crossProduct() for opposite vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // TC05: Test same direction vectors

        try{
            new Vector(1, 2, 3).crossProduct(new Vector(2, 4, 6));
            fail("crossProduct() for same direction vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // TC06: Test opposite direction vectors bur equals

        try{
            new Vector(1, 2, 3).crossProduct(new Vector(-2, -4, -6));
            fail("crossProduct() for opposite direction vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }





    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()} (Vector)} (primitives.Vector)}   .
     */

    @Test
    void lengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(14, new Vector(1, 2, 3).lengthSquared(), "lengthSquared() wrong result");
        // TC02: Test negative vector
        assertEquals(14, new Vector(-1, -2, -3).lengthSquared(), "lengthSquared() wrong result");




    }

    /**
     * Test method for {@link primitives.Vector#length()} (Vector)} (primitives.Vector)}   .
     */

    @Test
    void length() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(Math.sqrt(14), new Vector(1, 2, 3).length(), "length() wrong result");
        // TC02: Test negative vector
        assertEquals(Math.sqrt(14), new Vector(-1, -2, -3).length(), "length() wrong result");



    }

    /**
     * Test method for {@link primitives.Vector#normalize()} (Vector)} (primitives.Vector)}   .
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)), new Vector(1, 2, 3).normalize(), "normalize() wrong result");
        // TC02: Test negative vector
        assertEquals(new Vector(-1 / Math.sqrt(14), -2 / Math.sqrt(14), -3 / Math.sqrt(14)), new Vector(-1, -2, -3).normalize(), "normalize() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: Test normalized vector
        assertEquals(new Vector(1, 0, 0), new Vector(1, 0, 0).normalize(), "normalize() wrong result");

    }


    void subtruct(){
        Vector v1 = new Vector(1, 2, 3);


        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(-1, -1, -1), v1.subtract(new Vector(2, 3, 4)), "subtract() wrong result");

        // TC02: Test negative vector

        assertEquals(new Vector(3, 5, 7), v1.subtract(new Vector(-2, -3, -4)), "subtract() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: same direction vectors

        try {
            v1.subtract(new Vector(2, 4, 6));
            fail("subtract() for same direction vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // TC04: equal vectors

        try {
            v1.subtract(new Vector(1, 2, 3));
            fail("subtract() for equal vectors does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }



    }


}