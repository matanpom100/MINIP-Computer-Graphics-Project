package renderer;

import geometrics.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * SimpleRayTracer class is a class that represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase {


    /**
     * Constructor
     *
     * @param scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Trace the ray and calculate the color of the point
     *
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) { // Calculate the color of the ray
        var intersections = scene.geometries.findGeoIntersections(ray); // Find the intersections of the ray with the geometries
        if (intersections == null) { // If there are no intersections
            return scene.background; // Return the background color
        }

        return calcColor(ray.findClosestGeoPoint(intersections), ray); // Calculate the color of the point
    }

    /**
     * Calculate the color of the point
     *
     * @param point
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint point, Ray ray) { // Calculate the color of the point
        return scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray)); // Return the color of the point
    }

    private Color calcLocalEffects(Intersectable.GeoPoint point, Ray ray) {
        Color color = point.geometry.getEmission();
        Vector n = point.geometry.getNormal(point.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = point.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(point.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;

    }

    private Color calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {

    }

    private Color calcDiffusive(Material material, double nl) {

    }
}
