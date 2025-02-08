# Simple Drawing App in Java

This is a simple drawing application developed as part of a college assignment. The app demonstrates core Java concepts including **ArrayLists**, **exception handling**, **mouse listeners**, and key object-oriented principles such as **Encapsulation**, **Association**, **Inheritance**, and **Polymorphism**.

## Features:

- **Draw Shapes**: The app allows users to draw two types of shapes: **Squares** and **Circles**. These shapes inherit from a common superclass, allowing easy management of their properties and behavior.
  
- **Shape Customization**: Users can configure the shape's **color**, **size**, and **location** before drawing. The app offers two modes for specifying the location:
  - **Manual Input**: The user specifies the position via fields.
  - **Mouse Interaction**: The user can click or drag on the canvas to place the shape.
  
- **Dynamic Drawing**: Once the user presses the Draw button, the shape is rendered on the canvas. Switching between **Squares** and **Circles** dynamically updates the drawing mode.

- **Undo Functionality**: Shapes can be removed from the canvas using the **Undraw** button. Each click of this button removes the most recently drawn shape, and the entire canvas is redrawn to reflect the changes.

- **Error Handling**: Robust error handling ensures that the application remains stable. Possible user errors, such as non-numeric input or out-of-range values for color, are caught and displayed in a user-friendly error label. The label provides:
  - The field that caused the error.
  - The type of error (e.g., out of range, non-numeric).
  
  When no errors are present, the label resets to a friendly "No Errors" message.

## Key Concepts and Techniques:
- **Inheritance & Polymorphism**: Both the Square and Circle classes inherit from a common superclass (e.g., `GeometricObject`), allowing for shared functionality and dynamic shape behavior.
- **ArrayLists**: All drawn shapes are stored in an `ArrayList`, allowing users to undo their actions.
- **Mouse Listeners**: The app handles mouse clicks and drag events to place and draw shapes on the canvas.
- **Exception Handling**: Input validation ensures the app does not crash, with clear error messages for users.

## Getting Started:
1. Clone the repository to your local machine.
2. Open the project in **IntelliJ IDEA** (Community Edition).
3. Make sure to use **Java Version 15** (Liberica JDK 15) or higher.
4. Run the application and start drawing!

## Screenshots:
![image](https://github.com/user-attachments/assets/71971134-eb64-4f26-9bf0-28cbdc13bf5e)


## Requirements:
- **Java Version 15** (Liberica JDK 15) or higher
- **IntelliJ IDEA** (Community Edition)

## License:
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
