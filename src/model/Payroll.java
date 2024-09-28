package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Payroll {

    private double travelAmount = 0.0;
    private double foodAmount = 0.0;
    private double bonusAmount = 0.0;
    private double epfAmount = 0.0;
    private double taxAmount = 0.0;
    private double payeAmount = 0.0;

    SQLRun objSQLRun = new SQLRun();
    public Employee objEmployee = new Employee();

    public double getTravelAmount() {
        return travelAmount;
    }

    public void setTravelAmount(double travelAmount) {
        this.travelAmount = travelAmount;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public double getEpfAmount() {
        return epfAmount;
    }

    public void setEpfAmount(double epfAmount) {
        this.epfAmount = epfAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getPayeAmount() {
        return payeAmount;
    }

    public void setPayeAmount(double payeAmount) {
        this.payeAmount = payeAmount;
    }

    //get salary details - if no details found, return 0
    public boolean getSalaryDetails(String empId) {
        try {

            String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId=" + empId + " AND e.empId=s.empId";
            String sql2 = "SELECT * FROM employee WHERE empId=" + empId;
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objEmployee.setSalAmount(resSet.getDouble("salAmount"));
                objEmployee.setSalType(resSet.getString("salType"));
                this.travelAmount = resSet.getDouble("travelAllow");
                this.foodAmount = resSet.getDouble("foodAllow");
                this.bonusAmount = resSet.getDouble("bonus");
                this.epfAmount = resSet.getDouble("epf");
                this.taxAmount = resSet.getDouble("tax");
                this.payeAmount = resSet.getDouble("paye");

                return true;

            } else if (resSet2.next()) {                
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet2.getString("fname"));
                objEmployee.setLname(resSet2.getString("lname"));
                objEmployee.setDesignation(resSet2.getString("designation"));
                objEmployee.setDepartment(resSet2.getString("department"));
                objEmployee.setSalAmount(resSet2.getDouble("salAmount"));
                objEmployee.setSalType(resSet2.getString("salType"));
                this.travelAmount = 0;
                this.foodAmount = 0;
                this.bonusAmount = 0;
                this.epfAmount = 0;
                this.taxAmount = 0;
                this.payeAmount = 0;
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No record found for Employee ID : " + empId, "ERROR", 0);
                return false;
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }

    public boolean insertSalaryDetails() {
        String sql = "INSERT INTO salary_details (empId,travelAllow,foodAllow,bonus,epf,tax,paye) VALUES "
                + "('" + objEmployee.getEmpId() + "'," + travelAmount + "," + foodAmount + "," + bonusAmount + ","
                + "" + epfAmount + "," + taxAmount + "," + payeAmount + ")";

        int inserted = objSQLRun.sqlUpdate(sql);

        if (inserted > 0) {
            JOptionPane.showMessageDialog(null, "Salary Details for Employee ID : " + objEmployee.getEmpId() + " has been Added Successfully!", "", 1);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error!\nCould not Add Salary Details for Employee ID : " + objEmployee.getEmpId(), "ERROR", 0);
            return false;
        }

    }

    public boolean updateSalaryDetails(String empId) {

        String sql = "UPDATE salary_details SET travelAllow=" + travelAmount + ",foodAllow=" + foodAmount + ","
                + "bonus=" + bonusAmount + ",epf=" + epfAmount + ",tax=" + taxAmount + ","
                + "paye=" + payeAmount + " WHERE empId='" + empId + "'";

        int updated = objSQLRun.sqlUpdate(sql);

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    //calculate earnings and deductions based on input percentages
    public String calculate(String percVal, String basicSal) {
        try {

            double perc = Double.parseDouble(percVal);
            double sal = Double.parseDouble(basicSal);
            double amount = sal * (perc / 100);
            return String.valueOf(amount);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n"
                    + "Check to see whether you have Selected an employee or Entered a valid Percentage", "ERROR", 0);
            return null;
        }
    }
}
