import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUI_00_MainScaffoldPane mainPane = new GUI_00_MainScaffoldPane();

        Scene scene = new Scene(mainPane, 900, 800);
        primaryStage.setTitle("Contacts List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        //Stack<String> contacts = new Stack<>();
        ArrayList<String> contacts = new ArrayList<>();

        ContactsCollection contactsCollection = new ContactsCollection(contacts);


        contactsCollection.addContact("Jim", "123-345-3422");
        contactsCollection.addContact("Joan", "098-765-4321");
        contactsCollection.addContact("Jerry", "111-111-1111");
        contactsCollection.addContact("John", "222-222-2222");
        contactsCollection.addContact("Jenna", "333-333-3333");

        contactsCollection.removeContact("Jim");
        contactsCollection.sortContactsByName();
        System.out.println("getPhoneNum test: " + contactsCollection.getPhoneNumber("John"));
        contactsCollection.reverseOrder();

    }

}
