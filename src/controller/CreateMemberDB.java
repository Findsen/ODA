package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import view.CreateMember;

/**
 * Created by Christian Findsen on 17-06-2016.
 */
public class CreateMemberDB {

    public void createMemberDB() {

        //CreateMember cm = new CreateMember();

        try {

            System.out.println("Establishing a connection!");
            Connection myConn = DriverManager.getConnection
                    (
                            "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7119335", "sql7119335", "Uc898nAzAE"
                    );
            System.out.println("Connection success!");

            // Creating a PreparedStatement
            PreparedStatement myStmt = myConn.prepareStatement(
                    "INSERT INTO Member_List(First_Name,Last_Name,Address,Zipcode," +
                            "Email,Phonenumber,Birthday,Member_Until,Person_Id) "+
                            "VALUES(?,?,?,?,?,?,?,?,?)");

            myStmt.setString(1, CreateMember.tf_first_name.getText());
            myStmt.setString(2, CreateMember.tf_last_name.getText());
            myStmt.setString(3, CreateMember.tf_address.getText());
            myStmt.setInt(   4, Integer.parseInt(CreateMember.tf_zip_code.getText()));
            myStmt.setString(5, CreateMember.tf_email_address.getText());
            myStmt.setString(6, CreateMember.tf_phone_number.getText());
            myStmt.setString(7, CreateMember.tf_Birthday.getText());
            myStmt.setString(8, CreateMember.tf_membership_period.getText());
            myStmt.setString(9, CreateMember.tf_Id.getText());

            myStmt.executeUpdate();

            PreparedStatement mStmt2 = myConn.prepareStatement
                    ("Insert into ZipcodeDB(Postnummer, Bynavn)" +
                            " Values (?,?)" +
                            " on duplicate key update Postnummer ="
                            + Integer.parseInt(CreateMember.tf_zip_code.getText()));

            mStmt2.setInt(1, Integer.parseInt(CreateMember.tf_zip_code.getText()));
            mStmt2.setString(2, CreateMember.tf_city.getText());
            mStmt2.executeUpdate();
            myConn.close();




        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }


}
