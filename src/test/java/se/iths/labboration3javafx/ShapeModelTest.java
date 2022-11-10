package se.iths.labboration3javafx;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ShapeModelTest {

    ShapeModel model = new ShapeModel();

    @Test
    void checkIfSetColorMethodWorksProperly() {

        MyShape square = model.createShape(ShapeType.SQUARE, 50, 50);
        square.setColor(Color.PALEGREEN);
        assertEquals(Color.PALEGREEN, square.getColor());
    }

    @Test
    void checkIfTheRequiredShapeCreates() {

       var circle = model.createShape(ShapeType.CIRCLE, 50, 50);
        assertEquals(50, circle.getX());
        assertEquals(50, circle.getY());
        assertEquals(MyCircle.class, circle.getClass());
    }

}