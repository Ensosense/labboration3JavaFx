package se.iths.labboration3javafx;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyShape {


    private final double x;
    private final double y;
    private double size;
    private Color color;

    public MyShape(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw(GraphicsContext context);


    private boolean selectedField;

    public boolean isSelectedField() {
        return selectedField;
    }

    public void setSelectedField(boolean selectedField) {
        this.selectedField = selectedField;
    }
    public abstract String svg();

    public abstract boolean isSelected(double x, double y);

}
