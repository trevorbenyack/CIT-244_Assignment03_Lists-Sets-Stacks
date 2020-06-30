import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Trevor Benyack
 * CIT-244
 * Assignment 03 - Contacts Data Structure Application
 * Due 2020-06-15
 */

// TO DO: Add explainer section below each data structure option explaining how it stores data

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
