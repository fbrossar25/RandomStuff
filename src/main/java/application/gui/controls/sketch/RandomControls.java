package application.gui.controls.sketch;

import application.gui.controls.SketchControls;
import application.sketch.random.RandomCount;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class RandomControls extends SketchControls {
    private Button resetButton;

    public RandomControls(RandomCount sketch) {
        super(sketch);
    }

    @Override
    protected void resetControls() {

    }

    @Override
    protected void initControls() {
        resetButton = new Button("Reset");
        resetButton.setOnAction((evt) -> {
            sketch.reset();
        });
        GridPane grid = new GridPane();
        grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        grid.setAlignment(Pos.CENTER);
        grid.addRow(0, resetButton);
        rootPane.setCenter(grid);
    }

}
