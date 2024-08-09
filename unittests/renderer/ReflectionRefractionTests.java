
package renderer;

import static java.awt.Color.*;

import geometrics.*;
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
                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(GREEN))
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
    public void flowerTestWithBVH() {

        Geometries flower = new Geometries();
        Geometries root = new Geometries();
        Geometries petals = new Geometries();
        Geometries center = new Geometries();

        Geometries bubbles = new Geometries();

        Geometries river = new Geometries();



        // Create a new scene with a white background
        Scene scene = new Scene("Flower Test")
                .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)))
                .setBackground(new Color(37, 150, 190));

        // Add the center of the flower
        center.add(
                new Sphere(500, new Point(0, 0, 0)).setEmission(new Color(255, 215, 0)) // Gold center
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
        );

        // Add the petals of the flower
        for (int i = 0; i < 360; i += 45) {
            double radian = Math.toRadians(i);
            double x = 700 * Math.cos(radian); // Reduced from 1000 to 700
            double y = 700 * Math.sin(radian); // Reduced from 1000 to 700
            petals.add(
                    new Sphere(300, new Point(0, x, y)).setEmission(new Color(205, 105, 80)) // Hot Pink petals
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
            );
        }

        // Add a bubble in the sky
        bubbles.add(
                new Sphere(400, new Point(-3000, -500, 500)) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setkT(1).setnShininess(100).setkS(0.2).setkD(0.2)) // Material properties with transparency
        );

//        for (int i = 0; i < 10; i++) { // Change the number 10 to the number of bubbles you want to add
//            double x = -3000 + Math.random() * 6000; // Random position for each bubble between -3000 and 3000
//            double y = -500 + Math.random() * 1000; // Random position for each bubble between -500 and 500
//            double z = 500 + Math.random() * 1000; // Random position for each bubble between 500 and 1500
//
//            bubbles.add(
//                    new Sphere(400, new Point(x*7, y*7, z*2)) // Position of the bubble
//                            .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
//                            .setMaterial(new Material().setkT(1).setnShininess(100).setkS(0.2).setkD(0.2)) // Material properties with transparency
//            );
//        }

        // Add the root of the flower
        int rootHeight = 1000; // The height of the root
        int rootRadius = 50; // The radius of the root
        int rootSegments = 150; // The number of spheres to use for the root
        for (int i = 0; i < rootSegments; i++) {
            int y = -rootHeight + i * (2 * rootHeight / rootSegments);

            root.add(
                    new Sphere(rootRadius, new Point(0, 0, y-1200))
                            .setEmission(new Color(34, 139, 34)) // Green root
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100))
            );
        }

        // Add the plane that will look like the nature
        scene.geometries.add(
                new Plane(new Point(0, 0, -1800), new Vector(0, 0, 1)) // Plane at y = -2000 with normal pointing upwards
                        .setEmission(new Color(34, 139, 34)) // Green color
                        .setMaterial(new Material().setkD(0.9).setkS(0.9).setnShininess(100)) // Material properties
        );

        // Add a polygon that will be a river with a reflection
        river.add(
                new Polygon(new Point(4000, 10000, -1798), new Point(7000, 8000, -1798), new Point(-10000, -7000, -1798), new Point(-10000, -5000, -1798))
                        .setEmission(new Color(15, 15, 240)) // Blue color
                        .setMaterial(new Material().setkR(0.8)) // Material properties
        );

        flower.add(center, petals, root);

        scene.geometries.add(flower, bubbles, river);

        // Add lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, -1, -1)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(-10000, 0, 0))
                .setDirection(new Vector(1,0,0), new Vector(0,0,1))
                .setViewPlaneSize(500, 500).setViewPlaneDistance(600)
                .setThreadsCount(4); // Set the number of threads to 4 (for faster rendering)

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("FlowerYesBVH", 1000, 1000))
                .build()
                .renderImage(20)
                .writeToImage();
    }


    @Test
    public void flowerTestWithoutBVH() {
        // Create a new scene with a white background
        Scene scene = new Scene("Flower Test")
                .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)))
                .setBackground(new Color(37, 150, 190));

        // Add the center of the flower
        scene.geometries.add(
                new Sphere(500, new Point(0, 0, 0)).setEmission(new Color(255, 215, 0)) // Gold center
                        .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
        );

        // Add the petals of the flower
        for (int i = 0; i < 360; i += 45) {
            double radian = Math.toRadians(i);
            double x = 700 * Math.cos(radian); // Reduced from 1000 to 700
            double y = 700 * Math.sin(radian); // Reduced from 1000 to 700
            scene.geometries.add(
                    new Sphere(300, new Point(0, x, y)).setEmission(new Color(205, 105, 80)) // Hot Pink petals
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(70))
            );
        }

        // Add a bubble in the sky
        scene.geometries.add(
                new Sphere(400, new Point(-3000, -500, 500)) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setkT(1).setnShininess(100).setkS(0.2).setkD(0.2)) // Material properties with transparency
        );

