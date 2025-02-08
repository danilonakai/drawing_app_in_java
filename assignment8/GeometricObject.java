package assignment8;

import javafx.scene.paint.Color;

/**
 * Implementation of GeometricObject class
 * This class is the parent class of Circle and Square classes, it's used to set the essential
 * information of a geometric object
 *
 * @author Danilo Nakai
 */
public class GeometricObject {
    /** The x location of the object **/
    private double x;

    /** The y location of the object **/
    private double y;

    /** The color of the object **/
    private Color fillColor;

    /**
     * Constructor that set the values
     * @param x The x location of the object
     * @param y The y location of the object
     * @param color The color of the object
     **/
    public GeometricObject(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.fillColor = color;
    }

    /**
     * Get the x location
     * @return The x location
     **/
    public double getX() {
        return x;
    }

    /**
     * Get the y location
     * @return The y location
     **/
    public double getY() {
        return y;
    }

    /**
     * Get the color to fill the object
     * @return The color
     **/
    public Color getFillColor() {
        return fillColor;
    }
}
