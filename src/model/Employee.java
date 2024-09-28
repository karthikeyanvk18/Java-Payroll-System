package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Employee extends Person {

    private int empId = 0;
    private String designation = "";
    private String department = "";
    private String dateOfJoining = "";
    private String salType = "";
    private double salAmount = 0.0;

    SQLRun objSQLRun = new SQLRun();

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getSalType() {
        return salType;
    }

    public void setSalType(String salType) {
        this.salType = salType;
    }

    public double getSalAmount() {
        return salAmount;
    }

    public void setSalAmount(double salAmount) {
        this.salAmount = salAmount;
    }

    public boolean insertEmployee() {

        String sql = "INSERT INTO employee (empId,nic,fname,lname,dob,address,city,tel_home,tel_mobile,designation,"
                + "department,date_of_joining,gender,salType,salAmount) VALUES ('" + empId + "','" + nic + "','" + fName + "','" + lName + "','" + dob + "',"
                + "'" + address + "','" + city + "','" + telHome + "','" + telMobile + "','" + designation + "',"
                + "'" + department + "','" + dateOfJoining + "','" + gender + "','" + salType + "'," + salAmount + ")";

        int inserted = objSQLRun.sqlUpdate(sql);

        if (inserted > 0) {
            JOptionPane.showMessageDialog(null, "Employee " + fName + " " + lName + " has been added "
                    + "to the system successfully", "Success", 1);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Error occurred while trying to add Employee "
                    + "" + fName + " " + lName + " to the system", "ERROR", 0);
            return false;

        }

    }

    public boolean updateEmployee() {

        String sql = "UPDATE employee set nic='" + nic + "',fname='" + fName + "',lname='" + lName + "',dob='" + dob + "',address='" + address + "',"
                + "city='" + city + "',tel_home='" + telHome + "',tel_mobile='" + telMobile + "',designation='" + designation + "',"
                + "department='" + department + "',salType='" + salType + "',salAmount=" + salAmount + " WHERE empId='" + empId + "'";
        
        int updated = objSQLRun.sqlUpdate(sql);

        if (updated > 0) {
            JOptionPane.showMessageDialog(null, "Employee " + fName + " " + lName + " "
                    + "details has been Updated successfully", "Success", 1);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Error occurred while trying to Update Employee "
                    + "" + fName + " " + lName + " details", "ERROR", 0);
            return false;

        }

    }

    public boolean deleteEmployee(String empId) {

        String sql = "DELETE FROM employee WHERE empId='" + empId + "'";

        int deleted = objSQLRun.sqlUpdate(sql);

        if (deleted > 0) {
            JOptionPane.showMessageDialog(null, "Employee ID:" + empId + " has been deleted successfully", "ERROR", 1);
            return true;

        } else {
            if (empId == null) {
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID:" + empId + " does not exist", "ERROR", 0);
                return false;
            }
        }

    }

    public boolean getEmployeeDetails(String empId) {

        try {
            String sql = "SELECT * FROM employee WHERE empId='" + empId + "'";

            ResultSet rs = objSQLRun.sqlQuery(sql);

            if (rs.next()) {

                this.empId = rs.getInt("empId");
                nic = rs.getString("nic");
                address = rs.getString("address");
                city = rs.getString("city");
                dateOfJoining = rs.getString("date_of_joining");
                department = rs.getString("department");
                designation = rs.getString("designation");
                dob = rs.getString("dob");
                fName = rs.getString("fname");
                lName = rs.getString("lname");
                gender = rs.getString("gender");
                telHome = rs.getString("tel_home");
                telMobile = rs.getString("tel_mobile");
                salType = rs.getString("salType");
                salAmount = rs.getInt("salAmount");

                return true;

            } else {

                JOptionPane.showMessageDialog(null, "No record found for Employee ID:" + empId, "ERROR", 0);
                return false;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;
        }

    }

    //automatically populate employee id field when inserting a new employee
    public String setEmpIdField() {

        try {

            String sql = "SELECT MAX(empId) FROM employee";
            ResultSet rs = objSQLRun.sqlQuery(sql);

            if (rs.next()) {
                int eId = rs.getInt(1);
                eId++;
                return String.valueOf(eId);
            } else {
                JOptionPane.showMessageDialog(null, "Error! Please Contact Your System Administrator!", "ERROR", 0);
                return null;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return null;
        }

    }

    public ResultSet getAllEmployeeDetails(String sql) {

        try {
            ResultSet rs = objSQLRun.sqlQuery(sql);
            return rs;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return null;
        }

    }

    public Vector getColumnNames(ResultSet rs) {
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();
            Vector columns = new Vector();
            for (int i = 1; i <= columnCount; i++) {
                columns.addElement(rsMeta.getColumnName(i));
            }
            return columns;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return null;
        }
    }

    //overloaded method
    public Vector getAllEmployeeDetails(ResultSet rs) {
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();

            Vector data = new Vector();

            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= columnCount; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            return data;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return null;
        }

    }

}
