import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

public class GUI_01c_ChooseTransformationPane extends BorderPane {

    // holds the programs's instance of GUI_00_MainScaffoldPane so that it can be changed
    GUI_00_MainScaffoldPane mainPane;

    // Holds passed-in collection
    Contacts contacts;

    // UI Elements
    Label lbChooseTransformation = new Label("Choose a transformation:");
    ToggleGroup toggleGroup = new ToggleGroup();
    RadioButton rbSortByName = new RadioButton("Sort By Name");
    RadioButton rbReverse = new RadioButton("Reverse Order");
    RadioButton rbGetPhoneNumber = new RadioButton("Get a Contact's Phone Number");
    TextField tfContactSearch = new TextField();
    Button btBack = new Button("< Back");
    Button btContinue = new Button("Continue");

    public GUI_01c_ChooseTransformationPane() {
    }

    public GUI_01c_ChooseTransformationPane(GUI_00_MainScaffoldPane mainPane, Contacts contacts) {
        this.mainPane = mainPane;
        this.contacts = contacts;

        // adds elements to the instance VBox Pane
        this.setTop(lbChooseTransformation);
        this.setCenter(getTogglePane());
        this.setBottom(getNavPane());

        // CSS Selector assignments
        this.getStyleClass().add("leftColumn");
        this.setId("chooseTransformationPane");
        lbChooseTransformation.getStyleClass().add("padding");
    }

    // returns the pane from which the user chooses which type of transformation they would like to do on the
    // contacts Collection
    private VBox getTogglePane() {
        VBox togglePane = new VBox();

        rbSortByName.setToggleGroup(toggleGroup);
        rbReverse.setToggleGroup(toggleGroup);
        rbGetPhoneNumber.setToggleGroup(toggleGroup);

        tfContactSearch.setPromptText("Contact Name");
        togglePane.getChildren().addAll(rbSortByName, rbReverse, rbGetPhoneNumber, tfContactSearch);


        // UI PROPERTIES

        // css selector assignments
        togglePane.getStyleClass().addAll("functionArea", "vbox", "padding");
        lbChooseTransformation.getStyleClass().addAll("title");
        // other ui properties
        rbSortByName.setSelected(true);
        // hides contact search box unless that option is chosen
        tfContactSearch.setVisible(false);
        rbSortByName.setOnAction(e -> tfContactSearch.setVisible(false));
        rbReverse.setOnAction(e -> tfContactSearch.setVisible(false));
        rbGetPhoneNumber.setOnAction(e -> tfContactSearch.setVisible(true));


        return togglePane;
    }

    // creates the navigation pane that sits at the bottom of the left-hand column
    private GridPane getNavPane() {
        GridPane navPane = new GridPane();
        navPane.add(btBack, 0, 0);
        navPane.add(btContinue, 1, 0);

        // PROCESS EVENTS
        // sends the user back to the dataEntryPane. Retains the current contactsCollection, as well as any data
        // that was previously entered in the fields, but not saved to the collection.
        btBack.setOnAction(e -> {

            mainPane.setLeft(null);
            mainPane.setLeft(mainPane.dataEntryPane);
            mainPane.setRight(null);
            mainPane.resultsPane = new GUI_03_Results();
            mainPane.setRight(mainPane.resultsPane);
            mainPane.dataEntryPane.tfName.requestFocus();

        });
        // calls the performTransformation function to perform the transformation the user would like to see
        btContinue.setOnAction(e -> performTransformation());
        tfContactSearch.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                performTransformation();
            }
        });

        // UI Properties
        GUI_00_MainScaffoldPane.navPaneProperties(navPane, btContinue);

        return navPane;
    }

    // executes the transformation on the contact list and then calls displayResults to add the transformed list
    // to the resultsPane
    private void performTransformation() {

        if (rbSortByName.isSelected()) {
            displayResults(contacts.sortContactsByName());
        } else if (rbReverse.isSelected()) {
            displayResults(contacts.reverseOrder());
        } else if (rbGetPhoneNumber.isSelected()) {
            if (tfContactSearch.getText().isEmpty()) {
                // do nothing
            } else {
                displayResults(contacts.getPhoneNumber(tfContactSearch.getText()));
            }
        }
    }

    // helper function for displaying the results to the resultsPane
    private void displayResults(ArrayList<String> transformedContacts) {
        mainPane.setRight(null);
        mainPane.resultsPane = new GUI_03_Results(mainPane, transformedContacts);
        mainPane.setRight(mainPane.resultsPane);
    }




}
