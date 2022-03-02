package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    private static Connection connection = DatabaseConnection.getInstance().getConnection();
    private static PreparedStatement preparedStatement;
    public CreateTable(){
   createTableRole();
   createTableUser();
  // insertIntoRole();
   createTableCustomer();
   createTableCategory();
   createTableProduct();
   createTableShoppingCart();
   createTableItemCart();
   createTableOrder();
   createTableItemOrder();
    }





  private void createTableUser()
  {
      String query = "CREATE TABLE IF NOT EXISTS users(\n" +
              "                id SERIAL PRIMARY KEY,\n" +
              "                role_id INTEGER,\n" +
              "                first_name VARCHAR(100),\n" +
              "                last_name VARCHAR(100),\n" +
              "                phone_number VARCHAR(20),\n" +
              "                address VARCHAR(200)," +
              "                user_name VARCHAR (50)," +
              "                password VARCHAR (50),\n" +
              "                FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE );";
      try {
          preparedStatement = connection.prepareStatement(query);
          preparedStatement.execute();
          preparedStatement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

    private void createTableRole(){
        String createTableRoleQuery = "CREATE TABLE IF NOT EXISTS role(\n" +
                "    id SERIAL PRIMARY KEY ,\n" +
                "    role_name VARCHAR (50)\n" +
                ");";
        try {
            preparedStatement = connection.prepareStatement(createTableRoleQuery);
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            System.out.println(e);
        }

    }
    private void insertIntoRole()
    {
        String insertQuery = "insert into role(role_name) values ('Administrator'),('customer');";
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            System.out.println(e);
        }
    }


    private void createTableCustomer()
    {

        String query = "CREATE TABLE IF NOT EXISTS customer(" +
                "id SERIAL PRIMARY KEY," +
                "user_id INTEGER," +
                "balance DOUBLE PRECISION," +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE );" +
                "";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   private void createTableCategory(){
        String query = " CREATE TABLE IF NOT EXISTS category(\n" +
                "                 id SERIAL PRIMARY KEY,\n" +
                "                 ctaegory_name VARCHAR (50),\n" +
                "                 category_id INTEGER ,\n" +
                "                 FOREIGN KEY (category_id) REFERENCES  category(id) ON DELETE CASCADE \n" +
                "                 );";


       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   private void createTableProduct(){
        String query = " CREATE TABLE IF NOT EXISTS product(\n" +
                "                  id SERIAL PRIMARY KEY,\n" +
                "                  product_name VARCHAR(70), \n" +
                "                  price DOUBLE PRECISION,\n" +
                "                  stock INTEGER ,\n" +
                "                  category_id INTEGER ,\n" +
                "                  FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE \n" +
                "                  );     ";


       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    private void createTableShoppingCart(){
        String query = " CREATE TABLE IF NOT EXISTS Shopping(\n" +
                "                  id SERIAL PRIMARY KEY,\n" +
                "                  customer_id INTEGER , \n" +
                "                  total DOUBLE PRECISION,\n" +
                "                  status INTEGER ,\n" +
                "                  FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE \n" +
                "                  );     ";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createTableItemCart(){
        String query = "CREATE TABLE IF NOT EXISTS itemcart(\n" +
                "                  id SERIAL PRIMARY KEY,\n" +
                "                  product_id INTEGER , \n" +
                "                  cart_id INTEGER ,\n" +
                "                  quantity INTEGER ,\n" +
                "                  sum DOUBLE PRECISION,\n" +
                "                  FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE, \n" +
                "                  FOREIGN KEY (cart_id) REFERENCES shopping(id) ON DELETE CASCADE \n" +
                "                  );     ";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   private void createTableOrder(){
        String query = "CREATE TABLE IF NOT EXISTS Orders(\n" +
                "                  id SERIAL PRIMARY KEY,\n" +
                "                  customer_id INTEGER , \n" +
                "                  total DOUBLE PRECISION,\n" +
                "                  status VARCHAR(50) ,\n" +
                "                  FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE \n" +
                "                  );     ";


       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    private void createTableItemOrder(){
        String query ="  CREATE TABLE IF NOT EXISTS itemorder(\n" +
                "                  id SERIAL PRIMARY KEY,\n" +
                "                  product_id INTEGER , \n" +
                "                  order_id INTEGER ,\n" +
                "                  quantity INTEGER ,\n" +
                "                  sum DOUBLE PRECISION,\n" +
                "                  FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE, \n" +
                "                  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE \n" +
                "                  );";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

