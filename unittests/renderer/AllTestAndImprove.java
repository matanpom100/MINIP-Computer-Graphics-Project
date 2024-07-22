package renderer;

import static java.awt.Color.*;

import geometrics.*;
import lighting.*;
import primitives.*;
import scene.Scene;
import org.junit.jupiter.api.Test;

public class AllTestAndImprove {

    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder of the tests
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneDistance(100)
            .setViewPlaneSize(500, 500);

    @Test
    public void BVHTestWithout() {
        // Parameters for the spheres
        int numSpheres = 200;
        double spacing = 10;
        double radius = 10;
        int gridSize = (int) Math.sqrt(numSpheres);

        for (int l = 0; l < 3; l++) {
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < gridSize; i++) {
                    for (int j = 0; j < gridSize; j++) {
                        double x = i * spacing - (gridSize * spacing / 2) -500 + k * 500;
                        double y = j * spacing - (gridSize * spacing / 2)-500 + l * 500;
                        double z = -200;
                        scene.geometries.add(new Sphere(radius, new Point(x, y, z))
                                .setMaterial(new Material().setkS(0.5).setkD(0.5).setnShininess(100))
                                .setEmission(new Color(0, 0, 225)));
                    }
                }

            }


        }


        // Set ambient light and background
        scene.setBackground(new Color(BLACK));

        // Add directional light
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, -1, -1)));

        scene.geometries.add(new Plane(new Point(0, 0, -400), new Vector(0, 0, 1))
                .setEmission(new Color(225, 50, 50))
                .setMaterial(new Material().setkS(0.5).setkD(0.5).setnShininess(100).setkR(0.3)
                ));
        // Render the scene
        camera.setLocation(new Point(0, 0, 100)).setImageWriter(new ImageWriter("BVH Test Render Without", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }

    @Test
    public void BVHTest() {
        // Parameters for the spheres
        int numSpheres = 200;
        double spacing = 10;
        double radius = 10;
        int gridSize = (int) Math.sqrt(numSpheres);

        for (int l = 0; l < 3; l++) {
            for (int k = 0; k < 3; k++) {
                Geometries g = new Geometries();
                for (int i = 0; i < gridSize; i++) {
                    for (int j = 0; j < gridSize; j++) {
                        double x = i * spacing - (gridSize * spacing / 2) -500 + k * 500;
                        double y = j * spacing - (gridSize * spacing / 2)-500 + l * 500;
                        double z = -200;
                        g.add(new Sphere(radius, new Point(x, y, z))
                                .setMaterial(new Material().setkS(0.5).setkD(0.5).setnShininess(100))
                                .setEmission(new Color(0, 0, 225)));
                    }
                }
                scene.geometries.add(g);
            }
        }

        scene.geometries.add(new Plane(new Point(0, 0, -400), new Vector(0, 0, 1))
                .setEmission(new Color(225, 50, 50))
                .setMaterial(new Material().setkS(0.5).setkD(0.5).setnShininess(100).setkR(0.3)
                ));

        // Set ambient light and background
        scene.setBackground(new Color(BLACK));

        // Add directional light
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, -1, -1)));

        //  scene.geometries.makeBVH(); // should improve performance

        // Render the scene
        camera.setLocation(new Point(0, 0, 100)).setImageWriter(new ImageWriter("BVH Test Render", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }

}

