package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class APK extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws  Exception {
        Parent root = FXMLLoader.load(getClass().getResource("apk.fxml"));
        Scene scena = new Scene(root);
        primaryStage.setScene(scena);
        primaryStage.setOnCloseRequest(e-> {
            primaryStage.close();
            Controller.closeCamera();
        });
        primaryStage.setTitle("Video Stream /// Java Projekt 2");
        primaryStage.show();

    }
}
