package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created by Flemmer on 04-05-2016.
 */
public class MainMenu extends LogIn {

    private Stage mainMenu2;
    private CreateMember createMemberObject;
    private WindowCheckMember windowCheckMemberObject;

    private MemberListGUI memberListGUI;


    void mainMenu2(Stage logInWindow) {

        mainMenu2 = new Stage();
        createMemberObject = new CreateMember();
        windowCheckMemberObject = new WindowCheckMember();

        memberListGUI = new MemberListGUI();


        // Labels
        Label labelHovedMenu = new Label("                      " + "Velkommen!\n Brug knapperne til at navigere rundt");
        labelHovedMenu.setFont(Font.font(null, FontWeight.BOLD, 24));
        labelHovedMenu.setAlignment(Pos.TOP_CENTER);
        Label l_medlem = new Label("Medlem");
        Label l_kiosk = new Label("Kiosk     ");
        Label l_udstyr = new Label("Udstyr   ");
        Label l_osca = new Label("OSCA    ");


        Button b_opret = new Button("Opret medlem");
        b_opret.setOnAction(e -> createMemberObject.createMember());
        Button b_lister = new Button("Lister");
        b_lister.setOnAction(e -> memberListGUI.memberListGUI());
        Button b_check = new Button("Check medlem");
        b_check.setOnAction(e -> windowCheckMemberObject.windowCheckMemberStart());


        Button b_logud = new Button("Log ud");
        b_logud.setOnAction(e -> logUdClicked(logInWindow));

        // HBox for label
        HBox label = new HBox();
        label.setSpacing(30);
        label.setPadding(new Insets(35, 0, 35, 0));
        label.setAlignment(Pos.TOP_CENTER);
        label.getChildren().add(labelHovedMenu);

        // HBOX for setting labels and buttons with eachother (Medlem)
        HBox medlemBox = new HBox();
        medlemBox.setSpacing(30);
        medlemBox.setAlignment(Pos.TOP_CENTER);
        medlemBox.setPadding(new Insets(0, 0, 20, 0));
        medlemBox.getChildren().addAll(l_medlem, b_opret, b_check, b_lister);


        // HBox for log-ud
        HBox h_logud = new HBox();
        h_logud.setSpacing(30);
        h_logud.setAlignment(Pos.BOTTOM_RIGHT);
        h_logud.setPadding(new Insets(10, 20, 0, 0));
        h_logud.getChildren().addAll(b_logud);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, medlemBox, h_logud);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(vBox);
        sp.setId("backgroundDefault");

        Scene scene2 = new Scene(sp, 500, 380);
        scene2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        mainMenu2.setTitle("Odense Airsoft alpha 1.0 - HOVEDMENU ");
        mainMenu2.setScene(scene2);
        mainMenu2.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        mainMenu2.sizeToScene();
        mainMenu2.setResizable(false);
        mainMenu2.show();
    }

    //---------------------------------------------------------------------------------

    public void closeProgram() {
        Boolean answer = ConfirmBox.display("Luk", "Er du sikker på, at du vil lukke?");

        if (answer.equals(true)) {
            System.exit(0);
        }
    }

    private void logUdClicked(Stage logInWindow) {
        Boolean answer = ConfirmBox.display("Log ud", "Er du sikker på, at du vil logge ud?");

        if (answer.equals(true)) {
            mainMenu2.close();
            logInWindow.show();
        }
    }

}