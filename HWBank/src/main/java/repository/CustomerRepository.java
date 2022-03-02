package repository;

import database.DatabaseConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public CustomerRepository()
     {
         connection = DatabaseConnection.getInstance().getConnection();
     }


     public List<Customer> showCustomer()
     {
         String query = "select c.id,u.first_name,u.last_name,u.national_code,u.role_id from customer c inner join users u on u.id = c.user_id\n" +
                 "where u.role_id = 2;";
         try {
             List<Customer> customers = new ArrayList<>();
             preparedStatement = connection.prepareStatement(query);
              ResultSet resultSet =  preparedStatement.executeQuery();
              while (resultSet.next()){
                   customers.add(new Customer(resultSet.getInt(1),
                           resultSet.getString(2),
                           resultSet.getString(3),
                           resultSet.getString(4),
                           resultSet.getInt(5)));
              }
              return customers;
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
     }
     public int add(int userId){
         String query = "INSERT INTO customer (user_id) VALUES (?) RETURNING id";
         try {
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1,userId);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next())
             {
                 return resultSet.getInt(1);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return 0;
     }

  public Customer findById(int id)
  {
      String query = "select c.id,u.first_name,u.last_name,u.national_code,u.role_id from customer c" +
              " inner join users u on u.id = c.user_id where u.id = ?;";
      try {
          preparedStatement = connection.prepareStatement(query);
          preparedStatement.setInt(1,id);
          ResultSet resultSet =  preparedStatement.executeQuery();
          while (resultSet.next())
          {
            return    new Customer (resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getInt(5));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return null;
  }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM customer WHERE id = ?;";
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
