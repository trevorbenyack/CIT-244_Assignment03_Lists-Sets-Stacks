import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GUI_03_Results extends GridPane {

    // holds the programs's instance of GUI_00_MainScaffoldPane so that it can be changed
    GUI_00_MainScaffoldPane mainPane;

    // holds the current instance of Contacts
    Contacts contacts;

    // holds the transformed contacts Collection
    ArrayList<String> contactsTransformed;

    public GUI_03_Results() {
    }

    public GUI_03_Results(GUI_00_MainScaffoldPane mainPane, ArrayList<String> contactsTransformed) {
        this.mainPane = mainPane;
        this.contactsTransformed = contactsTransformed;

        // UI Properties
        this.getStyleClass().addAll("grid", "padding");
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(40);
        this.getColumnConstraints().addAll(column1, column2);

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
        this.add(new Text(contactString[0]), 0, row);
        this.add(new Text(contactString[1]), 1, row);

        // sets the rows' height
        GUI_00_MainScaffoldPane.setRowHeight(this);
    }

}
