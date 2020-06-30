import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/*
 * Trevor Benyack
 * CIT-244
 * Assignment 03 - Contacts Data Structure Application
 * Due 2020-06-15
 */

public class GUI_00_MainScaffoldPane extends BorderPane {
    GUI_01a_ChooseDataStructurePane optionPane = new GUI_01a_ChooseDataStructurePane(this);
    GUI_01b_DataEntryPane dataEntryPane;
    GUI_01c_ChooseTransformationPane chooseTransformationPane;
    GUI_02_OriginalDataPane originalData = new GUI_02_OriginalDataPane();
    GUI_03_Results resultsPane = new GUI_03_Results();

    public GUI_00_MainScaffoldPane() {
        this.setId("mainPane");
        this.setLeft(optionPane);
        this.setCenter(originalData);
        this.setRight(resultsPane);

    }

    // static method that sets UI properties for the navBars used in the program
    public static void navPaneProperties (GridPane navPane, Button btContinue) {
        GridPane.setHalignment(btContinue, HPos.RIGHT);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        navPane.getColumnConstraints().addAll(column1, column2);
        navPane.getStyleClass().add("padding");
    }

    // maintains row-height parity b/w the original contacts Collection display and the results display
    public static void setRowHeight(GridPane contactGridList) {
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setMinHeight(30);
        contactGridList.getRowConstraints().add(rowConstraint);
    }

}
