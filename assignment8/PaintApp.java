package assignment8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;

/**
 * Implementation of PaintApp Class, this class extends the Application class
 * This class is used for create the canvas and the interaction with the application
 *
 * @author Danilo Nakai
 */
public class PaintApp extends Application {
    /** The selected shape to draw **/
    private String selectedShape = "circle";

    /** The list of drew objects **/
    private ArrayList<Drawable> list = new ArrayList<>();

    /**
     * Create root, scene and canvas
     * Create all the controllers to interact with the application
     * @param stage The stage to set the scene and show the elements
     **/
    @Override
    public void start(Stage stage) throws Exception {
        // The basic settings to create the canvas
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(1000, 700);
        stage.setTitle("Paint App");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        stage.show();

        // Section that will group all the components in the top of the application
        VBox group = new VBox();
        group.setBackground(Background.fill(Color.web("#898585")));

        // Title and subtitle
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        Label title = new Label("Assignment 8 - Danilo Nakai");
        title.setFont(new Font(25));
        titleContainer.getChildren().add(title);

        HBox subtitleContainer = new HBox();
        subtitleContainer.setAlignment(Pos.CENTER);
        Label subtitle = new Label("Press Draw or Click to draw!");
        subtitle.setFont(new Font(20));
        subtitleContainer.getChildren().add(subtitle);

        // Controllers section
        HBox controllers = new HBox();
        controllers.setPrefWidth(1000);
        controllers.setPadding(new Insets(10));
        controllers.setSpacing(10);
        controllers.setAlignment(Pos.CENTER);
        controllers.setBackground(Background.fill(Color.web("#ccc")));

        // Circle and Square buttons
        Button circleButton = new Button("Circle");
        Button squareButton = new Button("Square");

        // Location inputs
        Label locationLabel = new Label("Location:");
        TextField locationX = new TextField();
        locationX.setPromptText("0~999");
        locationX.setPrefWidth(50);
        TextField locationY = new TextField();
        locationY.setPromptText("0~554");
        locationY.setPrefWidth(50);

        // Size inputs
        Label sizeLabel = new Label("Size:");
        TextField size = new TextField();
        size.setPromptText("1~30");
        size.setPrefWidth(50);

        // RGB color inputs
        Label colorLabel = new Label("Color:");
        TextField colorR = new TextField();
        colorR.setPromptText("0~255");
        colorR.setPrefWidth(50);
        TextField colorG = new TextField();
        colorG.setPromptText("0~255");
        colorG.setPrefWidth(50);
        TextField colorB = new TextField();
        colorB.setPromptText("0~255");
        colorB.setPrefWidth(50);

        // Draw and Undraw buttons
        Button drawButton = new Button("Draw");
        Button undrawButton = new Button("UnDraw");

        // Error message section
        HBox errorsContainer = new HBox();
        errorsContainer.setAlignment(Pos.CENTER);
        errorsContainer.setPadding(new Insets(5));
        Label errorMessage = new Label("No errors");
        errorMessage.setFont(new Font(15));
        errorsContainer.setBackground(Background.fill(Color.web("#ddd")));
        errorMessage.setTextFill(Color.GREEN);
        errorsContainer.getChildren().add(errorMessage);

        // Grouping all the controllers horizontally
        controllers.getChildren().addAll(
                circleButton,
                squareButton,
                locationLabel,
                locationX,
                locationY,
                sizeLabel,
                size,
                colorLabel,
                colorR,
                colorG,
                colorB,
                drawButton,
                undrawButton
        );

        // Grouping title section, subtitle section, controllers and error message section vertically
        group.getChildren().addAll(titleContainer, subtitleContainer, controllers, errorsContainer);
        // Adding everything to the root
        root.getChildren().add(group);

        // Watching the mouse click event to trigger the action
        canvas.setOnMouseClicked(event -> mouseDraw(event, gc, size.getText(), colorR.getText(), colorG.getText(), colorB.getText(), errorMessage));
        // Watching the mouse drag event to trigger the action
        canvas.setOnMouseDragged(event -> mouseDraw(event, gc, size.getText(), colorR.getText(), colorG.getText(), colorB.getText(), errorMessage));

        // Watching the draw button's click event to trigger the action
        drawButton.setOnAction(event -> {
            buttonDraw(gc, locationX.getText(), locationY.getText(), size.getText(), colorR.getText(), colorG.getText(), colorB.getText(), errorMessage);
        });

        // Watching the undraw button's click event to trigger the action
        undrawButton.setOnAction(event -> {
            undraw(gc, canvas);
        });

        // Watching the circle button's click event to trigger the action
        circleButton.setOnAction(event -> selectedShape = "circle");
        // Watching the square button's click event to trigger the action
        squareButton.setOnAction(event -> selectedShape = "square");
    }

