package view;

import controller.CheckMemberDB;
import controller.MembersDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowCheckMember extends MainMenu {

    Stage windowCheckMemberObject;

    private Label labelWindowHeader = new Label("Medlemscheck");
    private String labelInput = "Indtast nummer her";
    private String labelButtonCheck = "Check";
    private String labelButtonClose = "Luk";
    private String labelButtonCreateMember = "Opret";

    public static Label labelOK;

    public static TextField inputField;

    CheckMemberDB cMDB = new CheckMemberDB();


    public void windowCheckMemberStart() {

        windowCheckMemberObject = new Stage();

        // Fiddle with header
        labelWindowHeader.setFont(Font.font(null, FontWeight.BOLD, 24));

        // Buttons
        Button buttonCheck = new Button(labelButtonCheck);
        buttonCheck.setDefaultButton(true);

        Button buttonClose = new Button(labelButtonClose);
        buttonClose.setOnAction(e -> windowCheckMemberObject.close());

        Button buttonCreateMember = new Button(labelButtonCreateMember);
        buttonCreateMember.getStyleClass().add("buttonGreen");

        // Textfield
        inputField = new TextField(labelInput);


        // HBox til label
        HBox label = new HBox();
        label.setSpacing(30);
        label.setPadding(new Insets(35, 0, 35, 0));
        label.setAlignment(Pos.TOP_CENTER);
        label.getChildren().add(labelWindowHeader);

        // ResultBox for output after check.
        labelOK = new Label();
        labelOK.setTextFill(Color.LIGHTGREEN);
        labelOK.setFont(Font.font(18));
        Label labelNOTok = new Label("Er ikke medlem >>>>>>");
        labelNOTok.setTextFill(Color.RED);
        labelNOTok.setFont(Font.font(18));

        HBox resultBox = new HBox();
        resultBox.setSpacing(97);
        resultBox.setPadding(new Insets(0, 0, 35, 0));
        resultBox.setAlignment(Pos.CENTER);

        HBox closeHBox = new HBox();
        closeHBox.setSpacing(96);
        closeHBox.setPadding(new Insets(0, 0, 35, 0));
        closeHBox.setAlignment(Pos.CENTER);
        closeHBox.getChildren().add(buttonClose);

        // Press this button to check phonenumber.
        buttonCheck.setOnAction(e -> {

            if (true == cMDB.memberCheckDB()) {
                inputField.clear();
                resultBox.getChildren().clear();
                resultBox.getChildren().addAll(labelOK);
            } else {
                inputField.clear();
                resultBox.getChildren().clear();
                resultBox.getChildren().addAll(labelNOTok, buttonCreateMember);
            }
        });

        // Name is not a member, so create.
        buttonCreateMember.setOnAction(e -> {
            new CreateMember().createMember();
            windowCheckMemberObject.close();
        });

        // Layout.
        HBox inputBox = new HBox();    //Write phonenumber to be checked in inputField
        inputBox.setSpacing(30);
        inputBox.setPadding(new Insets(0, 0, 35, 0));
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(inputField, buttonCheck);

        VBox layout = new VBox();
        // Content of resultBox is set with buttonCheck.setOnAction()
        layout.getChildren().addAll(label, inputBox, resultBox, closeHBox);
        layout.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        layout.setId("backgroundDefault");

        // Set scene and stage.
        Scene windowCheckMember = new Scene(layout, 400, 300);
        windowCheckMemberObject.setScene(windowCheckMember);
        windowCheckMemberObject.setTitle("Odense Airsoft 1.0 - MEDLEMS CHECK");
        windowCheckMemberObject.initModality(Modality.APPLICATION_MODAL);
        windowCheckMemberObject.setResizable(false);
        windowCheckMemberObject.show();
    }

}