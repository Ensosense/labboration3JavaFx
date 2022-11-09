package se.iths.labboration3javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.iths.labboration3javafx.ShapeController;

import java.io.IOException;

public class ShapeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShapeApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        ShapeController controller = fxmlLoader.getController();
        controller.stage = stage;


        stage.setTitle("My paint app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}