    /**
     * Check if the string has a valid number
     * @param number The number in a string to be checked
     * @return boolean value(True or False)
     **/
    public boolean isValidNumber(String number) {
        boolean valid;

        try {
            Integer.parseInt(number);
            valid = true;
        } catch (NumberFormatException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Check if the RGB color is valid
     * @param rValue The r value of the RGB color
     * @param gValue The g value of the RGB color
     * @param bValue The b value of the RGB color
     * @return boolean value(True or False)
     **/
    public boolean colorIsValid(String rValue, String gValue, String bValue) {
        boolean valid = false;

        if (isValidNumber(rValue) && isValidNumber(gValue) && isValidNumber(bValue)) {
            int r = Integer.parseInt(rValue);
            int g = Integer.parseInt(gValue);
            int b = Integer.parseInt(bValue);

            if ((r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255)) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Check if the size is valid, the size must be greater than 0
     * @param size The value to be checked
     * @return boolean value(True or False)
     **/
    public boolean sizeIsValid(String size) {
        boolean valid = false;

        if (isValidNumber(size) && Integer.parseInt(size) > 0) {
            valid = true;
        }

        return valid;
    }

    /**
     * Check if the location is valid
     * @param xValue The location's x value
     * @param yValue The location's y value
     * @param sizeValue The size of the object to calculate the end of object
     * @return boolean value(True or False)
     **/
    public boolean locationIsValid(String xValue, String yValue, String sizeValue) {
        boolean valid = false;

        if (isValidNumber(xValue) && isValidNumber(yValue) && sizeIsValid(sizeValue)) {
            int x = Integer.parseInt(xValue);
            int y = Integer.parseInt(yValue);
            int size = Integer.parseInt(sizeValue);

            if ((x >= 0 && (x + size) <= 1000) && (y >= 0 && (y + size) <= 555)) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Make the validation and draw the object according to the position of the mouse
     * @param event The mouse event
     * @param gc The GraphicContext
     * @param size The size of the object
     * @param colorR The R value of the RGB color
     * @param colorG The G value of the RGB color
     * @param colorB The B value of the RGB color
     * @param errorMessage The error message to be displayed if the validation fails
     **/
    public void mouseDraw(MouseEvent event, GraphicsContext gc, String size, String colorR, String colorG, String colorB, Label errorMessage) {
        double x = event.getX();
        double y = event.getY();

        if (colorIsValid(colorR, colorG, colorB)) {
            if (sizeIsValid(size)) {
                // If there's no errors, reset the error message
                errorMessage.setTextFill(Color.GREEN);
                errorMessage.setText("No errors");

                // Convert everything to integer
                int intColorR = Integer.parseInt(colorR);
                int intColorG = Integer.parseInt(colorG);
                int intColorB = Integer.parseInt(colorB);
                int intSize = Integer.parseInt(size);

                // Check the selected shape, draw the object and add to the list
                if (selectedShape.equals("circle")) {
                    Circle circle = new Circle(x, y, Color.rgb(intColorR, intColorG, intColorB), intSize);
                    circle.draw(gc);

                    list.add(circle);
                } else if (selectedShape.equals("square")) {
                    Square square = new Square(x, y, Color.rgb(intColorR, intColorG, intColorB), intSize);
                    square.draw(gc);

                    list.add(square);
                }
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Size is invalid!");
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Color is invalid!");
        }
    }

    /**
     * Make the validation and draw the object according to the location provided
     * @param gc The GraphicContext
     * @param size The size of the object
     * @param colorR The R value of the RGB color
     * @param colorG The G value of the RGB color
     * @param colorB The B value of the RGB color
     * @param errorMessage The error message to be displayed if the validation fails
     **/
    public void buttonDraw(GraphicsContext gc, String xValue, String yValue, String size, String colorR, String colorG, String colorB, Label errorMessage) {
        if (colorIsValid(colorR, colorG, colorB)) {
            if (sizeIsValid(size)) {
                if (locationIsValid(xValue, yValue, size)) {
                    // If there's no errors, reset the error message
                    errorMessage.setTextFill(Color.GREEN);
                    errorMessage.setText("No errors");

                    // Convert everything to integer
                    int x = Integer.parseInt(xValue);
                    int y = Integer.parseInt(yValue) + 145;
                    int intColorR = Integer.parseInt(colorR);
                    int intColorG = Integer.parseInt(colorG);
                    int intColorB = Integer.parseInt(colorB);
                    int intSize = Integer.parseInt(size);

                    // Check the selected shape, draw the object and add to the list
                    if (selectedShape.equals("circle")) {
                        Circle circle = new Circle(x, y, Color.rgb(intColorR, intColorG, intColorB), intSize);
                        circle.draw(gc);

                        list.add(circle);
                    } else if (selectedShape.equals("square")) {
                        Square square = new Square(x, y, Color.rgb(intColorR, intColorG, intColorB), intSize);
                        square.draw(gc);

                        list.add(square);
                    }
                }
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Size is invalid!");
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Color is invalid!");
        }
    }

    /**
     * Remove the last object added and reset the canvas
     * @param gc The GraphicContext
     * @param canvas The Canvas
     **/
    public void undraw(GraphicsContext gc, Canvas canvas) {
        // reset the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // remove last item
        list.remove(list.size() - 1);

        // draw everything again
        for (int i = 0; i < list.size(); i++) {
            list.get(i).draw(gc);
        }
    }


    /**
     * The main method to run the application
     *
     * @param args unused
     **/
    public static void main(String[] args) {
        launch(args);
    }
}
