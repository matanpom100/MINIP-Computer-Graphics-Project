package renderer;

import primitives.Point;
import primitives.Ray;

public class BoundingBox {

    private  final Point min, max;




    public BoundingBox() {
        this.min = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        this.max = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public BoundingBox(BoundingBox other) {
        this.min = other.min;
        this.max = other.max;
    }

    public BoundingBox(Point p1, Point p2) {
        this.min = new Point(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ()));
        this.max = new Point(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()), Math.max(p1.getZ(), p2.getZ()));
    }

    public  boolean isIntersect(Ray ray){
        double txmin = (min.getX() - ray.getHead().getX()) / ray.getDirection().getX();// get the t value of the intersection with the min x value
        double txmax = (max.getX() - ray.getHead().getX()) / ray.getDirection().getX(); // get the t value of the intersection with the max x value

        if (txmin > txmax) {//swap the values if txmin is bigger than txmax
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




}
