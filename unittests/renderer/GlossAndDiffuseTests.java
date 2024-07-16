package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometrics.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;


class GlossAndBlurTests {
    @Test
    public void settlementScene() {
        // Create a new scene with a white background
        Scene scene = new Scene("Settlement Scene")
                .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.2)))
                .setBackground(new Color(135, 206, 250)); // Sky blue background

        // Define common properties
        double riverWidth = 400;
        double groundLevel = -1800;



        // Add the river segments
        scene.geometries.add(
                new Polygon(
                        new Point(5000, -2500, groundLevel + 1),
                        new Point(1500, -2500, groundLevel + 1),
                        new Point(1500, riverWidth / 2, groundLevel + 1),
                        new Point(5000, riverWidth / 2, groundLevel + 1)
                ).setEmission(new Color(0, 0, 90)) // Blue color
                        .setMaterial(new Material().setkR(1).setkG(3)) // Semi-transparent and reflective water
        );
        scene.geometries.add(
                new Polygon(
                        new Point(4000, 500, groundLevel + 1),        // נקודה תחתונה שמאלית
                        new Point(4000, 500, groundLevel + 1000),      // נקודה עליונה שמאלית
                        new Point(4000, 3500, groundLevel + 1000),     // נקודה עליונה ימנית
                        new Point(4000, 3500, groundLevel + 1)        // נקודה תחתונה ימנית
                ).setEmission(new Color(0, 0, 90)) // Blue color
                        .setMaterial(new Material().setkT(0.3).setkB(10)) // Semi-transparent and reflective water
        );



        // Add the ground plane
        scene.geometries.add(
                new Plane(new Point(0, 0, groundLevel), new Vector(0, 0, 1))
                        .setEmission(new Color(34, 139, 34)) // Green color for grass
                        .setMaterial(new Material().setkD(0.9).setkS(0.4).setnShininess(50))
        );

        // Add spheres along the river segments
        scene.geometries.add(
                new Sphere(300, new Point(5000, -2000, groundLevel+300))
                        .setEmission(new Color(255, 0, 0)) // Red color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000, -500, groundLevel + 300))
                        .setEmission(new Color(0, 255, 0)) // Green color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000, 1000, groundLevel + 300))
                        .setEmission(new Color(0, 0, 255)) // Blue color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000,2500 , groundLevel + 300))
                        .setEmission(new Color(255, 255, 0)) // Yellow color
                        .setMaterial(new Material().setnShininess(100))
        );





        // Add lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(-10000, 0, 1000))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1))  // Correctly orthogonal vectors
                .setViewPlaneSize(5000, 5000).setViewPlaneDistance(10000);

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("SettlementScene", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }



    @Test

    public void settlementScene1() {
        // Create a new scene with a white background
        Scene scene = new Scene("Settlement Scene1")
                .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.2)))
                .setBackground(new Color(135, 206, 250)); // Sky blue background

        // Define common properties
        double riverWidth = 400;
        double groundLevel = -1800;



        // Add the river segments
        scene.geometries.add(
                new Polygon(
                        new Point(5000, -2500, groundLevel + 1),
                        new Point(1500, -2500, groundLevel + 1),
                        new Point(1500, riverWidth / 2, groundLevel + 1),
                        new Point(5000, riverWidth / 2, groundLevel + 1)
                ).setEmission(new Color(0, 0, 90)) // Blue color
                        .setMaterial(new Material().setkR(1)) // Semi-transparent and reflective water
        );
        scene.geometries.add(
                new Polygon(
                        new Point(4000, 500, groundLevel + 1),        // נקודה תחתונה שמאלית
                        new Point(4000, 500, groundLevel + 1000),      // נקודה עליונה שמאלית
                        new Point(4000, 3500, groundLevel + 1000),     // נקודה עליונה ימנית
                        new Point(4000, 3500, groundLevel + 1)        // נקודה תחתונה ימנית
                ).setEmission(new Color(0, 0, 90)) // Blue color
                        .setMaterial(new Material().setkT(0.3)) // Semi-transparent and reflective water
        );



        // Add the ground plane
        scene.geometries.add(
                new Plane(new Point(0, 0, groundLevel), new Vector(0, 0, 1))
                        .setEmission(new Color(34, 139, 34)) // Green color for grass
                        .setMaterial(new Material().setkD(0.9).setkS(0.4).setnShininess(50))
        );

        // Add spheres along the river segments
        scene.geometries.add(
                new Sphere(300, new Point(5000, -2000, groundLevel+300))
                        .setEmission(new Color(255, 0, 0)) // Red color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000, -500, groundLevel + 300))
                        .setEmission(new Color(0, 255, 0)) // Green color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000, 1000, groundLevel + 300))
                        .setEmission(new Color(0, 0, 255)) // Blue color
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(100))
        );
        scene.geometries.add(
                new Sphere(300, new Point(5000,2500 , groundLevel + 300))
                        .setEmission(new Color(255, 255, 0)) // Yellow color
                        .setMaterial(new Material().setnShininess(100))
        );





        // Add lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(-10000, 0, 1000))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1))  // Correctly orthogonal vectors
                .setViewPlaneSize(5000, 5000).setViewPlaneDistance(10000);

        // Render the image and write it to a file
        cameraBuilder.setImageWriter(new ImageWriter("SettlementSceneNonImproved", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }


        private Scene scene = new Scene("Test scene");

        @Test
        public void twoSpheresOnMirrors() {




            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

            scene.geometries.add( //
                    new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)
                                    .setkT(new Double3(0.5, 0, 0)).setkB(0)),
                    new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                            .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                    new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                            .setEmission(new Color(20, 20, 20)) //
                            .setMaterial(new Material().setkR(1).setkG(10)),
                    new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                            new Point(-1500, -1500, -2000)) //
                            .setEmission(new Color(20, 20, 20)) //
                            .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4)).setkG(10)));

            scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                    .setkL(0.00001).setkQ(0.000005));


            Camera.Builder cameraBuilder = Camera.getBuilder()
                    .setRayTracer(new SimpleRayTracer(scene))
                    .setLocation(new Point(0, 0, 10000))
                    .setDirection( new Vector(0, 0, -1),new Vector(0, 1, 0))  // Correctly orthogonal vectors
                    .setViewPlaneSize(2500, 2500).setViewPlaneDistance(10000);

            cameraBuilder.setImageWriter(new ImageWriter("reflection", 500, 500));
            cameraBuilder.build().renderImage().writeToImage();

        }

        /** Geometry combination presenting glossing and diffusion */
        @Test
        public void geometryCombinationTest() {


            scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

            scene.geometries.add(
                    new Sphere(50d, new Point(0, 0, 0)).setMaterial(new Material().setkD(0.3).setkS(0.5).setnShininess(10))
                            .setEmission(new Color(130, 80, 0)),
                    new Triangle(new Point(-10, 0, 70), new Point(0, 90, 40), new Point(80, 0, 50))
                            .setMaterial(new Material().setkT(0.3).setkB(20)).setEmission(new Color(GRAY)),
                    new Plane(new Point(70, 0, -140), new Vector(-0.35, 0, 1))
                            .setMaterial(new Material().setkG(7).setkR(0.9)).setEmission(new Color(0, 30, 50)));

            scene.setBackground(new Color(30, 10, 0));
            scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                    .setkL(4E-5).setkQ(2E-7));

            Camera.Builder cameraBuilder = Camera.getBuilder().setLocation(new Point(40, 0, 1000)).setDirection( new Vector(0, 0, -1), new Vector(0, 1, 0))
                    .setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

            ImageWriter imageWriter = new ImageWriter("BlurredNewCombo1", 500, 500);

            cameraBuilder.setImageWriter(imageWriter)
                    .setRayTracer(new SimpleRayTracer(scene))
                    .build()
                    .renderImage()
                    .writeToImage();
        }

    }


