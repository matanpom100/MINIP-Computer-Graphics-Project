
package renderer;

import static java.awt.Color.*;

import geometrics.Plane;
import geometrics.Polygon;
import geometrics.Sphere;
import geometrics.Triangle;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;


import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
        scene.lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setkL(0.0004).setkQ(0.0000006));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setViewPlaneDistance(1000)
                .setViewPlaneSize(150, 150)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        scene.geometries.add(
                new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setkR(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setkL(0.00001).setkQ(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setViewPlaneDistance(10000)
                .setViewPlaneSize(2500, 2500)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        scene.geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
                new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setkL(4E-5).setkQ(2E-7));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setViewPlaneDistance(1000)
                .setViewPlaneSize(200, 200)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }


    @Test
    public void tenGeometriesTest() {
        // Create a new scene with a black background
        Scene scene = new Scene("Ten Geometries Test")
                .setAmbientLight(new AmbientLight(new Color(120, 121, 145), new Double3(0.15)))
                .setBackground(new Color(205, 205, 200));

        // Add the three spheres on top of each other
        scene.geometries.add(
                new Sphere(15, new Point(0, -10, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Sphere(15, new Point(0, 20, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Sphere(15, new Point(0, 50, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),

                new Sphere(10, new Point(0, 50, 7)).setEmission(new Color(183,197,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),

                new Sphere(5, new Point(0, 50, 15)).setEmission(new Color(255,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),

                new Sphere(10, new Point(-25, 20, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Sphere(10, new Point(25, 20, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Sphere(10, new Point(-15, -30, 10)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Sphere(10, new Point(15, -30, -10)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Plane(new Point(0, -70, 0), new Vector(0, 1, 0)) // Plane at y = -50 with normal pointing upwards
                        .setEmission(new Color(100, 100, 100)) // Gray color
                        .setMaterial(new Material().setkD(0.9).setkS(0.9).setnShininess(100)) // Material properties

        );


        scene.geometries.add(
                new Triangle(new Point(-25, 20, 0), new Point(-45, -20, 0), new Point(-45, -30, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100)),
                new Triangle(new Point(-25, 20, 0), new Point(-45, -20, 0), new Point(-45, -30, 0)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100))
        );


        // Add lights
        scene.lights.add(new DirectionalLight(new Color(227,227,218), new Vector(-1, -1, -2)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
                .setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("Hero6", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    @Test
    public void flowerTest() {
        // Create a new scene with a white background
        Scene scene = new Scene("Flower Test")
                .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)))
                .setBackground(new Color(0, 100, 255));

        // Add the center of the flower
        scene.geometries.add(
                new Sphere(500, new Point(0, 0, -500)).setEmission(new Color(255, 215, 0)) // Gold center
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
        );

        // Add the petals of the flower
        for (int i = 0; i < 360; i += 45) {
            double radian = Math.toRadians(i);
            double x = 700 * Math.cos(radian); // Reduced from 1000 to 700
            double y = 700 * Math.sin(radian); // Reduced from 1000 to 700
            scene.geometries.add(
                    new Sphere(300, new Point(x, y, -500)).setEmission(new Color(255, 105, 180)) // Hot Pink petals
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
            );
        }

        // Add the root of the flower
        int rootHeight = 1000; // The height of the root
        int rootRadius = 50; // The radius of the root
        int rootSegments = 150; // The number of spheres to use for the root
        for (int i = 0; i < rootSegments; i++) {
            int y = -rootHeight + i * (2 * rootHeight / rootSegments);

            scene.geometries.add(
                    new Sphere(rootRadius, new Point(0, y-1200, -370))
                            .setEmission(new Color(34, 139, 34)) // Green root
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100))
            );
        }

        // Add the plane that will look like the nature
        scene.geometries.add(
                new Plane(new Point(0, -2000, 0), new Vector(0, 1, 0)) // Plane at y = -2000 with normal pointing upwards
                        .setEmission(new Color(34, 139, 34)) // Green color
                        .setMaterial(new Material().setkD(0.9).setkS(0.9).setnShininess(100)) // Material properties
        );

        // Add the plane that will look like the sky


       scene.geometries.add(
           new Polygon(new Point(-200, 100, 100), new Point(-200, 100, -100), new Point(200, 100, -100), new Point(200, 100, 100))
                   .setEmission(new Color(255, 25, 25))// Blue color0
                   .setMaterial(new Material().setkD(0.1).setkS(0.6).setnShininess(100).setkT(0.6))



       );

        // Add the polygon that will look like the camera looking through a window

        // Add lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -2)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(0, 0, 2000))
                .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
                .setViewPlaneSize(2000, 2000).setViewPlaneDistance(1000);

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("Flower", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }


}
