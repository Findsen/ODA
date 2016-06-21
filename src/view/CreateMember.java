package view;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.*;

/**
 * Created by Flemmer on 28-04-2016.
 */
public class CreateMember extends MainMenu {



    public static TextField tf_first_name;
    public static TextField tf_last_name;
    public static TextField tf_address;
    public static TextField tf_city;
    public static TextField tf_zip_code;
    public static TextField tf_email_address;
    public static TextField tf_phone_number;
    public static TextField tf_Birthday;
    public static TextField tf_membership_period;
    public static TextField tf_Id;

    Stage createMemberObject;
    CreateMemberDB cmdb = new CreateMemberDB();



    public  void createMember(){

        createMemberObject = new Stage();


        // Labels
        final Label title1 = new Label("                          " +
                "Opret medlem\n\n\n");
        title1.setFont(Font.font(null, FontWeight.BOLD, 24));
        final Label displayInfo = new Label("Alle felter skal udfyldes, og fødselsdato skal være korrekt format!");
        final Label l_first_name = new Label("          Fornavn*:                       ");
        final Label l_last_name = new Label("          Efternavn*:                     ");
        final Label l_address = new Label("          Adresse*:                       ");
        final Label l_city = new Label("          By*:                                ");
        final Label l_zip_code = new Label("Post nr*:");
        final Label l_email_address = new Label("          E-mail adresse*:            ");
        final Label l_phone_number = new Label("          Telefon nr*:                   ");
        final Label l_birth_day = new Label("          Fødselsdag*:                 ");
        final Label l_membership_period = new Label("          Medlem til*:                  ");
        final Label l_driverlicense_or_passport = new Label("          Kørekort el. pas nr*:      ");

        // Confirmation label
        final Text conf_text = new Text("    \n    ");
        conf_text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        // Textfields
        tf_first_name = new TextField();
        tf_first_name.setPrefSize(300, 15);

        tf_last_name = new TextField();
        tf_last_name.setPrefSize(300, 15);

        tf_address = new TextField();
        tf_address.setPrefSize(300, 15);

        tf_city = new TextField();
        tf_city.setPrefSize(170, 15);

        tf_zip_code = new TextField();
        tf_zip_code.setPrefSize(73, 15);

        tf_email_address =  new TextField();
        tf_email_address.setPromptText("example@odenseairsoft.com");
        tf_email_address.setPrefSize(300, 15);

        tf_phone_number = new TextField();
        tf_phone_number.setPromptText("+45");
        tf_phone_number.setPrefSize(130, 15);


        tf_Birthday = new TextField();
        tf_Birthday.setPromptText("yyyy-mm-dd");
        tf_Birthday.setPrefSize(130, 15);

        tf_membership_period = new TextField();
        tf_membership_period.setPrefSize(300, 15);
        tf_membership_period.setPromptText("yyyy-mm-dd");

        tf_Id = new TextField();
        tf_Id.setPrefSize(300, 15);

        // Current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        // Checkboxes
        Label fillspace = new Label("          Vælg type*:              ");
        ToggleGroup group = new ToggleGroup();
        RadioButton box1 = new RadioButton("Basis medlem");
        box1.setOnAction(e -> {
            tf_membership_period.setText(dateFormat.format(date));
            tf_membership_period.setEditable(false);
            tf_membership_period.setStyle("-fx-border-color: red;");
        });
        box1.setToggleGroup(group);

        RadioButton box2 = new RadioButton("Fuldt medlem");
        box2.setOnAction(e -> {
            tf_membership_period.setEditable(true);
            tf_membership_period.clear();
            tf_membership_period.setStyle("-fx-border-color: white;");

        });
        box2.setToggleGroup(group);


        // Create button
        Button b_create = new Button("Opret medlem & luk");
        b_create.getStyleClass().add("buttonGreen");
        b_create.setOnAction(e -> {

            // CreateMemberDB method
            cmdb.createMemberDB();

            // Display confirmation to the user
            conf_text.setFill(Color.LIGHTGREEN);
            conf_text.setText("Medlem oprettet og gemt i database!\nLukker vindue inden for 5 sekunder...");

            PauseTransition delayForClose = new PauseTransition(Duration.seconds(5));
            delayForClose.setOnFinished(e1 -> {
                conf_text.setText("    \n    ");
                createMemberObject.close();
            });
            delayForClose.play();

        });

        // Clear button
        Button b_clear = new Button("Opret medlem & ryd felter");
        b_clear.getStyleClass().add("buttonYellow");
        b_clear.setOnAction(e -> {

            // CreateMemberDB method
            cmdb.createMemberDB();

            // Clear every field
            tf_address.clear();
            tf_Birthday.clear();
            tf_city.clear();
            tf_Id.clear();
            tf_email_address.clear();
            tf_first_name.clear();
            tf_last_name.clear();
            tf_membership_period.clear();
            tf_phone_number.clear();
            tf_zip_code.clear();

            // Display text to the user
            conf_text.setFill(Color.YELLOW);
            conf_text.setText("Medlem gemt i database - klar til nyt medlem!\n ");
            PauseTransition delayForClear = new PauseTransition(Duration.seconds(5));
            delayForClear.setOnFinished(e1 -> {
                conf_text.setText("    \n    ");
            });
            delayForClear.play();

        });

        // Close button
        Button b_close = new Button("Luk");
        b_close.setOnAction(e -> {
            Boolean result = ConfirmBox.display("Luk", "Er du sikker på, at du vil lukke?");

            if(result.equals(true)){
                createMemberObject.close();
            }
        });

        // HBox for textinfo
        HBox textinfo = new HBox();
        textinfo.setPadding(new Insets(10, 0, 10, 33));
        textinfo.getChildren().add(displayInfo);

        // HBoxes for buttons
        HBox hb_b_create_clear = new HBox();
        hb_b_create_clear.setSpacing(16);
        hb_b_create_clear.setAlignment(Pos.BOTTOM_CENTER);
        hb_b_create_clear.setPadding(new Insets(20, 0, 0, 115));
        hb_b_create_clear.getChildren().addAll(b_create, b_clear);

        HBox hb_b_close = new HBox();
        hb_b_close.setAlignment(Pos.BOTTOM_RIGHT);
        hb_b_close.setPadding(new Insets(0, 15, 10, 0));
        hb_b_close.getChildren().add(b_close);

        // HBoxes for radiobuttons
        HBox hb_checkbox = new HBox();
        hb_checkbox.setSpacing(20);
        hb_checkbox.setPadding(new Insets(30, 0, 20, 0));
        hb_checkbox.getChildren().addAll(fillspace, box1, box2);

        // HBoxes for confirmation labels
        HBox hb_conf_l = new HBox();
        hb_conf_l.setSpacing(20);
        hb_conf_l.setAlignment(Pos.CENTER);
        hb_conf_l.setPadding(new Insets(30, 0, 0, 0));
        hb_conf_l.getChildren().addAll(conf_text);

        // HBoxes to set label and textfield next to each other
        HBox hb_first_name = new HBox();
        hb_first_name.setSpacing(5);
        hb_first_name.getChildren().addAll(l_first_name, tf_first_name);

        HBox hb_last_name = new HBox();
        hb_last_name.setSpacing(5);
        hb_last_name.getChildren().addAll(l_last_name, tf_last_name);

        HBox address = new HBox();
        address.setSpacing(5);
        address.getChildren().addAll(l_address, tf_address);

        HBox city_zipcode = new HBox();
        city_zipcode.setSpacing(5);
        city_zipcode.getChildren().addAll(l_city, tf_city, l_zip_code, tf_zip_code);

        HBox emailaddress = new HBox();
        emailaddress.setSpacing(5);
        emailaddress.getChildren().addAll(l_email_address, tf_email_address);

        HBox phonenumber = new HBox();
        phonenumber.setSpacing(5);
        phonenumber.getChildren().addAll(l_phone_number, tf_phone_number);

        HBox birthday = new HBox();
        birthday.setSpacing(5);
        birthday.getChildren().addAll(l_birth_day, tf_Birthday);

        HBox membershipperiod = new HBox();
        membershipperiod.setSpacing(5);
        membershipperiod.getChildren().addAll(l_membership_period, tf_membership_period);

        HBox driverlicensepassport = new HBox();
        driverlicensepassport.setSpacing(5);
        driverlicensepassport.setPadding(new Insets(0, 0, 0, 0));
        driverlicensepassport.getChildren().addAll(l_driverlicense_or_passport, tf_Id);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(title1, textinfo, hb_first_name, hb_last_name, address, city_zipcode, emailaddress, phonenumber, birthday,
                hb_checkbox, membershipperiod, driverlicensepassport, hb_b_create_clear, hb_conf_l, hb_b_close);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(vBox);
        sp.setId("backgroundDefault");

        Scene scene2 = new Scene(sp, 500, 610);
        scene2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        createMemberObject.setTitle("Odense Airsoft alpha 1.0 - OPRET MEDLEM");
        createMemberObject.setScene(scene2);
        createMemberObject.setResizable(false);
        //setEventsScreensaver(scene2);
        createMemberObject.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        createMemberObject.initModality(Modality.APPLICATION_MODAL);
        createMemberObject.show();

    }

    //---------------------------------------------------------------------------------

    public void closeProgram(){
        Boolean answer = ConfirmBox.display("Luk", "Er du sikker på, at du vil lukke?");

        if (answer.equals(true)) {
            createMemberObject.close();
        }
    }




}