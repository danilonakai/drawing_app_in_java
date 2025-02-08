package assignment8;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of Circle class
 * This class extends GeometricObject class, it's used to create a specific geometric object
 *
 * @author Danilo Nakai
 */
public class Circle extends GeometricObject implements Drawable {
    /** Radius of the circle object **/
    private double radius;

    /**
     * Constructor that set the values
     * @param x The x location of the object
     * @param y The y location of the object
     * @param color The color of the object
     * @param radius The radius of the object
     **/
    public Circle(double x, double y, Color color, double radius) {
        super(x, y, color);
        this.radius = radius;
    }

    /**
     * Draw the object
     * @param gc The GraphicContext
     **/
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getFillColor());
        gc.fillOval(super.getX(), super.getY(), radius, radius);
    }
}
