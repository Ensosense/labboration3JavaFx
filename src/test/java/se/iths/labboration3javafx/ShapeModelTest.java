package se.iths.labboration3javafx;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {
    ShapeModel model = new ShapeModel();

    @Test
    void checkIfShapeObjectsAddsToTheList() {

        MyShape circle = new MyCircle(10, 20, 100, Color.GOLD);
        MyShape square = new MySquare(50, 50, 150, Color.PINK);

        ShapeModel.myShapes.add(circle);
        ShapeModel.myShapes.add(square);

        assertEquals(2, ShapeModel.myShapes.size());

    }

}