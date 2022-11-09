package se.iths.labboration3javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ShapeModel {

    ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<String> size = new SimpleObjectProperty<>("100");
    ObjectProperty<ShapeType> shape = new SimpleObjectProperty<>(ShapeType.CIRCLE);


    public String getSize() {
        return size.get();
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }


    public ObjectProperty<String> sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public ShapeType getShape() {
        return shape.get();
    }

    public ObjectProperty<ShapeType> shapeProperty() {
        return shape;
    }

    public void setShape(ShapeType shape) {
        this.shape.set(shape);
    }

    //Factory pattern
    public MyShape createShape(ShapeType type, double x, double y) {
        return switch (type) {
            case CIRCLE -> new MyCircle(x, y, Double.parseDouble(size.get()), color.get());
            case SQUARE -> new MySquare(x, y, Double.parseDouble(size.get()), color.get());
        };
    }


    public void saveToFile(Path path, ShapeController shapeController) {

        StringBuilder outPut = new StringBuilder();

        outPut.append("<svg version=\"1.1\"\n" +
                "     width=\"857.0\" height=\"576.0\"\n" +
                "     xmlns=\"http://www.w3.org/2000/svg\">");

        for(MyShape shape1: shapeController.myShapes) {
            outPut.append(shape1.svg());
            outPut.append("\n");
        }

        outPut.append("\n" + "</svg>" );

        try{
            Files.writeString(path, outPut.toString());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    void changeSelectedShape(MyShape s, ShapeController shapeController) {
        shapeController.selectedShape.add(s);
        s.setColor(getColor());
        s.setSize(Double.parseDouble(getSize()));
        System.out.println("Im drawing on selected");
    }

    void createAndAddNewShape(MouseEvent mouseEvent, ShapeController shapeController) {
        MyShape myShape = createShape(shapeController.choiceBox.getValue(), mouseEvent.getX(), mouseEvent.getY());
        shapeController.myShapes.add(myShape);
        System.out.println("Im drawing");
        System.out.println(myShape.svg());
    }

    void storeSetSelectedField(MyShape s, ShapeController shapeController) {
        for (MyShape d: shapeController.myShapes) {
            d.setSelectedField(false);
        }
        s.setSelectedField(true);
    }

    void reDraw(ShapeController shapeController) {
        shapeController.context.clearRect(0, 0, shapeController.canvas.getWidth(), shapeController.canvas.getHeight());
        for (MyShape s : shapeController.myShapes) {
            s.draw(shapeController.context);
        }
    }
}

