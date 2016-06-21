import javafx.application.Application;
import javafx.stage.Stage;
import view.LogIn;


/**
 * Created by Flemmer on 19-05-2016.
 */

public class RunClient extends Application{

    // Main method
    public static void main(String[] args) {
        launch(args);
    }

    Stage logInWindow;

    // Start method

    public void start(Stage primaryStage) throws Exception {
        logInWindow = primaryStage;

        LogIn logInObject = new LogIn();
        logInObject.logInWindowStart();

    }
}