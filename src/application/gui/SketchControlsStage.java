package application.gui;

import application.gui.controls.SketchControls;
import javafx.stage.Stage;

public class SketchControlsStage extends Stage{
	private SketchControls controls;
	
	public SketchControlsStage() {
		this(null);
	}
	
	public SketchControlsStage(SketchControls controls) {
		this.controls = controls;
		setControls(controls);
	}
	
	public  SketchControls getControls() {
		return controls;
	}
	
	public void setControls(SketchControls controls) {
		this.controls = controls;
		this.setScene(controls);
	}
}
