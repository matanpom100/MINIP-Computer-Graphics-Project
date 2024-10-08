package geometrics;

import primitives.Point;
import primitives.Ray;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a bounding box in 3D space.
 */
public class BoundingBox {

    /**
     * Tow points that represent the minimum and maximum values of the bounding box.
     */
    private final Point min, max;


    public BoundingBox() {
        min = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        max = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public BoundingBox(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    /**
     * This method creates a bounding box that contains all the points in the list.
     * @param ray
     * @return
     */
    public boolean hasIntersection(Ray ray) {
        //if the ray is not in the bounding box
        double txmin = (min.getX() - ray.getHead().getX()) / ray.getDirection().getX();// get the t value of the intersection with the min x value
        double txmax = (max.getX() - ray.getHead().getX()) / ray.getDirection().getX(); // get the t value of the intersection with the max x value

        //if the intersection with the min x value is bigger than the intersection with the max x value
        if (txmin > txmax) { // swap the values if txmin is bigger than txmax
            double temp = txmin;
            txmin = txmax;
            txmax = temp;
        }

        double tymin = (min.getY() - ray.getHead().getY()) / ray.getDirection().getY();// get the t value of the intersection with the min y value
        double tymax = (max.getY() - ray.getHead().getY()) / ray.getDirection().getY();// get the t value of the intersection with the max y value

        //if the intersection with the min y value is bigger than the intersection with the max y value
        if (tymin > tymax) {//swap the values if tymin is bigger than tymax
            double temp = tymin;
            tymin = tymax;
            tymax = temp;
        }

        if ((txmin > tymax) || (tymin > txmax))//if the intersection with the y values is not in the intersection with the x values
            return false;

        if (tymin > txmin)//if the intersection with the y values is bigger than the intersection with the x values
            txmin = tymin;

        if (tymax < txmax)//if the intersection with the y values is smaller than the intersection with the x values
            txmax = tymax;

        double tzmin = (min.getZ() - ray.getHead().getZ()) / ray.getDirection().getZ();
        double tzmax = (max.getZ() - ray.getHead().getZ()) / ray.getDirection().getZ();

        if (tzmin > tzmax) {//swap the values if tzmin is bigger than tzmax
            double temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        if ((txmin > tzmax) || (tzmin > txmax))//if the intersection with the z values is not in the intersection with the x values
            return false;

        return true;

    }


    /**
     * Returns the center of the bounding box.
     * @return
     */
    private Point getCenter() {
        return new Point((min.getX() + max.getX()) / 2, (min.getY() + max.getY()) / 2,
                (min.getZ() + max.getZ()) / 2);
    }

    /**
     * Returns the minimum values of the bounding box.
     * @return min
     */
    public Point getMin() {
        return min;
    }

    /**
     * Returns the maximum values of the bounding box.
     * @return max
     */
    public Point getMax() {
        return max;
    }
}