package assignment8;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of Square class
 * This class extends GeometricObject class, it's used to create a specific geometric object
 *
 * @author Danilo Nakai
 */
public class Square extends GeometricObject implements Drawable {
    /** Size of the object **/
    private double size;

    /**
     * Constructor that set the values
     * @param x The x location of the object
     * @param y The y location of the object
     * @param color The color of the object
     * @param size The size of the object
     **/
    public Square(double x, double y, Color color, double size) {
        super(x, y, color);
        this.size = size;
    }

    /**
     * Draw the object
     * @param gc The GraphicContext
     **/
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getFillColor());
        gc.fillRect(super.getX(), super.getY(), size, size);
    }
}
