package renderer;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Color;

import java.util.Locale;
import java.util.MissingResourceException;

import static primitives.Util.isZero;


public class Camera implements Cloneable {

    ImageWriter imageWriter;
    RayTracerBase rayTracer;

    /**
     * Builder for the camera
     */
    public static class Builder {
        private final Camera camera;

        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Constructor for the builder
         */
        public Builder() {
            camera = new Camera();
        }

        /**
         * Constructor for the builder
         *
         * @param camera
         */
        public Builder(Camera camera) {
            this.camera = camera;
        }

        /**
         * Set the camera position
         *
         * @param position
         * @return The builder
         */
        public Builder setLoaction(Point position) {
            if (position == null) {
                throw new IllegalArgumentException("The position cannot be null");
            }
            camera.position = position;
            return this;
        }

        /**
         * Set the direction of the camera
         *
         * @param to
         * @param up
         * @return The builder
         */
        public Builder setDirection(Vector to, Vector up) {

            if (to == null || up == null) {
                throw new IllegalArgumentException("The vectors cannot be null");
            }
            if (to.dotProduct(up) != 0) {
                throw new IllegalArgumentException("The vectors are not orthogonal");
            }
            camera.to = to.normalize();
            camera.up = up.normalize();

            return this;


        }

        /**
         * Set the view plane size
         *
         * @param width
         * @param height
         * @return The builder
         */
        public Builder setViewPlaneSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("The width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Set the view plane distance
         *
         * @param distance
         * @return The builder
         */
        public Builder setViewPlaneDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("The distance must be positive");
            }
            camera.distance = distance;
            return this;
        }



        /**
         * Build the camera
         *
         * @return the camera
         */
        public Camera build() {
            if (camera.position == null) {
                throw new MissingResourceException("The position was not initialized", "Camera", "position");
            }
            if (camera.to == null) {
                throw new MissingResourceException("The to vector was not initialized", "Camera", "to");
            }
            if (camera.up == null) {
                throw new MissingResourceException("The up vector was not initialized", "Camera", "up");
            }
            if (camera.width == 0.0) {
                throw new MissingResourceException("The width was not initialized", "Camera", "width");
            }
            if (camera.height == 0.0) {
                throw new MissingResourceException("The height was not initialized", "Camera", "height");
            }
            if (camera.distance == 0.0) {
                throw new MissingResourceException("The distance was not initialized", "Camera", "distance");
            }
            if (camera.rayTracer == null) {
                throw new MissingResourceException("The ray tracer was not initialized", "Camera", "rayTracer");
            }
            if (camera.imageWriter == null) {
                throw new MissingResourceException("The image writer was not initialized", "Camera", "imageWriter");
            }

            camera.right = camera.to.crossProduct(camera.up).normalize();
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

        }

    }


    /**
     * The camera position
     */
    Point position;
    /**
     * Right vector
     */
    Vector right;
    /**
     * To vector
     */
    Vector to;
    /**
     * Up vector
     */
    Vector up;
    /**
     * Camera height
     */
    double height = 0.0;
    /**
     * Camera width
     */
    double width = 0.0;
    /**
     * Camera distance
     */
    double distance = 0.0;

    /**
     * Getter for the position of the camera
     * @return the position
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter for the width of the view plane
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter for the distance of the camera from the view plane
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    private Camera() {

    }

    /**
     * Get a builder for the camera
     * @return the builder
     */
    public static Builder getBuilder() {
        return new Builder();
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

        Point Pc = position.add(to.scale(distance)); //center of the view plane

        double Ry = height / (double) nY; // height of a pixel
        double Rx = width / (double) nX; // width of a pixel

        double Yi = -(i - (nY - 1) / 2.0) * Ry; // y coordinate of the pixel
        double Xj = (j - (nX - 1) / 2.0) * Rx; // x coordinate of the pixel

        Point Pij = Pc;

        if (!isZero(Xj)) { //if Xj is not 0, move right
            Pij = Pij.add(right.scale(Xj)); // move right
        }
        if (!isZero(Yi)) { //if Yi is not 0, move up
            Pij = Pij.add(up.scale(Yi)); // move up
        }

        return new Ray(position, Pij.subtract(position)); //return the ray
    }

    public Camera printGrid(int interval, Color Color){
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, Color);
                }
            }
        }
        return this;
    }

    public Camera renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRay(nX, nY, j, i);
            }
        }
        return this;
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }

    private void castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }


}
