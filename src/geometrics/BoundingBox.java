package geometrics;

import primitives.Point;
import primitives.Ray;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BoundingBox {

    private final Point min, max;


    public BoundingBox() {
        min = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        max = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public BoundingBox(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public boolean hasIntersection(Ray ray) {
        double txmin = (min.getX() - ray.getHead().getX()) / ray.getDirection().getX();// get the t value of the intersection with the min x value
        double txmax = (max.getX() - ray.getHead().getX()) / ray.getDirection().getX(); // get the t value of the intersection with the max x value

        if (txmin > txmax) { // swap the values if txmin is bigger than txmax
            double temp = txmin;
            txmin = txmax;
            txmax = temp;
        }

        double tymin = (min.getY() - ray.getHead().getY()) / ray.getDirection().getY();// get the t value of the intersection with the min y value
        double tymax = (max.getY() - ray.getHead().getY()) / ray.getDirection().getY();// get the t value of the intersection with the max y value

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


    private Point getCenter() {
        return new Point((min.getX() + max.getX()) / 2, (min.getY() + max.getY()) / 2,
                (min.getZ() + max.getZ()) / 2);
    }


    public Point getMin() {
        return min;
    }
    public Point getMax() {
        return max;
    }
}