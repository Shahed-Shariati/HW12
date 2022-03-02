package repository;

import database.DataBaseJunit;
import database.SessionFactoryJunit;
import database.SessionFactorySingleton;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Transient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements Repository<User>{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private SessionFactory sessionFactory;
    private Connection connectionjunit;
    public UserRepository(Connection connection)
    {
        this.sessionFactory = SessionFactorySingleton.getSessionFactory();
        this.connection = connection;
        this.connectionjunit = DataBaseJunit.getInstance().getConnection();
    }
    public UserRepository(){
        this.connectionjunit = DataBaseJunit.getInstance().getConnection();
        this.sessionFactory = SessionFactoryJunit.getSessionFactory();
    }

    @Override
    public int save(User user) {
       /* try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                return user.getId();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }

        }*/
        String query = "INSERT INTO users(role_id,first_name,last_name,phone_number,address,user_name,password)" +
                "VALUES (?,?,?,?,?,?,?) RETURNING id";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,user.getRole());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4,user.getPhoneNumber());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getUserName());
            preparedStatement.setString(7,user.getPassWord());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    @Override
    public void upDate(User user) {
      String query = "UPDATE users set username = ? , password = ? ; ";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassWord());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public User findByUserName(String userName){
     String query = "SELECT * FROM users WHERE user_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void truncate(){
        String query = "TRUNCATE TABLE users ";
        try {
            preparedStatement = connectionjunit.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
