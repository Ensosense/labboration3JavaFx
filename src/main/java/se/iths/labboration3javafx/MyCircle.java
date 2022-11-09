package se.iths.labboration3javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.sqrt;


public class MyCircle extends MyShape {

    private double radius;

    public MyCircle(double x, double y, double size, Color color) {
        super(x, y, size*1.5, color);
    }

    @Override
    public void draw(GraphicsContext context) {

        context.setFill(getColor());
        context.fillRoundRect(getX() - getSize() / 2, getY() - getSize() / 2, getSize(), getSize(), getSize(), getSize());

        if (isSelectedField()) {

            context.setStroke(Color.BLUEVIOLET);
            context.strokeRoundRect(getX() - getSize() / 2, getY() - getSize() / 2, getSize(), getSize(), getSize(), getSize());
            System.out.println(this + " " + this.getClass().getSimpleName() + " isSelected");
        }
    }

    @Override
    public String svg() {

        String color = "#" + getColor().toString().substring(2, 10);
        return "<circle cx=\"" + getX() + "\" cy=\"" + getY() + "\" r=\"" + getSize() / 2 + "\" fill=\"" + color + "\" />";
    }

    @Override
    public boolean isSelected(double x2, double y2) {

        double distX = x2 - getX();
        double distY = y2 - getY();

        double distance = sqrt((distX * distX) + (distY * distY));
        return distance <= getSize();
    }
}
