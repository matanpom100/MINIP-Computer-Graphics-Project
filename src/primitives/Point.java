package primitives;

import primitives.Util;

/**
 * Class Point is the basic class representing a point in the 3D space.
 * The class is based on Util class.
 * The class is based on Double3 class.
 */
public class Point {

    // Point ZERO is the origin point (0,0,0)
    public static final Point ZERO = new Point(0, 0, 0);
    // Point X is the point (1,0,0)
    protected final Double3 xyz;

    /**
     * Constructor for Point class receiving 3 double values.
     *
     * @param x - the x value of the point
     * @param y - the y value of the point
     * @param z - the z value of the point
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructor for Point class receiving 3 double values.
     *
     * @param xyz - the Double3 value of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Copy constructor for Point class.
     *
     * @param other - the other Point to copy
     * @return a number that represents the distance squared between the two points
     */
    public double distanceSquared(Point other) {
        return (this.xyz.d1 - other.xyz.d1) * (this.xyz.d1 - other.xyz.d1) + (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2) + (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3); // calculate the distance squared by the formula
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if the object is the same object
        return (obj instanceof Point other) // if the object is an instance of Point
                && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "Point {" +
                "xyz=" + xyz +
                '}';
    }


    /**
     * Returns a new Vector that is the subtraction of the two points.
     *
     * @param other - the other Point to subtract
     * @return a new Vector that is the subtraction of the two points
     */
    public Vector subtract(Point other) {
        return new Vector(xyz.subtract(other.xyz)); // Calculate the subtraction of the two points and return a new Vector with the result
    }

    /**
     * Returns a new Point that is the addition of the point and the vector.
     *
     * @param v - the Vector to add
     * @return a new Point that is the addition of the point and the vector
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz)); // Calculate the addition of the point and the vector and return a new Point with the result
    }

    /**
     * Returns the distance between the two points.
     *
     * @param other - the other Point to calculate the distance
     * @return a number that represents the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other)); // Calculate the distance by the formula
    }


    /**
     * Returns the x value of the point.
     *
     * @return the x value of the point
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y value of the point.
     *
     * @return the y value of the point
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Returns the z value of the point.
     *
     * @return the z value of the point
     */
    public double getZ() {
        return xyz.d3;
    }
}
