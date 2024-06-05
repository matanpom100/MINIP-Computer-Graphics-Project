package renderer;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Locale;
import java.util.MissingResourceException;


public class Camera implements Cloneable{

    public static class Builder{
        private final Camera camera ;


        public Builder (){
            camera = new Camera();
        }

        public Builder (Camera camera){
            this.camera = camera;
        }

        public Builder setLoaction(Point position){
            if (position == null){
                throw new IllegalArgumentException("The position cannot be null");
            }
            camera.position = position;
            return this;
        }

        public Builder setDirection(Vector to, Vector up){

            if (to == null || up == null){
                throw new IllegalArgumentException("The vectors cannot be null");
            }
            if (to.dotProduct(up) != 0){
                throw new IllegalArgumentException("The vectors are not orthogonal");
            }
            camera.to = to.normalize();
            camera.up = up.normalize();

            return this;


        }

        public Builder setViewPlaneSize(double width, double height){
            if (width <= 0 || height <= 0){
                throw new IllegalArgumentException("The width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Builder setViewPlaneDistance(double distance){
            if (distance <= 0){
                throw new IllegalArgumentException("The distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        public Camera build(){
            if (camera.position == null){
                throw new MissingResourceException("The position was not initialized", "Camera", "position");
            }
            if (camera.to == null){
                throw new MissingResourceException("The to vector was not initialized", "Camera", "to");
            }
            if (camera.up == null){
                throw new MissingResourceException("The up vector was not initialized", "Camera", "up");
            }
            if (camera.right == null){
                throw new MissingResourceException("The width was not initialized", "Camera", "right");
            }
            if (camera.width == 0){
                throw new MissingResourceException("The width was not initialized", "Camera", "width");
            }
            if (camera.height == 0){
                throw new MissingResourceException("The height was not initialized", "Camera", "height");
            }
            if (camera.distance == 0){
                throw new MissingResourceException("The distance was not initialized", "Camera", "distance");
            }

            camera.right = camera.to.crossProduct(camera.up).normalize();
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }




        }



    }



    Point position;
    Vector right;
    Vector to;
    Vector up;
    double height = 0.0;
    double width = 0.0;
    double distance = 0.0;

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    private Camera(){

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
    }











}
