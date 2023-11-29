module iitu.tsay.quizziz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens iitu.tsay.quizziz to javafx.fxml;
    exports iitu.tsay.quizziz;
}