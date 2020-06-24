import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GUI_03_Results extends BorderPane {

    // holds the programs's instance of GUI_00_MainScaffoldPane so that it can be changed
    GUI_00_MainScaffoldPane mainPane;

    // holds the current instance of Contacts
    Contacts contacts;

    // holds the transformed contacts Collection
    ArrayList<String> contactsTransformed;

    // UI Elements
    Label lbListTransform = new Label("Transformed Contact Data:");

    // displays the contactsList
    GridPane contactsListGridPane = new GridPane();

    public GUI_03_Results() {
        this.setTop(lbListTransform);
        lbListTransform.getStyleClass().addAll("rightColumn", "padding", "title");
    }

    public GUI_03_Results(GUI_00_MainScaffoldPane mainPane, ArrayList<String> contactsTransformed) {

        // assigns passed-in instances for use w/in the class
        this.mainPane = mainPane;
        this.contactsTransformed = contactsTransformed;

        // adds elements to the this BorderBox instance
        this.setTop(lbListTransform);
        this.setCenter(contactsListGridPane);

        // UI PROPERTIES

        // css selector assignments
        contactsListGridPane.getStyleClass().addAll("grid", "padding");
        this.getStyleClass().addAll("middleColumn");
        lbListTransform.getStyleClass().addAll("padding", "title");

        // UI Properties
        contactsListGridPane.getStyleClass().addAll("grid", "padding");
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(40);
        contactsListGridPane.getColumnConstraints().addAll(column1, column2);

        populateContactsGrid();
    }

    // The following methods fill the instance GridPane with all of the contacts in the current Collection.
    // These methods demonstrate to the user the order that each type of data structure stores elements within itself.

    public void populateContactsGrid() {

        int row = 0;

        Iterator<String> iterator = contactsTransformed.iterator();

        while (iterator.hasNext()) {
            addContactToGrid(iterator.next(), row);
            row++;
        }

    }

    // helper function that receives an individual contact string, parses it into it's name and phone number elements
    // and then adds it to the instance GridPane
    private void addContactToGrid(String tempString, int row) {
        String[] contactString = tempString.split("__");
        contactsListGridPane.add(new Text(contactString[0]), 0, row);
        contactsListGridPane.add(new Text(contactString[1]), 1, row);

        // sets the rows' height
        GUI_00_MainScaffoldPane.setRowHeight(contactsListGridPane);
    }

}
