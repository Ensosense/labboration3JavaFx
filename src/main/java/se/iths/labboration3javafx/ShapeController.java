package se.iths.labboration3javafx;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ShapeController {

    public ColorPicker colorpicker;
    public Canvas canvas;
    public Button onSave;
    public Button onDeleteAll;
    public ChoiceBox<ShapeType> choiceBox;
    public TextField textField;
    public GraphicsContext context;
    public Button undo;
    public Stage stage;

    ShapeModel model = new ShapeModel();

    public void initialize() {

        textField.textProperty().bindBidirectional(model.size);
        colorpicker.valueProperty().bindBidirectional(model.colorProperty());
        choiceBox.valueProperty().bindBidirectional(model.shapeProperty());
        choiceBox.setItems(model.shapeTypesList);
        context = canvas.getGraphicsContext2D();

    }


    public void onCanvasClicked(MouseEvent mouseEvent) {

        if (!mouseEvent.isControlDown()) {
            model.createAndAddNewShape(mouseEvent, this);

        } else {

            model.myShapes.stream()
                    .filter(s -> s.isSelected(mouseEvent.getX(), mouseEvent.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(s -> {
                        model.changeSelectedShape(s, this);
                        model.storeSetSelectedField(s, this);
                    });
        }
        model.reDraw(this);

    }


    public void onSave() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));
        fileChooser.setInitialFileName("MyCanvas");
        File filepath = fileChooser.showSaveDialog(stage);

        if (filepath != null) {
            java.nio.file.Path path = java.nio.file.Path.of(filepath.toURI());
            model.saveToFile(path, this);
        }
    }

    public void clearAllShapes() {

        model.myShapes.clear();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println(model.myShapes);
    }

    public void undoLastShape(MouseEvent event) {

        model.myShapes.remove(model.myShapes.size() - 1);
        model.reDraw(this);
    }

}