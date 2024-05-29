package primitives;


/**
 * Class Vector is the basic class representing a vector in the 3D space.
 * The class is based on
 */
public class Vector extends Point {

    /**
     * Constructor for Vector class
     *
     * @param x coordinate of the head of the vector
     * @param y coordinate of the head of the vector
     * @param z coordinate of the head of the vector
     * @throws IllegalArgumentException if the head of the vector is Point(0,0,0)
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
    }

    /**
     * Constructor for Vector class
     *
     * @param xyz coordinates of the head of the vector
     * @throws IllegalArgumentException if the head of the vector is Point(0,0,0)
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (Util.isZero(xyz.d1) && Util.isZero(xyz.d2) && Util.isZero(xyz.d3)) {
            //we cannot use the Util.isZero method here because it is not static
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }

    }

    /**
     * Adding two vectors
     *
     * @param v the vector to add
     * @return the sum of the two vectors
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));  //returning a new vector by adding the tow points of the vectors
    }

    /**
     * Multiplying a vector by a scalar
     *
     * @param scalar
     * @return the scaled vector
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * Scalar multiplication of a vector by another vector
     *
     * @param v1
     * @return the scalar product of the two vectors
     */
    public double dotProduct(Vector v1) {
        return xyz.d1 * v1.xyz.d1 + xyz.d2 * v1.xyz.d2 + xyz.d3 * v1.xyz.d3; //returning the scalar product of the two vectors
    }

    /**
     * Vector multiplication of a vector by another vector
     *
     * @param v1
     * @return the vector product of the two vectors
     */
    public Vector crossProduct(Vector v1) {
        return new Vector( // calculating the vector product of the two vectors by algebraic formula
                xyz.d2 * v1.xyz.d3 - xyz.d3 * v1.xyz.d2,
                xyz.d3 * v1.xyz.d1 - xyz.d1 * v1.xyz.d3,
                xyz.d1 * v1.xyz.d2 - xyz.d2 * v1.xyz.d1);
    }

    /**
     * Squared length of a vector
     *
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }


    /**
     * Length of a vector
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizing a vector
     *
     * @return the normalized vector
     * @throws ArithmeticException if the vector is the zero vector
     */
    public Vector normalize() {
        return scale(1 / length()); // returning the normalized vector by scaling the vector by 1/length
    }

    @Override
    public String toString() {
        return super.toString() + "\n Vector:" +
                "\n xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Vector other;
    }
}
