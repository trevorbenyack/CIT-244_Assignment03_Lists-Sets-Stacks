import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.util.*;

public class GUI_02_OriginalDataPane extends GridPane {

    // holds the current instance of Contacts
    Contacts contacts;

    // holds the program's instance of GUI_00_MainScaffoldPane so that it can be edited
    GUI_00_MainScaffoldPane mainPane;


    public GUI_02_OriginalDataPane() {
    }

    public GUI_02_OriginalDataPane(GUI_00_MainScaffoldPane mainPane, Contacts contacts) {

        this.mainPane = mainPane;
        this.contacts = contacts;



        if (contacts.contactsCollection instanceof Stack) {
            populateContactsGrid((Stack<String>)contacts.contactsCollection);
        } else if (contacts.contactsCollection instanceof LinkedList) {
            populateContactsGrid((LinkedList<String>)contacts.contactsCollection);
        } else {
            populateContactsGrid(contacts.contactsCollection);
        }

        // UI PROPERTIES

        // css selector assignments
        // CSS selector assignment
        this.setId("displayOriginalDataPane");
        this.getStyleClass().addAll("middleColumn", "grid", "padding");

        // other ui properties

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(40);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        this.getColumnConstraints().addAll(column1, column2, column3);

    }

    // The following methods fill the instance GridPane with all of the contacts in the current Collection.
    // These methods demonstrate to the user the order that each type of data structure stores elements within itself.

    public void populateContactsGrid(Collection<String> contactsCollection) {

        int row = 0;

        Iterator<String> iterator = contactsCollection.iterator();

        while (iterator.hasNext()) {
            addContactToGrid(iterator.next(), row);
            row++;
        }

    }

    // fills the GridPane with all of the contacts in the current Collection if the contacts Collection
    // is of subtype Stack<String>
    public void populateContactsGrid(LinkedList<String> contactsLinkedList) {

        // creates temporary clone of the contactsLinkedList so that original is not destroyed when retrieving elements
        LinkedList<String> temp = (LinkedList<String>)contactsLinkedList.clone();

        int row = 0;
        while (temp.isEmpty() == false) {
            addContactToGrid(temp.remove(), row);
            row++;
        }

    }


    // fills the GridPane with all of the contacts in the current Collection if the contacts Collection
    // is of subtype Stack<String>
    public void populateContactsGrid(Stack<String> contactsStack) {

        // creates temporary clone of the contactsStack so that original is not destroyed when retrieving elements
        Stack<String> temp = (Stack<String>)contactsStack.clone();

        int row = 0;
        while (temp.isEmpty() == false) {
            addContactToGrid(temp.pop(), row);
            row++;
        }

    }

    // helper function that receives an individual contact string, parses it into it's name and phone number elements
    // and then adds it to the instance GridPane
    private void addContactToGrid(String tempString, int row) {
        String[] contactString = tempString.split("__");
        this.add(new Text(contactString[0]), 0, row);
        this.add(new Text(contactString[1]), 1, row);
        this.add(getDeleteButton(contactString[0]), 2, row);

        // sets the rows' height
        GUI_00_MainScaffoldPane.setRowHeight(this);

    }

    // creates a delete button for each individual contact
    private Button getDeleteButton(String contactString) {
        Button btDelete = new Button("-");

        btDelete.setOnAction(e -> {
            contacts.removeContact(contactString);
            mainPane.getChildren().remove(mainPane.originalData);
            mainPane.originalData = new GUI_02_OriginalDataPane(mainPane, contacts);
            mainPane.add(mainPane.originalData, 1, 1);
        });
        return btDelete;
    }


}
