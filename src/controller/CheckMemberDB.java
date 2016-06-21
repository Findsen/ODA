package controller;

import view.WindowCheckMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Christian Findsen on 21-06-2016.
 */
public class CheckMemberDB {

   
    public boolean memberCheckDB() {

        boolean check = true;

        try {


            Connection myCoon = DriverManager.getConnection
                    (
                            "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7119335", "sql7119335", "Uc898nAzAE"
                    );


            Statement myStmt = myCoon.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM Member_List WHERE "
                    + "Phonenumber"
                    + " = "
                    + WindowCheckMember.inputField.getText());


            if (rs.next()) {

                String firstname = rs.getString("First_Name");
                String lastname = rs.getString("Last_Name");
                String memberUntil = rs.getString("Member_Until");
                WindowCheckMember.labelOK.setText(firstname + " " + lastname + " er medlem indtil " + memberUntil);

                check = true;

            } else {
                check = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }
}
