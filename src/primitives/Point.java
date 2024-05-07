package primitives;

import primitives.Util;

public class Point {

    public static final Point ZERO = new Point(0, 0, 0);

    protected final Double3 xyz;

    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public double distanceSquared(Point other) {
        return (this.xyz.d1 - other.xyz.d1) * (this.xyz.d1 - other.xyz.d1) + (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2) + (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "Point {" +
                "xyz=" + xyz +
                '}';
    }


    public Vector subtract(Point other) {
        return new Vector( xyz.subtract(other.xyz) );
    }

    public Point add(Vector v) {
        return new Point( xyz.add(v.xyz) );
    }

    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }








}
