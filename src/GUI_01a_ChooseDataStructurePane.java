import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

import java.util.*;

public class GUI_01a_ChooseDataStructurePane extends BorderPane {

        // holds the program's instance of mainPane so its elements can be changed
        GUI_00_MainScaffoldPane mainPane;

        // holds the current contact Collection so it can be used to display its contacts
        Contacts contacts;

        // UI ELEMENTS
        Label lbOption = new Label("Please select an option: ");
        // Properties for creating the option toggle group
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton rbHashSet = new RadioButton("Create and work with a HashSet");
        RadioButton rbLinkedHashSet = new RadioButton("Create and work with a LinkedHashSet");
        RadioButton rbTreeSet = new RadioButton("Create and work with a TreeSet");
        RadioButton rbArrayList = new RadioButton("Create and work with an ArrayList");
        RadioButton rbLinkedList = new RadioButton("Create and work with a LinkedList");
        RadioButton rbStack = new RadioButton("Create and work with a Stack");
        // Navigation buttons
        Button btExit = new Button("< Exit");
        Button btContinue = new Button("Continue");

        public GUI_01a_ChooseDataStructurePane(GUI_00_MainScaffoldPane mainPane) {

            this.mainPane = mainPane;

            this.setTop(lbOption);
            this.setCenter(getTogglePane());
            this.setBottom(getNavPane());

            // CSS Selector assignments
            this.setId("chooseDataStructurePane");
            this.getStyleClass().add("leftColumn");

            // UI Properties
            lbOption.getStyleClass().addAll("padding", "title");



        } // end DataStructure constructor

        public VBox getTogglePane() {
                VBox togglePane = new VBox();
                rbHashSet.setToggleGroup(toggleGroup);
                rbLinkedHashSet.setToggleGroup(toggleGroup);
                rbTreeSet.setToggleGroup(toggleGroup);
                rbArrayList.setToggleGroup(toggleGroup);
                rbLinkedList.setToggleGroup(toggleGroup);
                rbStack.setToggleGroup(toggleGroup);
                togglePane.getChildren().addAll(rbHashSet, rbLinkedHashSet, rbTreeSet, rbArrayList, rbLinkedList, rbStack);

                // UI Properties
                togglePane.getStyleClass().addAll("functionArea", "vbox", "padding");
                rbHashSet.setSelected(true);

                return togglePane;

        } // end getTogglePane method

        public GridPane getNavPane() {
            GridPane navPane = new GridPane();
            navPane.add(btExit, 0, 0);
            navPane.add(btContinue, 1, 0);

            // Process actions
            btExit.setOnAction(event -> System.exit(0));
            btContinue.setOnAction(event -> {
                createContactsCollection();
                contacts.sortContactsByName();
                mainPane.dataEntryPane = new GUI_01b_DataEntryPane(mainPane, contacts);
                mainPane.getChildren().remove(mainPane.optionPane);
                mainPane.add(mainPane.dataEntryPane, 0, 0, 1, 2);
            });

            // UI Properties
            GUI_00_MainScaffoldPane.navPaneProperties(navPane, btContinue);

            return navPane;
        }

        public void createContactsCollection () {
            if (rbHashSet.isSelected()) {
                contacts = new Contacts(new HashSet<>());
            } else if (rbLinkedHashSet.isSelected()){
                contacts = new Contacts(new LinkedHashSet<>());
            } else if (rbTreeSet.isSelected()) {
                contacts = new Contacts(new TreeSet<>());
            } else if (rbArrayList.isSelected()) {
                contacts = new Contacts(new ArrayList<>());
            } else if (rbLinkedList.isSelected()) {
                contacts = new Contacts(new LinkedList<>());
            } else if (rbStack.isSelected()) {
                contacts = new Contacts(new Stack<>());
            }

            contacts.seedData();
        }



} //
