package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase class is an abstract class that represents a ray tracer
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray and returns the color of the closest intersection
     * @param ray
     * @return
     */
    public abstract Color traceRay(Ray ray);
}
