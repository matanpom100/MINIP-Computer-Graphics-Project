package geometrics;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometrics.Tube}
 *
 * @author Matan and Eitan
 */
class TubeTest {


    /**
     * Test method for {@link geometrics.Tube#getNormal(primitives.Point)}   .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube(1, new Ray(new Point(1, 1, 1), new Vector(0, 1, 0)));
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(1, 3, 2)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC02: if the point is on the tube's axis, the normal is the vector from the point to the axis
        assertThrows(IllegalArgumentException.class, () -> tube.getNormal(new Point(1, 1, 1)), "Point on tube's axis should throw exception");

    }
}