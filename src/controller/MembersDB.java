package controller;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class MembersDB {


    public static ObservableList<ODA_Member> getMember() {
        ObservableList<ODA_Member> ODAMembers = FXCollections.observableArrayList();

        try {

            //Building a connection to the database

            Connection myConn = DriverManager.getConnection
                    (
                            "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7119335", "sql7119335", "Uc898nAzAE"
                    );



            // Creating statement
            Statement myStmt = myConn.createStatement();
            // Execute the  MySQL query
            ResultSet myRs = myStmt.executeQuery
                    ("SELECT Member_ID, First_Name,Last_Name,Address,Zipcode," +
                            "ZipcodeDB.Bynavn,Email,Phonenumber,Birthday,Member_Until,Person_Id" +
                            " FROM Member_List" +
                            " JOIN ZipcodeDB on Member_List.Zipcode = ZipcodeDB.Postnummer");
            //Process the result
            while (myRs.next()) {

                // Retreiving the info from the database
                int Member_ID = myRs.getInt("Member_ID");
                String first_name = myRs.getString("First_Name");
                String last_name = myRs.getString("Last_Name");
                String address = myRs.getString("Address");
                int zipcode = myRs.getInt("Zipcode");
                String city = myRs.getString("Bynavn");
                String email = myRs.getString("Email");
                String phonenumber = myRs.getString("Phonenumber");
                String birthday = myRs.getString("Birthday");
                String member_until = myRs.getString("Member_Until");
                String id = myRs.getString("Person_Id");

                //Building an ODAMembers object with all these paramaters
                ODAMembers.add
                        (
                                new ODA_Member(
                                        Member_ID, first_name, last_name,
                                        address, zipcode, city, email, phonenumber,
                                        birthday, member_until,id)
                        );

            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return ODAMembers;


    }



}// End controller.controller.MembersDB
