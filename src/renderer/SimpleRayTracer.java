package renderer;

import geometrics.Intersectable;
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
        var intersections = scene.geometries.findGeoIntersections(ray); // Find the intersections of the ray with the geometries
        if (intersections == null) { // If there are no intersections
            return scene.background; // Return the background color
        }

        return calcColor(ray.findClosestGeoPoint(intersections)); // Calculate the color of the point
    }

    /**
     * Calculate the color of the point
     * @param point
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint point) { // Calculate the color of the point
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission()); // Return the color of the point
    }
}
