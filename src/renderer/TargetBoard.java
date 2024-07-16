package renderer;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Class TargetBoard is a class that represents the target area surface.
 */
public class TargetBoard implements Cloneable {

    /**
     * The TargetArea's right vector
     */
    Vector vRight;

    /**
     * The TargetArea's up vector
     */
    Vector vUp;

    /**
     * The TargetArea's to vector
     */
    Vector vTo;


    /**
     * The TargetArea's position
     */
    Point p0;

    /**
     * The TargetArea's height
     */
    double height;

    /**
     * The TargetArea's width
     */
    double width;

    /**
     * The TargetArea's distance
     */
    double distance;

    /**
     * The TargetArea's density
     */
    int density =9;
    /**
     * The TargetArea's default constructor
     */
    private TargetBoard() {    }

    /**
     * The TargetArea's clone method Builder pattern
     */
    public static class Builder {

        /**
         * The Builder's Target board object
         */
        private TargetBoard targetBoard = new TargetBoard();



        /**
         * The Builder's constructor with a ray and a size
         */
        public Builder(Ray ray, double size) {
            setDirection(ray);
            targetBoard.height = targetBoard.width = size;
            targetBoard.distance = 100;
        }

        /**
         * The Builder's constructor with a point, two vectors, a width, a height and a distance
         */
        private Builder(Point p0, Vector vTo, Vector vUp, double width, double height, double distance) {
            targetBoard.p0 = p0;
            targetBoard.vTo = vTo.normalize();
            targetBoard.vUp = vUp.normalize();
            targetBoard.vRight = vTo.crossProduct(vUp).normalize();
            targetBoard.width = width;
            targetBoard.height = height;
            targetBoard.distance = distance;

        }



        /**
         * Set the TargetArea direction according to the ray
         *
         * @param ray the TargetArea direction
         * @return the TargetArea builder
         */
        public Builder setDirection(Ray ray) {
            targetBoard.p0 = ray.getHead();
            targetBoard.vTo = ray.getDirection();
            targetBoard.vRight = targetBoard.vTo.makePerpendicularVector();
            targetBoard.vUp = targetBoard.vRight.crossProduct(targetBoard.vTo);
            return this;
        }


        /**
         * the Builder's constructor with a TargetBoard
         * @param targetBoard
         */
        private Builder(TargetBoard targetBoard) {
            this.targetBoard = targetBoard;
        }




        /**
         * Build the TargetBoard
         * @return the TargetBoard
         */
        public TargetBoard build() {

            //check if the TargetBoard is initialized
            if (targetBoard.vRight == null) {
                throw new IllegalStateException("vRight is not initialized");
            }
            if (targetBoard.vUp == null) {
                throw new IllegalStateException("vUp is not initialized");
            }
            if (targetBoard.vTo == null) {
                throw new IllegalStateException("vTo is not initialized");
            }
            if (targetBoard.p0 == null) {
                throw new IllegalStateException("position is not initialized");
            }
            if (targetBoard.height == 0.0) {
                throw new IllegalStateException("height is not initialized");
            }
            if (targetBoard.width == 0.0) {
                throw new IllegalStateException("width is not initialized");
            }
            if (targetBoard.distance == 0.0) {
                throw new IllegalStateException("distance is not initialized");
            }
            if (targetBoard.density == 0) {
                throw new IllegalStateException("density is not initialized");
            }


            //return a clone of the targetBoard
            try {
                return (TargetBoard) targetBoard.clone(); //return a clone of the targetBoard
            } catch (CloneNotSupportedException e) { //if the clone is not supported, throw an exception
                throw new RuntimeException(e);
            }

        }

    }



    /**
     * Get the TargetArea's height
     * @return the TargetArea's height
     */
    public double getHeight() {
        return height;
    }


    /**
     * Get the TargetArea's width
     * @return the TargetArea's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the TargetArea's distance
     * @return the TargetArea's distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Get the TargetArea's density
     * @return the TargetArea's density
     */
    public double getDensity() {
        return density;
    }


    /**
     * Get the TargetArea's builder with a ray and a size -actually set the target area
     * @return the TargetArea's builder with a ray and a size
     */
    public static TargetBoard.Builder getBuilder(Ray ray, double size) {
        return new Builder(ray, size);
    }

    /**
     * Get the TargetArea's builder with a point, two vectors, a width, a height and a distance
     * @return the TargetArea's builder with a point, two vectors, a width, a height and a distance
     */
    public static TargetBoard.Builder getBuilder(Point p0, Vector vTo, Vector vUp, double width, double height, double distance) {
        return new Builder(p0, vTo, vUp,width, height,distance);
    }

    /**
     * Construct a ray through a pixel in the view plane
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the ray through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        Point Pc = p0.add(vTo.scale(distance)); //center of the view plane

        double Yi = -(i - (nY - 1) / 2.0) *  height / (double) nY;; // y coordinate of the pixel
        double Xj = (j - (nX - 1) / 2.0) * width / (double) nX; // x coordinate of the pixel

        Point Pij = Pc;//the pixel point

        if (!isZero(Xj)) { //if Xj is not 0, move right
            Pij = Pij.add(vRight.scale(Xj)); // move right
        }
        if (!isZero(Yi)) { //if Yi is not 0, move up
            Pij = Pij.add(vUp.scale(Yi)); // move up
        }

        return new Ray(p0, Pij.subtract(p0)); //return the ray
    }


    /**
     * Construct the rays through the pixels in the view plane
     * @return the rays through the pixels
     */
    public List<Ray> constructRays() {

        List<Ray> rays = new LinkedList<>();
        for (int i = 0; i < density; ++i) {
            for (int j = 0; j < density; j++) {
               rays.add(constructRay(density, density, j, i));
            }
        }
        return rays;

    }




}
