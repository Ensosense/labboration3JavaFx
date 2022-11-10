package se.iths.labboration3javafx;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {

    MyShape circle = new MyCircle(10, 20, 100, Color.GOLD);
    MyShape square = new MySquare(50, 50, 150, Color.PINK);

    @Test
    void checkIfShapeObjectsAddsToTheList() {

        ShapeModel.myShapes.clear();

        ShapeModel.myShapes.add(circle);
        ShapeModel.myShapes.add(square);
        assertEquals(2, ShapeModel.myShapes.size());
    }

    @Test
    void checkIfTheRequiredShapeCreates() {

        ShapeModel.myShapes.clear();

        ShapeModel.myShapes.add(square);
        assertEquals(square, ShapeModel.myShapes.get(0));
    }

}