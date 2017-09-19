package application.gui.controls.sketch;

import application.gui.canvas.SketchCanvas;
import application.gui.controls.SketchControls;
import application.sketch.Sketch;
import application.sketch.random.RandomCount;
import javafx.scene.control.Button;

public class RandomControls extends SketchControls {
    public RandomControls(RandomCount sketch) {
		super(sketch);
	}

	private Button             resetButton;

	@Override
	protected void resetControls() {
		
	}

	@Override
	protected void initControls() {
		resetButton = new Button("Reset");
		resetButton.setOnAction((evt) -> {
            sketch.reset();
        });
	}

}
