package model;

import java.sql.*;
import javax.swing.JOptionPane;

public class SQLRun {

    //create and initialize variables
    Statement statement = null;    

    //method to query data
    public ResultSet sqlQuery(String sql) {

        try {            
            statement = DbConnection.getDbConnection().createStatement();
            return statement.executeQuery(sql);

        } catch (SQLException ex1) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex1.getMessage(), "ERROR", 0);
            return null;

        } catch (Exception ex2) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex2.getMessage(), "ERROR", 0);
            return null;

        }
    }

    //method to update data
    public int sqlUpdate(String sql) {
        try {
            statement = DbConnection.getDbConnection().createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException ex1) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Insert Data! Please Contact Your System Administrator!\n\n" + ex1.getMessage(), "ERROR", 0);
            return 0;

        } catch (Exception ex2) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Insert Data! Please Contact Your System Administrator!\n\n" + ex2.getMessage(), "ERROR", 0);
            return 0;

        }

    }

}
