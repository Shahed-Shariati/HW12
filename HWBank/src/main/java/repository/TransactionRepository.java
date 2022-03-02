package repository;

import database.DatabaseConnection;
import model.Account;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements Repository{
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement;
     public void findByDate()
     {

     }
     public void addTransaction(Transaction transaction){
         String query = "INSERT INTO transactions (accountid, amount, status, types, transaction_date, account_destination) \n" +
                 "VALUES (?,?,?,?,?,?)";
         java.util.Date date = transaction.getDate();
         Date dateSql = new Date(date.getTime());

         try {

             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1,transaction.getAccountSource().getId());
             preparedStatement.setDouble(2,transaction.getAmount());
             preparedStatement.setString(3,transaction.getStatus());
             preparedStatement.setString(4,transaction.getType());
             preparedStatement.setDate(5, dateSql);
             preparedStatement.setInt(6,transaction.getAccountDestination().getId());
             preparedStatement.execute();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

     public List<Transaction> findByAccountNumberAndDate(String accountNumber, Date date){
      String query = "SELECT * FROM account a INNER JOIN transactions t on a.id = t.accountid\n" +
              "WHERE a.account_number = ? AND t.transaction_date >  ? ::date;";
         try {
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1,accountNumber);
             preparedStatement.setDate(2,date);
             ResultSet resultSet =preparedStatement.executeQuery();
             List<Transaction> transactions = new ArrayList<>();
             while (resultSet.next()){
                 Account account = new Account(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3) );
                 transactions.add(new Transaction(resultSet.getInt(6),
                                                   account,
                         resultSet.getDouble(8),
                         resultSet.getString(9),
                         resultSet.getString(10),
                         resultSet.getDate(11)));
             }
             return transactions;
         } catch (SQLException e) {
             e.printStackTrace();
         }
          return null;
     }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM transactions WHERE id = ?;";
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
