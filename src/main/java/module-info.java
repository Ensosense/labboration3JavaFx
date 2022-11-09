module com.example.labboration3javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.labboration3javafx to javafx.fxml;
    exports se.iths.labboration3javafx;
}