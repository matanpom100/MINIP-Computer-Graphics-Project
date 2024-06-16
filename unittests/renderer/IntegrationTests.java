package renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import geometrics.Geometry;
import geometrics.Intersectable;
import org.junit.jupiter.api.Test;

import primitives.*;
import geometrics.*;
import scene.Scene;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests of the camera with the geometries
 * The tests are based on the integration of the camera with the geometries
 * and the number of intersections that are found
 * @author Matan and Eitan
 */
public class IntegrationTests {

    private static final Camera.Builder cameraBuilder = new Camera.Builder()
            .setLoaction(Point.ZERO)
            .setImageWriter(new ImageWriter("Test", 1, 1))
            .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneDistance(1).setViewPlaneSize(3, 3);


    /**
     * Test the integration of the camera with the triangles
     */
    @Test
    void testTriangleIntegrations() {
        /////////////////////////// Triangle Tests ///////////////////////////

        // TC01: Triangle with vertices (0, 1, -2), (-1, -1, -2), (1, -1, -2) - 1 intersection
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        assertEquals(1, getGeoIntersections(triangle).size(), "Triangle test failed");

        // TC02: Triangle with vertices (0, 20, -2), (-1, -1, -2), (1, -1, -2) - 2 intersections
        triangle = new Triangle(new Point(0, 20, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        assertEquals(2, getGeoIntersections(triangle).size(), "Triangle test failed");
    }

    /**
     * Test the integration of the camera with the Sphere
     */
    @Test
    void testSphereIntegrations() {
        /////////////////////////// Sphere Tests ///////////////////////////

        // TC01: Sphere with radius 1 at (0, 0, -3) - 2 intersections
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));

        cameraBuilder.build();
        assertEquals(2, getGeoIntersections(sphere).size(), "Sphere test failed");

        // TC02: Sphere with radius 2.5 at (0, 0, -2.5) - 18 intersections
        cameraBuilder.setLoaction(new Point(0,0,0.5));
        cameraBuilder.build();
        sphere = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, getGeoIntersections(sphere).size(), "Sphere test failed");

        // TC03: Sphere with radius 2 at (0, 0, -2) - 10 intersections
        sphere = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, getGeoIntersections(sphere).size(), "Sphere test failed");

        // TC04: Sphere with radius 4 at (0, 0, 0) - 9 intersections
        sphere = new Sphere(4, new Point(0, 0, 0));
        assertEquals(9, getGeoIntersections(sphere).size(), "Sphere test failed");

        // TC05: Sphere with radius 0.5 at (0, 0, 1) - 0 intersections
        sphere = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0, getGeoIntersections(sphere).size(), "Sphere test failed");
    }

    /**
     * Test the integration of the camera with the Plane
     */
    @Test
    void testPlaneIntegrations() {
        /////////////////////////// Plane Tests ///////////////////////////

        Plane plane = new Plane(new Point(0, 0, -1), new Vector(0, 0, -1));

        // TC01: Plane with normal (0, 0, -1) and point (0, 0, -1) - 9 intersections
        assertEquals(9, getGeoIntersections(plane).size(), "Plane test failed");

        // TC02: Plane with normal (0, 0, -1) and point (0, 0, -2) - 9 intersections

        plane = new Plane(new Point(0, 0, -2), new Vector(0, 0, -1));
        assertEquals(9, getGeoIntersections(plane).size(), "Plane test failed");

        // TC03: Plane with normal (0, 0, -1) and point (0, 0, 1) - 0 intersections

        cameraBuilder.setLoaction(new Point(0,0,0));
        cameraBuilder.build();
        plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, -1));
        assertEquals(0, getGeoIntersections(plane).size(), "Plane test failed");
    }

    private List<Point> getGeoIntersections(Geometry geo){ //get the intersections of the geometry with the camera

        ArrayList<Point>  inter = new ArrayList<>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                Ray ray = cameraBuilder.build().constructRay(3, 3, i, j);
                List<Point> intersections = geo.findIntersections(ray);
                if (intersections != null){
                    inter.addAll(intersections);
                }
            }
        }
        return inter;

    }


}
