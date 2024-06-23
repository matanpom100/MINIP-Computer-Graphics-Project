package primitives;

import primitives.Point;
import primitives.Vector;
import geometrics.Intersectable.GeoPoint;


import java.util.List;


/**
 * Ray class represents a ray in 3D Cartesian coordinate system
 * represented by a point and a direction
 */


public class Ray {

    final Point head;
    final Vector direction;


    /**
     * Ray constructor receiving a point and a vector
     *
     * @param head
     * @param direction
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * To string method for the ray
     * @return a string representation of the ray
     */
    @Override
    public String toString() {
        return super.toString() + "\n Ray:" +
                "head=" + head +
                ", direction=" + direction;
    }

    /**
     * Checks if the ray is equal to another ray
     * @param obj the other ray
     * @return true if the rays are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ray other &&
                head.equals(other.head) && direction.equals(other.direction);
    }


    // ***************** Getters/Setters ********************** //

    /**
     * Getter for the direction of the ray
     * @return the direction of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Getter for the head of the ray
     * @return the head of the ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns a point on the ray at a distance t from the head
     * @param t the distance from the head
     * @return the point on the ray at a distance t from the head
     */
    public Point getPoint(double t) {
        if (Util.isZero(t)) {//if t is 0, the point is the head of the ray
            return head;
        }

        return head.add(direction.scale(t));//returning the point on the ray at a distance t from the head
    }

    /**
     * Finds the closest point to the head of the ray from a list of points
     * @param points the list of points
     * @return the closest point to the head of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {

        GeoPoint close = null;

        double minDistance = Double.POSITIVE_INFINITY;

        for (GeoPoint p : points) {
            double distance = head.distance(p.point);
            if (distance < minDistance) {
                minDistance = distance;
                close = p;
            }
        }
        return close;

    }


}
