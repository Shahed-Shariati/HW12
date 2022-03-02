package repository;

import database.DatabaseConnection;
import model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCardRepository implements Repository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement ;

    public int add(CreditCard creditCard)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date;
        java.sql.Date sqlDate;
        String query = "INSERT INTO creditcard (number,expirdate,cvv,password,password2,isactive,failed_password) " +
                       "VALUES (?,?,?,?,?,?,?) RETURNING id";
        try {
            date = formatDate.parse(creditCard.getExpireDate());
            sqlDate = new java.sql.Date(date.getTime());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,creditCard.getNumberCard());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,creditCard.getCvv());
            preparedStatement.setString(4,creditCard.getPassword());
            preparedStatement.setString(5, creditCard.getPasswordOnline());
            preparedStatement.setInt(6,0);
            preparedStatement.setInt(7,0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return resultSet.getInt(1);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void upDate(CreditCard creditCard){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date;
        java.sql.Date sqlDate;
        String query = "UPDATE creditcard SET number = ? ,expirdate = ?,cvv = ?,password = ?,password2 = ?,isactive = ? WHERE id = ?; ";
        try {
            date = formatDate.parse(creditCard.getExpireDate());
            sqlDate = new java.sql.Date(date.getTime());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,creditCard.getNumberCard());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,creditCard.getCvv());
            preparedStatement.setString(4, creditCard.getPassword());
            preparedStatement.setString(5, creditCard.getPasswordOnline());
            preparedStatement.setInt(6,creditCard.getIsActiveInt());
            preparedStatement.setInt(7,creditCard.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ParseException e) {
           // e.printStackTrace();
            if(e.getMessage().equals("value too long for type character varying(4)")){
                System.out.println("Enter 4 Character");
            }
        }
    }

    public CreditCard findByAccountId(int id)
    {
        String query = "select c2.id,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive,c2.failed_password from account a inner join creditcard c2 on c2.id = a.credit_card_id " +
                "where a.id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return new CreditCard(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CreditCard> findByCustomerId(int customerId){
       String query = "select c2.id,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive,c2.failed_password from customer c inner join accoutncustoer a on c.id = a.customer_id " +
               "                 inner join account a2 on a2.id = a.account_id" +
               "                  inner join creditcard c2 on c2.id = a2.credit_card_id" +
               "               where c.id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CreditCard> creditCards = new ArrayList<>();
            while (resultSet.next())
            {
                creditCards.add(  new CreditCard(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                        resultSet.getInt(8)));
            }
            return creditCards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CreditCard findByCardNumber(String cardNumber){
        String query = "SELECT * FROM creditcard WHERE number = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return new CreditCard(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM creditcard WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void failedPassword(CreditCard creditCard){
        String query = "UPDATE creditcard set failed_password = failed_password + 1 WHERE id = ?";
       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1,creditCard.getId());
           preparedStatement.executeUpdate();
           preparedStatement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

  }
