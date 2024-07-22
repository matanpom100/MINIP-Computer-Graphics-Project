package geometrics;

import primitives.Point;
import primitives.Ray;

import java.util.List;


/**
 * Intersectable interface represents a geometry that can be intersected by a ray.
 *
 * @author Matan and Eitan
 */

public abstract class Intersectable {

    protected BoundingBox box;


    /**
     * A class that represents a point and the geometry it intersects.
     * Used for finding the closest intersection point.
     *
     * @author Matan and Eitan
     */
    public static class GeoPoint {
        /**
         * The geometry that the point intersects.
         */
        public Geometry geometry;
        /**
         * The point that intersects the geometry.
         */
        public Point point;

        /**
         * Constructor for GeoPoint.
         *
         * @param geometry The geometry that the point intersects.
         * @param point    The point that intersects the geometry.
         */

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }


    }

    /**
     * Calculate the bounding box of the geometry.
     */
    protected abstract void calculateBoundingBox();


    /**
     * Find the intersections of a ray with the geometry.
     * @param ray
     * @return the list of points where the ray intersects the geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * Find the intersections of a ray with the geometry.
     * @param ray
     * @return
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    /**
     * Find the intersections of a ray with the geometry.
     * @param ray
     * @return the findGeoIntersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        if (box != null && !box.hasIntersection(ray))
            return null;
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Find the intersections of a ray with the geometry within a certain distance.
     * @param ray
     * @param maxDistance
     * @return the findGeoIntersectionsHelper
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Find the intersections of a ray with the geometry within a certain distance.
     * @param ray
     * @param maxDistance
     * @return
     */
    protected abstract List<GeoPoint>
    findGeoIntersectionsHelper(Ray ray, double maxDistance);

    public BoundingBox getBoundingBox() {
        return box;
    }


}
