package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PaySlip {

    SQLRun objSQLRun = new SQLRun();
    public Employee objEmployee = new Employee();
    public Payroll objPayroll = new Payroll();

    private double grossPay = 0.0;
    private double totalDeductions = 0.0;
    private double netPay = 0.0;

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public boolean getPayDetails(String empId) {
        try {

            String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId='" + empId + "' AND e.empId=s.empId";
            ResultSet resSet = objSQLRun.sqlQuery(sql);

            if (resSet.next()) {

                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objEmployee.setSalAmount(resSet.getDouble("salAmount"));
                objPayroll.setTravelAmount(resSet.getDouble("travelAllow"));
                objPayroll.setFoodAmount(resSet.getDouble("foodAllow"));
                objPayroll.setBonusAmount(resSet.getDouble("bonus"));
                objPayroll.setEpfAmount(resSet.getDouble("epf"));
                objPayroll.setTaxAmount(resSet.getDouble("tax"));
                objPayroll.setPayeAmount(resSet.getDouble("paye"));
                grossPay = (objEmployee.getSalAmount() + objPayroll.getTravelAmount() + objPayroll.getFoodAmount() + objPayroll.getBonusAmount());
                totalDeductions = (objPayroll.getEpfAmount() + objPayroll.getTaxAmount() + objPayroll.getPayeAmount());
                netPay = (grossPay - totalDeductions);

                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No Record Found for Employee ID : " + empId, "ERROR", 0);
                return false;

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "No Record Found for Employee ID : " + empId + "\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }

}
