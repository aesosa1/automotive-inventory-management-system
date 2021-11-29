package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Javadocs located C482 Alberto Sosa submission/C482 Java Docs/
 *
 * FUTURE ENHANCEMENT implementing SQL to handle the data could provide further functionality for this program.
 *
 * @author Alberto Sosa
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("First Screen");
        Scene mainFormScene = new Scene(root,  1200, 600);
        stage.setScene(mainFormScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
