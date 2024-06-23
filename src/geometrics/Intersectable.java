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

    /**
     * Find the intersections of a ray with the geometry.
     *
     * @param ray The ray to find the intersections with.
     * @return A list of points where the ray intersects the geometry.
     */


    /**
     * A class that represents a point and the geometry it intersects.
     * Used for finding the closest intersection point.
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
         * @param geometry The geometry that the point intersects.
         * @param point The point that intersects the geometry.
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



    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    public final   List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
