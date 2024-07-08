package renderer;

import geometrics.Intersectable;
import geometrics.Sphere;
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
     * Maximum level of the color calculation recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * Minimum threshold value for the accumulated coefficient of transparency or reflection.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Initial coefficient for transparency and reflection
     */
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * The threshold value for the shadow rays
     */
    private static final double DELTA = 0.1;


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
        var intersections = findClosestIntersection(ray); // Find the intersections of the ray with the geometries
        if (intersections == null) { // If there are no intersections
            return scene.background; // Return the background color
        }
        return calcColor(intersections, ray); // Calculate the color of the point
    }


    /**
     * Calculate the color of the point
     * @param gp
     * @param ray
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }


    /**
     * Calculate the color of the point
     * @param intersection
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray,
                            int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculate the global effects on the point
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return the effects on the point
     */
    private Color calcGlobalEffects(Intersectable.GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        return calcGLobalEffect(constructRefractedRay(gp, v, n), material.kT, level, k).add(calcGLobalEffect(constructReflectedRay(gp, v, n), material.kR, level, k));
    }

    /**
     * Construct the reflected ray
     * @param gp
     * @param v
     * @param n
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        double nv = n.dotProduct(v);
        if (nv == 0) return null;

        Vector vec = v.subtract(n.scale(2 * nv));
        return new Ray(gp.point, vec, n);

    }

    /**
     * Construct the refracted ray
     * @param gp
     * @param v
     * @param n
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
    }

    /**
     * Calculate the global effect on the point
     * @param ray
     * @param kx
     * @param level
     * @param k
     * @return the effect on the point
     */
    private Color calcGLobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        Intersectable.GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, (level - 1), kkx)).scale(kx);
    }

    /**
     * Find the closest intersection of the ray with the geometries
     *
     * @param ray
     * @return the closest intersection
     */
    private Intersectable.GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }


    /**
     * Calculate the local effects of the point
     *
     * @param point
     * @return the color of the point
     */
    private Color calcLocalEffects(Intersectable.GeoPoint point, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(point, l, n, nv, lightSource);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(point.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * Calculate the specular light
     *
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return the color of the point
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double minusVR = -alignZero(v.dotProduct(r));
        return material.kS.scale(Math.pow(Math.max(0, minusVR), material.nShininess));
    }


    /**
     * Calculate the diffusive light
     *
     * @param material
     * @param nl
     * @return the color of the point
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }


    /**
     * Check if the point is shaded
     *
     * @param l
     * @param n
     * @param gp
     * @param lightSource
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(Intersectable.GeoPoint gp, Vector l, Vector n, double n1, LightSource lightSource) {
        Vector lightD = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n1 < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightD);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(gp.point));
        if (intersections != null) {
            double lightDistance = lightSource.getDistance(gp.point);
            for (Intersectable.GeoPoint geoPoint : intersections) {
                if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0)
                    if (geoPoint.geometry.getMaterial().kT == Double3.ZERO)
                        return false;
            }
        }
        return true;
    }

    /**
     * Calculate the transparency of the point
     *
     * @param gp
     * @param l
     * @param n
     * @param n1
     * @param lightSource
     * @return the transparency of the point
     */
    private Double3 transparency(Intersectable.GeoPoint gp, Vector l, Vector n, double n1, LightSource lightSource) {
        Vector lightD = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n1 < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightD);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(gp.point));
        if (intersections == null) return Double3.ONE;
        Double3 ktr = Double3.ONE;
        double lightDistance = lightSource.getDistance(gp.point);
        for (Intersectable.GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0) {
                ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    return Double3.ZERO;
                }
            }
        }
        return ktr;
    }
}
