package view;

import controller.MembersDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.ODA_Member;

/**
 * Created by Christian Findsen on 12-05-2016.
 */
public class MemberListGUI extends MainMenu {

    Stage window;
    TableView<ODA_Member> table;


    public void memberListGUI()  {
        window = new Stage();
        window.setTitle("ODA - Medlemsliste");
        window.setMaximized(true);

        //Building the columns - member_ID column
        TableColumn<ODA_Member, String> memberIdColumn = new TableColumn<>("Medlemsnr");
        memberIdColumn.setMinWidth(25);
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        memberIdColumn.setVisible(false);

        //Building the columns - First name column
        TableColumn<ODA_Member, String> first_nameColumn = new TableColumn<>("Fornavn");
        first_nameColumn.setMinWidth(150);
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        first_nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //Building the columns - last_name column
        TableColumn<ODA_Member, String> last_nameColumn = new TableColumn<>("Efternavn");
        last_nameColumn.setMinWidth(150);
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        last_nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Address column
        TableColumn<ODA_Member, String> addressColumn = new TableColumn<>("Adresse");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Zipcode column
        TableColumn<ODA_Member, Integer> zipCodeColumn = new TableColumn<>("Postnr");
        zipCodeColumn.setMinWidth(100);
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        zipCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //Building the columns - City column
        TableColumn<ODA_Member, String> cityColumn = new TableColumn<>("By");
        cityColumn.setMinWidth(80);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Email column
        TableColumn<ODA_Member, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Phonenumber column
        TableColumn<ODA_Member, String> phoneColumn = new TableColumn<>("Tlf");
        phoneColumn.setMinWidth(50);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Birthday column
        TableColumn<ODA_Member, String> birthdayColumn = new TableColumn<>("Fødselsdag");
        birthdayColumn.setMinWidth(100);
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        birthdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Member_Until column
        TableColumn<ODA_Member, String> memberUntilColumn = new TableColumn<>("Medlem indtil");
        memberUntilColumn.setMinWidth(100);
        memberUntilColumn.setCellValueFactory(new PropertyValueFactory<>("member_Until"));
        memberUntilColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Building the columns - Id column
        TableColumn<ODA_Member, String> idColumn = new TableColumn<>("Pas/Kørekort");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Building buttons
        Button cancel = new Button("Luk");
        cancel.setMinWidth(50);
        cancel.setOnAction(
                event -> window.close()
        );

        Button genopfrisk = new Button("Genopfrisk");
        genopfrisk.setMinWidth(50);
        genopfrisk.setOnAction(e-> MembersDB.getMember() );

        //Making a HBox to contain the buttons
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 0));
        hBox.setSpacing(20);
        hBox.getChildren().addAll(genopfrisk, cancel);

        // Adding an AncherPane so we can chose where the buttons will be
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(hBox);
        AnchorPane.setRightAnchor(hBox,10.0);

        // Adding a tableview to contain our columns
        table = new TableView<>();
        table.setItems(MembersDB.getMember());
        table.getColumns().addAll(
                memberIdColumn,
                first_nameColumn,
                last_nameColumn,
                addressColumn,
                zipCodeColumn,
                cityColumn,
                emailColumn,
                phoneColumn,
                birthdayColumn,
                memberUntilColumn,
                idColumn);
        table.setEditable(true);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setVgrow(table, Priority.ALWAYS); //Letting the tableview grow to fit the screen
        vBox.getChildren().addAll(table, anchorPane);
        vBox.setId("backgroundDefault");

        Scene scene = new Scene(vBox);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        window.setScene(scene);
        window.show();
    }

}
