package view;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Flemmer on 13-04-2016.
 */
public class LogIn {

    private Stage logInWindow;
    private MainMenu mainMenu;
    private TextField usernameField;
    private PasswordField passwordField;
    private Text logInPressedText;

    public void logInWindowStart() {

        logInWindow = new Stage();
        mainMenu = new MainMenu();

        // Labels
        final Label welcomeLabel = new Label("          " +
                "Odense Airsoft build 1.0 \n                Venligst log ind\n\n");
        welcomeLabel.setFont(Font.font("Verdana", 14));
        final Label usernameLabel = new Label("Brugernavn: ");
        final Label passwordLabel = new Label("Kodeord: ");

        // Textfields
        usernameField = new TextField("admin");
        passwordField = new PasswordField();
        usernameField.setPromptText("brugernavn");
        passwordField.setPromptText("password");
        passwordField.setText("pass");
        usernameField.setMaxSize(150, 100);
        passwordField.setMaxSize(150, 100);

        // Text
        logInPressedText = new Text();

        // Buttons
        final Button logInButton = new Button("Log ind");
        logInButton.setOnAction(e -> logInButtonPressed());
        logInButton.setDefaultButton(true);

        final Button helpButton = new Button("?");
        helpButton.setOnAction(e -> PopupBox.display("Hjælp", "For at kunne logge ind, skal du bruge et brugernavn og password."));

        //HBox to gather label and textfield(username) so they are next to eachother
        HBox usernameAndUserTextField = new HBox();
        usernameAndUserTextField.setSpacing(5);
        usernameAndUserTextField.getChildren().addAll(usernameLabel, usernameField);

        //HBox to gather label and textfield(password) so they are next to eachother
        HBox passwordAndPassWordTextField = new HBox();
        passwordAndPassWordTextField.setSpacing(19);
        passwordAndPassWordTextField.getChildren().addAll(passwordLabel, passwordField);

        //HBox to gather the two buttons, so they are next to eachother
        HBox helpAndLogInButtons = new HBox();
        helpAndLogInButtons.setSpacing(73);
        helpAndLogInButtons.setPadding(new Insets(0, 0, 0, 70));
        helpAndLogInButtons.getChildren().addAll(logInButton, helpButton);

        // HBox for Text
        HBox textBox = new HBox();
        Text fillfill = new Text("                     ");
        textBox.setPadding(new Insets(0, 0, 10, 0));
        textBox.getChildren().addAll(fillfill, logInPressedText);

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(welcomeLabel, usernameAndUserTextField, passwordAndPassWordTextField, helpAndLogInButtons, textBox);
        vBox.setPadding(new Insets(40, 0, 0, 70));

        StackPane sp = new StackPane();
        sp.getChildren().add(vBox);
        sp.setId("backgroundDefault");

        Scene scene = new Scene(sp);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        logInWindow.setScene(scene);
        logInWindow.setTitle("Odense Airsoft alpha 1.0 - LOG IND");
        logInWindow.setResizable(false);
        logInWindow.setHeight(315);
        logInWindow.setWidth(400);
        logInWindow.show();
    }

    public void logInButtonPressed() {
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("pass")) {
            logInPressedText.setFill(Color.LIGHTGREEN);
            logInPressedText.setText("Login succes!");
            PauseTransition delayForNewWindow = new PauseTransition(Duration.seconds(1));
            PauseTransition delayForText = new PauseTransition(Duration.seconds(1));
            PauseTransition delayCloseLogIn = new PauseTransition(Duration.seconds(1));
            delayForNewWindow.setOnFinished(e -> mainMenu.mainMenu2(logInWindow));
            delayForText.setOnFinished(e -> logInPressedText.setText(""));
            delayCloseLogIn.setOnFinished(e -> logInWindow.close());
            delayForNewWindow.play();
            delayForText.play();
            delayCloseLogIn.play();

            PauseTransition delayClearUser = new PauseTransition(Duration.seconds(1));
            PauseTransition delayClearPass = new PauseTransition(Duration.seconds(1));
            delayClearUser.setOnFinished(e -> usernameField.clear());
            delayClearPass.setOnFinished(e -> passwordField.clear());
            delayClearUser.play();
            delayClearPass.play();
        } else {
            logInPressedText.setFill(Color.FIREBRICK);
            logInPressedText.setText("Forkert brugernavn/password");
            PauseTransition delayForText = new PauseTransition(Duration.seconds(5));
            delayForText.setOnFinished(e -> logInPressedText.setText(""));
            delayForText.play();
            passwordField.clear();
        }
    }
}