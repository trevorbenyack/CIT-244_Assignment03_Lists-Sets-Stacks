import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GUI_01_FunctionalityPane extends Pane {

    public GUI_01_FunctionalityPane() {
        this.getChildren().add(new DataStructure());
    }

    public class DataStructure extends BorderPane {

        Label lbOption = new Label("Please select an option: ");

        // Properties for creating the option toggle group
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton rbHashSet = new RadioButton("Create and work with a HashSet");
        RadioButton rbLinkedHashSet = new RadioButton("Create and work with a LinkedHashSet");
        RadioButton rbTreeSet = new RadioButton("Create and work with a TreeSet");
        RadioButton rbArrayList = new RadioButton("Create and work with an ArrayList");
        RadioButton rbLinkedList = new RadioButton("Create and work with a LinkedList");
        RadioButton rbStack = new RadioButton("Create and work with a Stack");


        Button btExit = new Button("Exit");
        Button btContinue = new Button("Continue");


        public DataStructure() {
            this.setTop(lbOption);
            this.setCenter(getTogglePane());
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
                return togglePane;

        } // end getTogglePane method

    } // end DataStructure class
}
