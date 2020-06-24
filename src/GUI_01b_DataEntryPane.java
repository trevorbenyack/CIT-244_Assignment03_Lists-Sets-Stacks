import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class GUI_01b_DataEntryPane extends BorderPane {

    // holds mainPane instance so that its elements can be updated
    GUI_00_MainScaffoldPane mainPane;

    // Holds passed-in collection
    Contacts contacts;

    // UI Elements
    Label lbDirection = new Label("Enter your contacts:");
    TextField tfName = new TextField();
    TextField tfPhone = new TextField();
    Label lbSpacer = new Label("");
    Button btAddContact = new Button("Add Contact");
    Button btSeedData = new Button("Use Seed Data");
    Button btBack = new Button("< Back");
    Button btContinue = new Button("Continue");

    public GUI_01b_DataEntryPane() {
    }

    public GUI_01b_DataEntryPane(GUI_00_MainScaffoldPane mainPane, Contacts contacts) {

        // assigns passed in references to instance's variables
        this.mainPane = mainPane;
        this.contacts = contacts;

        // adds elements to the instance's VBox pane
        this.setTop(lbDirection);
        this.setCenter(getContactEntryPane());
        this.setBottom(getNavPane());

        // UI PROPERTIES
        // CSS Selector assignments
        this.getStyleClass().addAll("leftColumn");
        this.setId("dataEntryPane");
        lbDirection.getStyleClass().addAll("padding", "title");

    }

    // creates a Pane that allows users to add the contact
    public VBox getContactEntryPane () {
        VBox contactEntry = new VBox();

        contactEntry.getChildren().add(new Label("Contact Name: "));
        contactEntry.getChildren().add(tfName);
        contactEntry.getChildren().add(new Label("Phone Number:"));
        contactEntry.getChildren().add(tfPhone);
        contactEntry.getChildren().add(lbSpacer);
        contactEntry.getChildren().add(btAddContact);
        contactEntry.getChildren().add(btSeedData);

        // UI PROPERTIES

        // css selector assignments
        contactEntry.getStyleClass().addAll("functionArea", "vbox", "padding");
        // other ui properties
        lbSpacer.setVisible(false);
        btAddContact.prefWidthProperty().bind(tfPhone.widthProperty());
        btSeedData.prefWidthProperty().bind(tfPhone.widthProperty());

        // EVENT HANDLERS

        // this events saves the data from the fields to the contacts Collection, removes the contacts Collection
        // that is currently being displayed in the middle column, and it replaces it with the updated contacts
        // Collection
        btAddContact.setOnAction(event -> addContact());
        tfPhone.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                addContact();
            }
        });

        // seeds sample data for the user
        btSeedData.setOnAction(event -> {
            contacts.seedData();
            mainPane.setCenter(null);
            mainPane.originalData = new GUI_02_OriginalDataPane(mainPane, contacts);
            mainPane.setCenter(mainPane.originalData);
            tfName.requestFocus();
        });

        return contactEntry;
    } // end getContactEntryPane method

    // creates the navigation pane that sits at the bottom of the left-hand column
    private GridPane getNavPane() {
        GridPane navPane = new GridPane();
        navPane.add(btBack, 0, 0);
        navPane.add(btContinue, 1, 0);

        // PROCESS EVENTS
        // Returns the user to the main selection view. Resets the program.
        btBack.setOnAction(e -> {

            // resets the left column to new GUI_01a_ChooseDataStructure, GUI_01b_DataEntry, and GUI_03_Results instances
            mainPane.setLeft(null);
            mainPane.optionPane = new GUI_01a_ChooseDataStructurePane(mainPane);
            mainPane.setLeft(mainPane.optionPane);
            mainPane.setCenter(null);
            mainPane.originalData = new GUI_02_OriginalDataPane();
            mainPane.setCenter(mainPane.originalData);
            mainPane.setRight(null);
            mainPane.resultsPane = new GUI_03_Results();
            mainPane.setRight(mainPane.resultsPane);

        });
        btContinue.setOnAction(e -> {
            mainPane.setLeft(null);
            mainPane.chooseTransformationPane = new GUI_01c_ChooseTransformationPane(mainPane, contacts);
            mainPane.setLeft(mainPane.chooseTransformationPane);
            mainPane.chooseTransformationPane.rbSortByName.requestFocus();

        });

        // UI Properties
        GUI_00_MainScaffoldPane.navPaneProperties(navPane, btContinue);

        return navPane;
    }

    // adds contact to the contacts Collection and displays the updated contact Collection in the middle column
    private void addContact() {
        try {
            contacts.addContact(tfName.getText(), tfPhone.getText());
            clearFields();
            mainPane.setCenter(null);
            mainPane.originalData = new GUI_02_OriginalDataPane(mainPane, contacts);
            mainPane.setCenter(mainPane.originalData);
            tfName.requestFocus();

        } catch (ArrayIndexOutOfBoundsException outOfBounds){
            System.out.println("User tried entering a contact w/o a name, number, or both");
        }
    }
    // clears the tfName and tfPhone fields
    private void clearFields() {
        tfName.clear();
        tfPhone.clear();
    }

}