//        for (int i = 0; i < 10; i++) { // Change the number 10 to the number of bubbles you want to add
//            double x = -3000 + Math.random() * 6000; // Random position for each bubble between -3000 and 3000
//            double y = -500 + Math.random() * 1000; // Random position for each bubble between -500 and 500
//            double z = 500 + Math.random() * 1000; // Random position for each bubble between 500 and 1500
//
//            scene.geometries.add(
//                    new Sphere(400, new Point(x*7, y*7, z*2)) // Position of the bubble
//                            .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
//                            .setMaterial(new Material().setkT(1).setnShininess(100).setkS(0.2).setkD(0.2)) // Material properties with transparency
//            );
//        }

        // Add the root of the flower
        int rootHeight = 1000; // The height of the root
        int rootRadius = 50; // The radius of the root
        int rootSegments = 150; // The number of spheres to use for the root
        for (int i = 0; i < rootSegments; i++) {
            int y = -rootHeight + i * (2 * rootHeight / rootSegments);

            scene.geometries.add(
                    new Sphere(rootRadius, new Point(0, 0, y-1200))
                            .setEmission(new Color(34, 139, 34)) // Green root
                            .setMaterial(new Material().setkD(0.2).setkS(0.6).setnShininess(100))
            );
        }

        // Add the plane that will look like the nature
        scene.geometries.add(
                new Plane(new Point(0, 0, -1800), new Vector(0, 0, 1)) // Plane at y = -2000 with normal pointing upwards
                        .setEmission(new Color(34, 139, 34)) // Green color
                        .setMaterial(new Material().setkD(0.9).setkS(0.9).setnShininess(100)) // Material properties
        );

        // Add a polygon that will be a river with a reflection
        scene.geometries.add(
                new Polygon(new Point(4000, 10000, -1798), new Point(7000, 8000, -1798), new Point(-10000, -7000, -1798), new Point(-10000, -5000, -1798))
                        .setEmission(new Color(15, 15, 240)) // Blue color
                        .setMaterial(new Material().setkR(0.8)) // Material properties
        );




        // Add lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, -1, -1)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(-10000, 0, 0))
                .setDirection(new Vector(1,0,0), new Vector(0,0,1))
                .setViewPlaneSize(500, 500).setViewPlaneDistance(600);
                // .setThreadsCount(4); // Set the number of threads to 4 (for faster rendering)

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("FlowerNoBVH", 1000, 1000))
                .build()
                .renderImage(20)
                .writeToImage();
    }








    @Test
    public void testCameraRotation(){
        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);
        scene.center = new Point(0, 0, 0);
        Camera.Builder camera = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setViewPlaneDistance(100)
                .setViewPlaneSize(500, 500);

        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(-50, -50, -50);
        Point p3 = new Point(50, -50, -50);
        Point p4 = new Point(50, 50, 50);
        Point p5 = new Point(-50, 50, -50);
        scene.geometries.add(
                new Triangle(p1, p2, p3).setEmission(new Color(200, 0, 100)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Triangle(p1, p3, p4).setEmission(new Color(100, 200, 0)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100))

        );
        scene.lights.add(new DirectionalLight(new Color(225, 225, 225), new Vector(-1, -1, -1)));

        // right
        camera .setLocation(new Point(0, 0, 200)).lookAt(scene.center)
                .setImageWriter(new ImageWriter("camera rotation test1", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
        camera. setLocation(new Point(-100, -100, 200)).lookAt(scene.center)
                .setImageWriter(new ImageWriter("camera rotation test2", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();

        camera. setLocation(new Point(100, 100, -50)).lookAt(scene.center)
                .setImageWriter(new ImageWriter("camera rotation test3", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }









}
