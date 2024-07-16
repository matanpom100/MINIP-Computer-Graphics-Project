package renderer;


import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Class TargetBoard is a class that represents the target area surface.
 */
public class TargetBoard implements Cloneable {

    Vector vRight;

    Vector vUp;

    Vector vTo;


    Point p0;

    double height;

    double width;

    double distance;

    int density = 3;

    private TargetBoard() {
    }


    public static class Builder {

        private TargetBoard targetBoard = new TargetBoard();


        public Builder(Ray ray, double size) {
            setDirection(ray);
            targetBoard.height = targetBoard.width = size;
            targetBoard.distance = 100;
        }

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


        private Builder(TargetBoard targetBoard) {
            this.targetBoard = targetBoard;
        }

        public Builder calcVTo(Ray ray) {
            targetBoard.vTo = ray.getDirection().normalize();
            return this;
        }

        public TargetBoard build() {

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


            try {
                return (TargetBoard) targetBoard.clone(); //return a clone of the targetBoard
            } catch (CloneNotSupportedException e) { //if the clone is not supported, throw an exception
                throw new RuntimeException(e);
            }

        }

    }


    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public double getDensity() {
        return density;
    }


    public static TargetBoard.Builder getBuilder(Ray ray, double size) {
        return new Builder(ray, size);
    }

    public static TargetBoard.Builder getBuilder(Point p0, Vector vTo, Vector vUp, double width, double height, double distance) {
        return new Builder(p0, vTo, vUp, width, height, distance);
    }

    /**
     * Construct a ray through a pixel in the view plane
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the ray through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        return new Ray(p0, calcPIJ(nX, nY, j, i).subtract(p0)); //return the ray
    }

    /**
     * Calculate the point on the view plane corresponding to the pixel (the center of the pixel)
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the point on the view plane
     */
    private Point calcPIJ(int nX, int nY, int j, int i) {

        Point Pc = p0.add(vTo.scale(distance)); //center of the view plane

        double Yi = -(i - (nY - 1) / 2.0) * height / (double) nY;
        ; // y coordinate of the pixel
        double Xj = (j - (nX - 1) / 2.0) * width / (double) nX; // x coordinate of the pixel

        Point Pij = Pc;

        if (!isZero(Xj)) { //if Xj is not 0, move right
            Pij = Pij.add(vRight.scale(Xj)); // move right
        }
        if (!isZero(Yi)) { //if Yi is not 0, move up
            Pij = Pij.add(vUp.scale(Yi)); // move up
        }

        return Pij;
    }


    /**
     * Construct a beam of rays through the pixels in the view plane
     *
     * @return the beam of rays
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

    /**
     * Construct a beam of rays through the pixels in the view plane
     *
     * @return the beam of rays
     */
    public Color constructBeam(int nX, int nY, int j, int i, int sampleSize, RayTracerBase rayTracer) {
        Point pIJ = calcPIJ(nX, nY, j, i); //calculate the point on the view plane (the center of the pixel)
        double Y = height / (double) nY;
        double X = width / (double) nX;
        Color color = new Color(0, 0, 0); //initialize the color to black
        pIJ = pIJ.add(vRight.scale(-X / 2)); // move left
        pIJ = pIJ.add(vUp.scale(Y / 2)); // move up
        for (int k = 0; k < sampleSize + 1; k++) { //for each sample in the y direction
            for (int w = 0; w < sampleSize + 1; w++) { //for each sample in the x direction
                Point p = pIJ;
                if (!Util.isZero(w)) { //if w is not 0, move right
                    p = p.add(vRight.scale((w * X / sampleSize) - 0.5 + (Math.random() * (0.5 - (-0.5))) * (X / sampleSize)));
                }
                if (!Util.isZero(k)) { //if k is not 0, move up
                    p = p.add(vUp.scale((-k * Y / sampleSize) - 0.5 + (Math.random() * (0.5 - (-0.5))) * (Y / sampleSize))); // Typically, the y direction is inverted in image coordinates
                }
                Ray ray = new Ray(p0, p.subtract(p0)); //construct the ray
                color = color.add(rayTracer.traceRay(ray)); //trace the ray and add the color to the total color

            }
        }
        color = color.reduce((sampleSize + 1) * (sampleSize + 1)); //reduce the color
        return color; //return the color


    }


}
