package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.MissingResourceException;

/**
 * The camera class
 */
public class Camera implements Cloneable {


    /**
     * The image writer
     */
    ImageWriter imageWriter;
    /**
     * The ray tracer
     */
    RayTracerBase rayTracer;

    TargetBoard targetBoard;


    /**
     * Builder for the camera
     */
    public static class Builder {
        private final Camera camera;

        /**
         * Set the ray tracer
         *
         * @param rayTracer
         * @return The builder
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Set the image writer
         *
         * @param imageWriter
         * @return The builder
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        public Builder setTargetBoard(TargetBoard targetBoard) {
            camera.targetBoard = targetBoard;
            return this;
        }

        public Builder setThreadsCount(int threadsCount) {
            camera.threadsCount = threadsCount;
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
        public Builder setLocation(Point position) {
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
            if (to == null || up == null) { //if the vectors are null, throw an exception
                throw new IllegalArgumentException("The vectors cannot be null");
            }
            if (to.dotProduct(up) != 0) { //if the vectors are not orthogonal, throw an exception
                throw new IllegalArgumentException("The vectors are not orthogonal");
            }
            camera.to = to.normalize(); //normalize the to vector
            camera.up = up.normalize(); //normalize the up vector
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
            if (width <= 0 || height <= 0) { //if the width or height are not positive, throw an exception
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
            if (distance <= 0) { //if the distance is not positive, throw an exception
                throw new IllegalArgumentException("The distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        /**
         * Build the camera and throw an exception if a required field is missing
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

            camera.right = camera.to.crossProduct(camera.up).normalize(); //calculate the right vector

            if (!Util.isZero(camera.to.dotProduct(camera.up))) { //if the vectors are not orthogonal, throw an exception
                throw new IllegalArgumentException("The vectors are not orthogonal");
            }
            camera.targetBoard = TargetBoard.getBuilder(camera.position, camera.to, camera.up, camera.width, camera.height, camera.distance).build();
            try {
                return (Camera) camera.clone(); //return a clone of the camera
            } catch (CloneNotSupportedException e) { //if the clone is not supported, throw an exception
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
     * Pixel manager for supporting:
     * <ul>
     * <li>multi-threading</li>
     * <li>debug print of progress percentage in Console window/tab</li>
     * <ul>
     */
    private PixelManager pixelManager;


    /**
     * Getter for the position of the camera
     *
     * @return the position
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter for the width of the view plane
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    double printInterval = 0;

    int threadsCount = 0;

    /**
     * Getter for the distance of the camera from the view plane
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    private Camera() {

    }

    /**
     * Get a builder for the camera
     *
     * @return the builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }


    /**
     * Print a grid on the view plane
     *
     * @param interval
     * @param Color
     * @return the camera
     */
    public Camera printGrid(int interval, Color Color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) { //for each pixel
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) { //if the pixel the line should be drawn on
                    imageWriter.writePixel(j, i, Color); //write the color
                }
            }
        }
        return this;
    }

    /**
     * Render the image
     *
     * @return the camera
     */
    public Camera renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        pixelManager = new PixelManager(nY, nX, printInterval);

        if (threadsCount == 0) {
            for (int i = 0; i < nY; i++) { //for each pixel
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, j, i); // cast a ray through the pixel
                }

            }
        }
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null)
                        // cast ray through pixel (and color it – inside castRay)
                        castRay(nX, nY, pixel.col(), pixel.row());
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }
        return this;
    }

    /**
     * Render the image
     *
     * @return the camera
     */
    public Camera renderImage(int sampleSize) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        pixelManager = new PixelManager(nY, nX, printInterval);
        if (threadsCount == 0) {
            for (int i = 0; i < nY; i++) { //for each pixel
                for (int j = 0; j < nX; j++) {
                    castBeam(nX, nY, j, i, sampleSize); // cast a ray through the pixel
                }
            }
        }
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null)
                        // cast ray through pixel (and color it – inside castRay)
                        castBeam(nX, nY, pixel.col(), pixel.row(), sampleSize);
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }
        return this;
    }

    /**
     * Write the image to a file
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }

    /**
     * Cast a ray through a pixel
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     */
    private void castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i); //construct the ray
        Color color = rayTracer.traceRay(ray); //trace the ray
        imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(nX, nY, j, i)));
        pixelManager.pixelDone();
    }

    /**
     * Cast a beam through a pixel
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     */
    private void castBeam(int nX, int nY, int j, int i, int sampleSize) {
        Color color = constructBeam(nX, nY, j, i, sampleSize); //construct the rays
        imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(nX, nY, j, i)));
    }


    /**
     * Construct a ray through a pixel
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the ray through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return targetBoard.constructRay(nX, nY, j, i); //construct the ray (calls from the target board)
    }

    /**
     * Construct a beam of rays through the pixels in the view plane
     *
     * @return the beam of rays
     */
    public Color constructBeam(int nX, int nY, int j, int i, int sampleSize) {
        return targetBoard.constructBeam(nX, nY, j, i, sampleSize, rayTracer); //construct the beam of rays (calls from the target board)
    }


}
