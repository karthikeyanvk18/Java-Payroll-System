package model;

import java.sql.ResultSet;

public class User extends Person {

    SQLRun objSQLRun = new SQLRun();
    private String userName = "";
    private String password = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultSet isUser() {
        String sql = "SELECT * FROM User WHERE userName='"+userName+"' AND password='"+password+"'";
        ResultSet resSet = objSQLRun.sqlQuery(sql);
        return resSet;
    }
}
