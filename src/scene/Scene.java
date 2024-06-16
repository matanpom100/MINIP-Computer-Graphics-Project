package scene;

import geometrics.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Scene class represents a scene in 3D Cartesian coordinate system
 * The scene contains a name, background color, ambient light and geometries
 * The scene is the main class that contains all the objects in the scene
 * @Author Matan and Eitan
 */

public class Scene {

    /**
     * The scene's name
     */
    public String name;

    /**
     * The scene's background color
     */
    public Color background = Color.BLACK;

    /**
     * The scene's ambient light - set to NONE by default
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * The scene's geometries - set to an empty list by default
     */
    public Geometries geometries = new Geometries();


    /**
     * Scene constructor based on a name
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Scene setter - sets the background color of the scene
     * @param background the background color of the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Scene setter - sets the ambient light of the scene
     * @param ambientLight the ambient light of the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Scene setter - sets the geometries of the scene
     * @param geometries the geometries of the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }






}
