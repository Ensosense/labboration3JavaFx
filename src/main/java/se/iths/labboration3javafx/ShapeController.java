package se.iths.labboration3javafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ObservableList<ShapeType> shapeTypesList = FXCollections.observableArrayList(ShapeType.values());
    ObservableList<MyShape> myShapes = FXCollections.observableArrayList();
    ObservableList<MyShape> selectedShape = FXCollections.observableArrayList();

    public void initialize() {

        textField.textProperty().bindBidirectional(model.size);
        colorpicker.valueProperty().bindBidirectional(model.colorProperty());
        choiceBox.valueProperty().bindBidirectional(model.shapeProperty());
        choiceBox.setItems(shapeTypesList);
        context = canvas.getGraphicsContext2D();

    }


    public void onCanvasClicked(MouseEvent mouseEvent) {

        if (!mouseEvent.isControlDown()) {
            model.createAndAddNewShape(mouseEvent, this);

        } else {
            for (MyShape s : myShapes) {

                if (s.isSelected(mouseEvent.getX(), mouseEvent.getY())) {
                    model.changeSelectedShape(s, this);
                    model.storeSetSelectedField(s, this);
                }
            }
        }
        model.reDraw(this);

    }


    public void onSave() {

        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));
            fileChooser.setInitialFileName("MyMasterpiece");
            File filepath = fileChooser.showSaveDialog(stage);

            if (filepath != null) {
                java.nio.file.Path path = java.nio.file.Path.of(filepath.toURI());
                model.saveToFile(path, this);
            }

        }
    }

    public void clearAllShapes() {

        myShapes.clear();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println(myShapes);
    }

    public void undoLastShape(MouseEvent event) {

        myShapes.remove(myShapes.size() - 1);
        model.reDraw(this);
    }

}