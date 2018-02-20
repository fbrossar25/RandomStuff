package application.gui.controls;

import application.gui.GUIController;
import application.sketch.Sketch;
import application.sketch.Sketches;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ControlsPane extends GridPane {
    private GUIController      controller;
    private Button             resetButton;
    private Label              changeSketchLabel;
    private ComboBox<Sketches> sketchChooser;

    public ControlsPane(GUIController controller) {
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.CENTER);
        this.controller = controller;

        changeSketchLabel = new Label("Change sketch : ");

        sketchChooser = new ComboBox<Sketches>();
        sketchChooser.getItems().setAll(Sketches.values());
        sketchChooser.setPromptText("Choose a sketch");
        sketchChooser.setOnAction((evt) -> {
            handleSketchChange(getSelectedSketch().instanciateNew());
        });

        resetButton = new Button("Reset");
        resetButton.setOnAction((evt) -> {
            controller.getCanvas().resetSketch();
        });

        addRow(0, changeSketchLabel, sketchChooser, resetButton);
        setEveryChildsPos();
    }

    private void setEveryChildsPos() {
        for (Node child : getChildren()) {
            setHalignment(child, HPos.CENTER);
            setValignment(child, VPos.CENTER);
            setHgrow(child, Priority.ALWAYS);
            setVgrow(child, Priority.ALWAYS);
        }
    }

    public Sketches getSelectedSketch() {
        return sketchChooser.getSelectionModel().getSelectedItem();
    }

    private void handleSketchChange(Sketch newSketch) {
        controller.changeSketch(newSketch);
    }
}
