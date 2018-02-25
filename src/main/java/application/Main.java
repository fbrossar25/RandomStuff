package application;

import application.gui.GUIController;
import application.gui.dialogs.ExceptionDialog;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    public static final boolean PRODUCTION = false;

    public static GUIController CONTROLLER_INSTANCE;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(Main::handleUncaughtException);
        try {
            CONTROLLER_INSTANCE = new GUIController(primaryStage);
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
