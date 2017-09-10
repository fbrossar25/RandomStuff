package application;

import application.gui.GUIController;
//TODO use OpenSimplex because Simplex is patended
import application.gui.dialogs.ExceptionDialog;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    public static final boolean PRODUCTION = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(Main::handleUncaughtException);
        try {
            new GUIController(primaryStage);
        } catch (Exception e) {
            handleUncaughtException(Thread.currentThread(), e);
        }
    }

    public static void handleUncaughtException(Thread t, Throwable e) {
        if (PRODUCTION) {
            new ExceptionDialog(e);
        } else {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(-1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
