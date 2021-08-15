package phoneBook;

import Helpers.DBUtils;

import java.sql.*;
public class userRegistration {


    private Connection con;
    public userRegistration(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacoursework?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    ResultSet get(){
        try {
            String select = "SELECT *FROM phonebook";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null; // in a try and catch box if it gets error and fail to run then we need to use return null
    }
    void insert(String firstName, String lastName, String phoneNumber , String privateCheckBox){
  try {
      String insert = "INSERT INTO phonebook(first_name,last_name,phone,private)" +
              "VALUES(?,?,?,?)";

      PreparedStatement statement = con.prepareStatement(insert);

      statement.setString(1,firstName);
      statement.setString(2,lastName);
      statement.setString(3,phoneNumber);
      statement.setString(4,privateCheckBox);

      statement.executeUpdate();
      statement.close();
  }
  catch (SQLException e){
      e.printStackTrace();
  }
    }

    void removeData(String phone){
        String  delete = "DELETE FROM phonebook WHERE phone = ?";

        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1,phone);

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    void updateField(String S_NO, String firstName, String lastName, String phone, String privateCheckBox){
    try {
        String update = "UPDATE phonebook SET first_name = ?, last_name = ?, private=? WHERE phone = ?";
        PreparedStatement statement = con.prepareStatement(update);
        statement.setString(1,firstName);
        statement.setString(2,lastName);
        statement.setString(3,privateCheckBox);
        statement.setString(4,S_NO);

        statement.executeUpdate();
        statement.close();
}
    catch (SQLException e){
        e.printStackTrace();
    }
    }
}
