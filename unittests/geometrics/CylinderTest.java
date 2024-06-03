package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Cylinder}.
 *
 * @author Matan and Eitan
 */
class CylinderTest {

    /**
     * Test method for {@link geometrics.Cylinder#getNormal(primitives.Point)}   .
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: In case the point is on the cylinder's side

        Cylinder cylinder = new Cylinder(1, new Ray(new Point(1, 1, 1), new Vector(0, 1, 0)), 1);

        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 2, 1)), "Bad normal to cylinder");

        // TC02: In case the point is on the cylinder's top

        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 2, 1)), "Bad normal to cylinder");

        // TC03: In case the point is on the cylinder's bottom

        assertEquals(new Vector(0, -1, 0), cylinder.getNormal(new Point(1, 1, 1)), "Bad normal to cylinder");

        // =============== Boundary Values Tests ==================


        // TC04: In case the point is on the cylinder's top edge

        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 2, 0)), "Bad normal to cylinder");

        // TC05: In case the point is on the cylinder's bottom edge

        assertEquals(new Vector(0, -1, 0), cylinder.getNormal(new Point(1, 1, 0)), "Bad normal to cylinder");


    }
}