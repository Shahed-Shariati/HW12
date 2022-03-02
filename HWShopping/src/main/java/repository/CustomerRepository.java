package repository;

import database.DataBaseJunit;
import database.SessionFactoryJunit;
import database.SessionFactorySingleton;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository implements Repository<Customer>{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private SessionFactory sessionFactory;
   private Connection connectionjunit;
    public CustomerRepository(Connection connection)
    {
        this.connection = connection;
        this.connectionjunit = DataBaseJunit.getInstance().getConnection();
        sessionFactory = SessionFactorySingleton.getSessionFactory();
    }
    public CustomerRepository(){
        this.connectionjunit = DataBaseJunit.getInstance().getConnection();
        sessionFactory = SessionFactoryJunit.getSessionFactory();
    }

    public Customer add(int userId,double balance){
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setBalance(balance);
          try(Session session = sessionFactory.openSession()){
              Transaction transaction = session.beginTransaction();
              try{
                  session.save(customer);
                  transaction.commit();
                  return customer;
              }catch (Exception e){
                  transaction.rollback();
                  throw e;
              }
          }

    }
    @Override
    public int save(Customer customer) {
      try(Session session = sessionFactory.openSession()){
          Transaction transaction = session.beginTransaction();
          try{
              session.save(customer);
              transaction.commit();
              return customer.getId();
          }catch (Exception e){
              transaction.rollback();
              throw e;
          }
      }



    }

    @Override
    public void upDate(Customer customer) {
       try (Session session = sessionFactory.openSession()){
           Transaction transaction = session.beginTransaction();
           try{
               session.update(customer);
               transaction.commit();
           }catch (Exception e){
               transaction.rollback();
               throw e;
           }
       }


    }

    @Override
    public void delete(int id) {
     try (Session session = sessionFactory.openSession()){
        Transaction transaction = session.beginTransaction();
        try{
            Customer customer = new Customer();
            customer.setId(id);
            session.delete(customer);
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
     }
    }

    @Override
    public Customer find(int id) {
      try (Session session = sessionFactory.openSession()){
          try {
             return   session.get(Customer.class,id);

          }catch (Exception e){
              throw e;
          }

      }

    }

    @Override
    public List<Customer> findAll() {
       try(Session session = sessionFactory.openSession()){
           try {
           return  session.createCriteria(Customer.class).list();
              /* Query<Customer> query = session.createQuery("SELECT  * FROM customer ", Customer.class);
               return List<Customer> list = (List<Customer>)query.getResultList();*/
           }catch (Exception e){
               throw e;
           }
       }

    }

    public Customer findByUserId(int id){
        String query = "SELECT * FROM customer INNER JOIN users u on u.id = customer.user_id\n" +
                "                                                         WHERE u.id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
             return mapTo(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    private Customer mapTo(ResultSet resultSet){
        try {
            while (resultSet.next()) {
                return new Customer(resultSet.getInt(4),
                        resultSet.getInt("role_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt(1));
            }
        }catch (SQLException e){
           e.printStackTrace();
        }
        return null;
    }

   public void truncate(){
        String query = "TRUNCATE TABLE customer";
        try {
            preparedStatement = connectionjunit.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
