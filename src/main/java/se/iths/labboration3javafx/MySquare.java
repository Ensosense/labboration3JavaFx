package se.iths.labboration3javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MySquare extends MyShape {


    public MySquare(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getX(), getY(), getSize(), getSize());

        if (isSelectedField()) {
            context.setStroke(Color.BLUEVIOLET);
            context.strokeRect(getX(), getY(), getSize(), getSize());
        }
    }


    public String svg() {

        String color = "#" + getColor().toString().substring(2, 10);
        return "<rect x=\"" + getX() + "\" y=\"" + getY() + "\" width=\"" + getSize() + "\" height=\"" + getSize() + "\" fill=\"" + color + "\" />";


    }

    @Override
    public boolean isSelected(double x1, double y1) {
        return x1 >= getX() &&
                x1 <= getX() + getSize() &&
                y1 >= getY() &&
                y1 <= getY() + getSize();

    }

}
