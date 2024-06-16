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
public class Geometries implements Intersectable {

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
    }


    /**
     * Find intersections of a ray with the geometries in the collection
     * @param ray
     * @return List of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;//initialize the list of intersections
        for (Intersectable geometry : geometries) {//for each geometry in the collection, find the intersections with the ray
            List<Point> geometryIntersections = geometry.findIntersections(ray);
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
