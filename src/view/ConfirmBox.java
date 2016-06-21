package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Flemmer on 29-04-2016.
 */
public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        // Two buttons created
        Button yesButton = new Button("Ja");
        Button noButton = new Button("Nej");

        yesButton.setDefaultButton(true);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //Building a hBox to add the buttons
        HBox hBoxForButtons = new HBox();
        hBoxForButtons.setSpacing(50);
        hBoxForButtons.setAlignment(Pos.BASELINE_CENTER);
        hBoxForButtons.getChildren().addAll(yesButton, noButton);

        // Adding a VBox to set up the label and two buttons
        VBox layout = new VBox(10);
        layout.setSpacing(12);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.getChildren().addAll(label, hBoxForButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setId("backgroundDefault");

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("view/style.css");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}