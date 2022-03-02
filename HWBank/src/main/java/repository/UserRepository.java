package repository;

import database.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements Repository {
    private Connection connection;
    private PreparedStatement preparedStatement;
   public UserRepository()
   {
       connection = DatabaseConnection.getInstance().getConnection();
   }

   public User findByUserName(String userName )
   {
      String query = "SELECT * FROM users WHERE user_name = ?";
       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,userName);
           ResultSet resultSet = preparedStatement.executeQuery();
           if(!resultSet.next()) {
               return null;
           }else {
               return new User(resultSet.getInt(1),
                       resultSet.getInt(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5),
                       resultSet.getString(6),
                       resultSet.getString(7),
                       resultSet.getString(8),
                       resultSet.getString(9));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }
 public int add(User user){
    String query = "INSERT INTO users (password,user_name,address,phone_number,national_code,last_name,first_name,role_id)" +
            "VALUES (?,?,?,?,?,?,?,?) returning id";
     try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1,user.getPassWord());
         preparedStatement.setString(2,user.getUserName());
         preparedStatement.setString(3,user.getAddress());
         preparedStatement.setString(4,user.getPhoneNumber());
         preparedStatement.setString(5,user.getNationalCode());
         preparedStatement.setString(6,user.getLastName());
         preparedStatement.setString(7,user.getFirstName());
         preparedStatement.setInt(8,user.getRole());
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next())
            return resultSet.getInt(1);
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return 0;
 }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
