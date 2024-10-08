package geometrics;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;


/**
 * Geometries class represents a collection of geometries in 3D Cartesian coordinate system
 * The class is a container for geometries that implements the Intersectable interface
 *
 * @author Matan and Eitan
 * @see Intersectable
 */
public class Geometries extends Intersectable {

    /**
     * List of geometries in the collection
     */
    final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor
     */
    public Geometries() {
    }

    /**
     * Constructor based on a list of geometries
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
        calculateBoundingBox();
    }

    /**
     * Constructor based on a list of geometries
     *
     * @param geometries
     */
    public Geometries(List<Intersectable> geometries) {
        this.geometries.addAll(geometries);
        calculateBoundingBox();
    }

    /**
     * Add geometries to the collection
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {//for each geometry in the list, add it to the collection
            this.geometries.add(geometry);
        }
        calculateBoundingBox();
    }

    /**
     * Add geometries to the collection
     * @param geometries
     */
    public void add(List<Intersectable> geometries) {
        this.geometries.addAll(geometries);
        calculateBoundingBox();
    }


    @Override
    protected void calculateBoundingBox() {
        //if the collection is empty, the bounding box is null
        if (geometries.isEmpty()) {
            box = null;
            return;
        }

        //initialize the min and max values to the maximum and minimum possible values
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        //for each geometry in the collection, calculate the bounding box
        for (Intersectable geo : geometries) {
            if (geo.box == null) {
                geo.calculateBoundingBox();
            }
            if (geo.box != null) {
                minX = Math.min(minX, geo.box.getMin().getX());
                minY = Math.min(minY, geo.box.getMin().getY());
                minZ = Math.min(minZ, geo.box.getMin().getZ());
                maxX = Math.max(maxX, geo.box.getMax().getX());
                maxY = Math.max(maxY, geo.box.getMax().getY());
                maxZ = Math.max(maxZ, geo.box.getMax().getZ());
            }
        }

        //initialize the bounding box
        box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));

    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (box != null && !box.hasIntersection(ray))//check if the ray intersects the bounding box
            return null;//if not, return null (no intersections

        List<GeoPoint> intersections = null;//initialize the list of intersections
        for (Intersectable geometry : geometries) {//for each geometry in the collection, find the intersections with the ray
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
            if (geometryIntersections != null) {//if there are intersections, add them to the list
                if (intersections == null) {//check if the list is initialized
                    intersections = new LinkedList<>();//if not, initialize it
                }
                intersections.addAll(geometryIntersections);//add the intersections to the list
            }
        }
        return intersections;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (box != null && !box.hasIntersection(ray))//check if the ray intersects the bounding box
            return null;//if not, return null (no intersections

        List<GeoPoint> intersections = null;//initialize the list of intersections
        for (Intersectable geometry : geometries) {//for each geometry in the collection, find the intersections with the ray
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray, maxDistance);
            if (geometryIntersections != null) {//if there are intersections, add them to the list
                if (intersections == null) {//check if the list is initialized
                    intersections = new LinkedList<>();//if not, initialize it
                }
                intersections.addAll(geometryIntersections);//add the intersections to the list
            }
        }
        return intersections;
    }


}
