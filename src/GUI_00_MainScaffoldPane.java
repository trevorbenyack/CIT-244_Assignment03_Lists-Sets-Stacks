import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class GUI_00_MainScaffoldPane extends BorderPane {
    GUI_01_FunctionalityPane optionPane = new GUI_01_FunctionalityPane();
    GUI_02_OriginalData dataEntryPane = new GUI_02_OriginalData();
    GUI_03_Results listTransformPane = new GUI_03_Results();

    public GUI_00_MainScaffoldPane() {
        this.setLeft(optionPane);
        this.setCenter(dataEntryPane);
        this.setRight(listTransformPane);
    }
}
