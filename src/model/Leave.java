package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Leave {

    SQLRun objSQLRun = new SQLRun();
    public Employee objEmployee = new Employee();

    private double annual = 0.0;
    private double casual = 0.0;
    private double optional = 0.0;

    public double getAnnual() {
        return annual;
    }

    public void setAnnual(double annual) {
        this.annual = annual;
    }

    public double getCasual() {
        return casual;
    }

    public void setCasual(double casual) {
        this.casual = casual;
    }

    public double getOptional() {
        return optional;
    }

    public void setOptional(double optional) {
        this.optional = optional;
    }

    public boolean getLeaveDetails(String empId) {
        try {
            String sql = "SELECT * FROM employee e,tbl_leave l WHERE e.empId=l.empId AND e.empId='" + empId + "'";
            ResultSet resSet = objSQLRun.sqlQuery(sql);

            if (resSet.next()) {
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                this.annual = resSet.getDouble("annual");
                this.casual = resSet.getDouble("casual");
                this.optional = resSet.getDouble("optional");

                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No Record Found for Employee ID : " + empId, "ERROR", 0);
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;
        }
    }

    public boolean applyLeave() {
        try {

            String sqlUpdate = "UPDATE tbl_leave SET annual=" + annual + ",casual=" + casual + ","
                    + "optional=" + optional + " WHERE empId='" + objEmployee.getEmpId() + "'";

            int updated = objSQLRun.sqlUpdate(sqlUpdate);

            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Your Leave has been Recorded", "", 1);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error in Applying Leave", "ERROR", 0);
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;
        }
    }

}
