package primitives;



public class Vector extends Point {


    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (Util.isZero(xyz.d1) && Util.isZero(xyz.d2) && Util.isZero(xyz.d3)) {
            //we cannot use the Util.isZero method here because it is not static
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
    }

    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }


    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

   public double dotProduct(Vector v1) {
        return xyz.d1 * v1.xyz.d1 + xyz.d2 * v1.xyz.d2 + xyz.d3 * v1.xyz.d3;
    }

    public Vector crossProduct(Vector v1) {
        return new Vector(
                xyz.d2 * v1.xyz.d3 - xyz.d3 * v1.xyz.d2,
                xyz.d3 * v1.xyz.d1 - xyz.d1 * v1.xyz.d3,
                xyz.d1 * v1.xyz.d2 - xyz.d2 * v1.xyz.d1);
    }

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }


    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double length = length();
        if (Util.isZero(length)) {
            throw new ArithmeticException("Cannot normalize Vector(0,0,0)");
        }
        return scale(1 / length);
    }

    @Override
    public String toString() {
        return super.toString()+"\n Vector:" +
                "\n xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Vector other;
    }
}
