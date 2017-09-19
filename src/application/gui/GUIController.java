package application.gui;

import application.gui.canvas.SketchCanvas;
import application.gui.canvas.SketchCanvasPane;
import application.gui.controls.ControlsPane;
import application.sketch.Sketch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIController {
    private Stage            primaryStage;
    private BorderPane       root;
    private Scene            scene;
    private SketchCanvas     canvas;
    private SketchCanvasPane canvasPane;
    private ControlsPane     controlsPane;
    private SketchControlsStage controlsStage;

    public GUIController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setOnCloseRequest((evt) -> {
            Platform.exit();
            System.exit(0);
        });
        root = new BorderPane();
        scene = new Scene(root);

        canvas = new SketchCanvas();
        canvasPane = new SketchCanvasPane(canvas);

        controlsPane = new ControlsPane(this);

        root.setCenter(canvasPane);
        root.setBottom(controlsPane);

        initStage();
        initControlsStage();
    }

    private void initStage() {
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
    private void initControlsStage() {
    	controlsStage = new SketchControlsStage();
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
    	controlsStage.setResizable(true);
    	controlsStage.show();
   }

    public SketchCanvas getCanvas() {
        return canvas;
    }

    public void changeSketch(Sketch newSketch) {
        canvas.setSketch(newSketch);
    }
}
