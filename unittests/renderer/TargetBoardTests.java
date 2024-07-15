package renderer;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetBoardTests {
    TargetBoard.Builder builder = new TargetBoard.Builder(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 200);

    @Test
    void testConstructRayBeamGrid() {
        List<Ray> result = builder.build().constructRays();

        //
        assertEquals(9, result.size(), "ERROR: findIntersections() did not return the right number of rays");

        //
        result = builder.build().constructRays().stream().filter(r -> r.getDirection().dotProduct(new Vector(0, 1, 0)) <= 0)
                .collect(Collectors.toList());
        assertEquals(6, result.size(), "ERROR: findIntersections() did not return the right number of reflected rays");

        result = builder.build().constructRays().stream().filter(r -> r.getDirection().dotProduct(new Vector(0, 1, 0)) > 0)
                .collect(Collectors.toList());
        assertEquals(3, result.size(), "ERROR: findIntersections() did not return the right number of refracted rays");


    }

}
