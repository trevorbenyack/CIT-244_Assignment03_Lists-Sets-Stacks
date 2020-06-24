import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUI_00_MainScaffoldPane mainPane = new GUI_00_MainScaffoldPane();
        Scene scene = new Scene(mainPane);

        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setTitle("Contacts List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
