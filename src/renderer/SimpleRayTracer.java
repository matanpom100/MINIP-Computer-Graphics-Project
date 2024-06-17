package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * SimpleRayTracer class is a class that represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase {


    /**
     * Constructor
     * @param scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Trace the ray and calculate the color of the point
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) { // Calculate the color of the ray
        List<Point> intersections = scene.geometries.findIntersections(ray); // Find the intersections of the ray with the geometries
        if (intersections == null) { // If there are no intersections
            return scene.background; // Return the background color
        }
        Point closestPoint = ray.findClosestPoint(intersections); // Find the closest intersection point
        return calcColor(closestPoint); // Calculate the color of the point
    }

    /**
     * Calculate the color of the point
     * @param point
     * @return
     */
    private Color calcColor(Point point) { // Calculate the color of the point
        return scene.ambientLight.getIntensity();
    }
}
