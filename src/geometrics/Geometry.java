package geometrics;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface is the basic interface for all geometric shapes
 */
public abstract class Geometry extends Intersectable {


    Material material = new Material();

    /**
     * Getter for the material
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Setter for the material
     * @param material
     * @return this
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @param point
     * @return the normal vector to the geometry at the point
     */
    public abstract Vector getNormal(Point point);


    /**
     * @param point
     * @return the emission color of the geometry at the point
     */
    protected Color emission = Color.BLACK;


    /**
     * getter for the emission color
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }


    /**
     * setter for the emission color
     * @param emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

}
