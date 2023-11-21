module iesfranciscodelosrios.timerproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens iesfranciscodelosrios.timerproject to javafx.fxml;
    exports iesfranciscodelosrios.timerproject;
}