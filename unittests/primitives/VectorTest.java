package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 * @author Matan and Eitan
 */

class VectorTest {


    /**
     *  Test method for  {@link primitives.Vector#subtract(Point)}
     */

    @Test
    void subtruct(){
        Vector v1 = new Vector(1, 2, 3);


        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(-1, -1, -1), v1.subtract(new Vector(2, 3, 4)), "subtract() wrong result");

        // TC02: Test negative vector

        assertEquals(new Vector(3, 5, 7), v1.subtract(new Vector(-2, -3, -4)), "subtract() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03: Test zero vector

        try {
            v1.subtract(new Vector(0, 0, 0));
            fail("subtract() for zero vector does not throw an exception");
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
        // TC03: Test zero vector

        try {
            new Vector(0, 0, 0).add(new Vector(0, 0, 0));
            fail("add() for zero vector does not throw an exception");
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
        // TC03: Test zero vector

        try {
            new Vector(0, 0, 0).scale(1);
            fail("scale() for zero vector does not throw an exception");
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
        // TC03: Test zero vector


        try {
            new Vector(0, 0, 0).dotProduct(new Vector(0, 0, 0));
            fail("dotProduct() for zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
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

        // TC03: Test zero vector

        try {
            new Vector(0, 0, 0).crossProduct(new Vector(0, 0, 0));
            fail("crossProduct() for zero vector does not throw an exception");
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

        // =============== Boundary Values Tests ==================

        // TC03: Test zero vector

        try {
            new Vector(0, 0, 0).lengthSquared();
            fail("lengthSquared() for zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }



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

            // =============== Boundary Values Tests ==================

            // TC03: Test zero vector

            try {
                new Vector(0, 0, 0).length();
                fail("length() for zero vector does not throw an exception");
            } catch (IllegalArgumentException e) {
                assertTrue(true);
            }

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

            // TC03: Test zero vector

            try {
                new Vector(0, 0, 0).normalize();
                fail("normalize() for zero vector does not throw an exception");
            } catch (IllegalArgumentException e) {
                assertTrue(true);
            }
    }


}