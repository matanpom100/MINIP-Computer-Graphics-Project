package primitives;

import primitives.Point;
import primitives.Vector;


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

    @Override
    public String toString() {
        return super.toString() + "\n Ray:" +
                "head=" + head +
                ", direction=" + direction;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ray other &&
                head.equals(other.head) && direction.equals(other.direction);
    }


    public Vector getDirection() {
        return direction;
    }

    public Point getHead() {
        return head;
    }

}
