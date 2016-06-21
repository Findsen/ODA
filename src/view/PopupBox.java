package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Flemmer on 29-04-2016.
 */
public class PopupBox {

    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.setAlignment(Pos.CENTER);
        layout.setId("backgroundDefault");

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("view/style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
