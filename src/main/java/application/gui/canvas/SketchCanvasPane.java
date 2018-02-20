package application.gui.canvas;

import javafx.scene.layout.Pane;

public class SketchCanvasPane extends Pane {

    private SketchCanvas canvas;

    public SketchCanvasPane(SketchCanvas canvas) {
        this.canvas = canvas;
        getChildren().add(canvas);
    }

    public SketchCanvas getCanvas() {
        return canvas;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        final double x = snappedLeftInset();
        final double y = snappedTopInset();
        final double w = snapSize(getWidth()) - x - snappedRightInset();
        final double h = snapSize(getHeight()) - y - snappedBottomInset();
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
        canvas.setWidth(w);
        canvas.setHeight(h);
    }

}
