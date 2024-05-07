package primitives;
import primitives.Point;
import primitives.Vector;

public class Ray {

    final Point head;
    final Vector direction;

    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    @Override
    public String toString() {
        return super.toString()+"\n Ray:" +
                "head=" + head +
                ", direction=" + direction;
    }

    @Override
    public boolean equals(Object obj) {
        return  obj instanceof Ray other &&
                head.equals(other.head) && direction.equals(other.direction);
    }
}
