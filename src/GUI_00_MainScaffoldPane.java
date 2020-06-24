import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GUI_00_MainScaffoldPane extends GridPane {
    GUI_01a_ChooseDataStructurePane optionPane = new GUI_01a_ChooseDataStructurePane(this);
    GUI_01b_DataEntryPane dataEntryPane;
    GUI_01c_ChooseTransformationPane chooseTransformationPane;
    GUI_02_OriginalDataPane originalData = new GUI_02_OriginalDataPane();
    GUI_03_Results resultsPane = new GUI_03_Results();


    // UI Elements
    Label lbOrigContData = new Label("Original Contact Data:");
    Label lbListTransform = new Label("Transformed Contact Data:");

    public GUI_00_MainScaffoldPane() {
        this.setId("mainPane");
        this.add(optionPane, 0, 0, 1, 2);
        this.add(lbOrigContData, 1, 0);
        this.add(originalData, 1, 1);
        this.add(lbListTransform, 2, 0);
        this.add(resultsPane, 2, 1);

        // UI
        // css selector assignments
        lbOrigContData.getStyleClass().addAll("middleColumn", "padding", "title");
        lbListTransform.getStyleClass().addAll("rightColumn", "padding", "title");
        // ui properties
        GridPane.setValignment(optionPane, VPos.TOP);
        GridPane.setValignment(lbOrigContData, VPos.TOP);
        GridPane.setValignment(originalData, VPos.TOP);
        GridPane.setValignment(lbListTransform, VPos.TOP);
        GridPane.setValignment(resultsPane, VPos.TOP);

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
