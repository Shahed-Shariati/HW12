package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    private static Connection connection = DatabaseConnection.getInstance().getConnection();
    private static PreparedStatement preparedStatement;
    public CreateTable(){
   createTableRole();
  //insertIntoRole();
  createTableUser();
   createTableBoss();
   createTableClerk();
   createTableBank();
   createTableBankBranch();
  createTableCreditCard();
  createTableType();
   createTableAccount();
   createTableCustomer();
   createBranchCustomer();
   createTableAccountCustomer();
   createTableTransaction();

    }


  private void createTableTransaction()
  {
      String createStatement = "CREATE TABLE IF NOT EXISTS transactions (" +
              "id SERIAL    PRIMARY KEY," +
              "accountId     INTEGER ," +
              "amount       DOUBLE PRECISION ," +
              "status       varchar(10)," +
              "types        varchar(10)," +
              "transaction_date date ," +
              "account_destination INTEGER ," +
              "FOREIGN KEY (account_destination) REFERENCES account (id), " +
              "FOREIGN KEY (accountId) REFERENCES account (id));";
      try {
          PreparedStatement transactionPrepared = connection.prepareStatement(createStatement);
          transactionPrepared.execute();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }


  private void createTableUser()
  {
      String query = "CREATE TABLE IF NOT EXISTS users(\n" +
              "                id SERIAL PRIMARY KEY,\n" +
              "                role_id INTEGER,\n" +
              "                first_name VARCHAR(100),\n" +
              "                last_name VARCHAR(100),\n" +
              "                national_code VARCHAR(10) UNIQUE,\n" +
              "                phone_number VARCHAR(20),\n" +
              "                address VARCHAR(200)," +
              "                 user_name VARCHAR (50)," +
              "                  password VARCHAR (50),\n" +
              "                FOREIGN KEY (role_id) REFERENCES role(id) );";
      try {
          preparedStatement = connection.prepareStatement(query);
          preparedStatement.execute();
          preparedStatement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }


    private void insertIntoUser()
    {
        String query = "insert into users( role_id, first_name, last_name, user_name, password,isactive) values (1,'admin','admin','admin','admin',1);";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("userinsert");
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private void insertIntoRole()
    {
        String insertQuery = "insert into role(role_name) values ('boss'),('customer'),('clerk');";
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            System.out.println(e);
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

    private void createTableBoss()
    {
        String query = "CREATE TABLE IF NOT EXISTS boss(" +
                "id SERIAL PRIMARY KEY," +
                "user_id INTEGER," +
                "FOREIGN KEY (user_id) REFERENCES users(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createTableClerk()
    {
        String query = "CREATE TABLE IF NOT EXISTS clerks(" +
                "id SERIAL PRIMARY KEY," +
                "user_id INTEGER," +
                "boss_id INTEGER ," +
                "FOREIGN KEY (boss_id) REFERENCES boss(id),"+
                "FOREIGN KEY (user_id) REFERENCES users(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createTableBank()
    {
        String query = "CREATE TABLE IF NOT EXISTS bank(" +
                "id SERIAL PRIMARY KEY," +
                "name INTEGER," +
                "address VARCHAR(250));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createTableBankBranch()
    {
        String query = "CREATE TABLE IF NOT EXISTS bankbranch(" +
                "id SERIAL PRIMARY KEY," +
                "bank_id INTEGER," +
                "branchcode INTEGER);";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCreditCard()
    {
        String query = "CREATE TABLE IF NOT EXISTS creditcard(" +
                "id SERIAL PRIMARY KEY," +
                "number VARCHAR(16) UNIQUE ," +
                "expirDate DATE ," +
                "cvv INTEGER," +
                "password VARCHAR(4)," +
                "password2 VARCHAR (6)," +
                "isactive INTEGER ," +
                "failed_password INTEGER );";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableAccount()
    {
        String query = "CREATE TABLE IF NOT EXISTS account(" +
                "id SERIAL PRIMARY KEY," +
                "account_number VARCHAR UNIQUE ," +
                "balance DOUBLE PRECISION," +
                "credit_card_id INTEGER," +
                "type_id INTEGER ," +
                "FOREIGN KEY (credit_card_id) REFERENCES creditcard(id)," +
                "FOREIGN KEY (type_id) REFERENCES types(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableType()
    {
        String query = "CREATE TABLE IF NOT EXISTS types(" +
                "id SERIAL PRIMARY KEY," +
                "type VARCHAR " +
                ");";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCustomer()
    {

        String query = "CREATE TABLE IF NOT EXISTS customer(" +
                "id SERIAL PRIMARY KEY," +
                "user_id INTEGER," +
                "account_id INTEGER, " +

                "FOREIGN KEY (user_id) REFERENCES users(id)," +
                "FOREIGN KEY (account_id) REFERENCES account(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createBranchCustomer()
    {
        String query = "CREATE TABLE IF NOT EXISTS branchcustomer(" +
                "id SERIAL PRIMARY KEY," +
                "branch_id INTEGER," +
                "customer_id INTEGER ," +
                "FOREIGN KEY (branch_id) REFERENCES bankbranch(id)," +
                "FOREIGN KEY (customer_id) REFERENCES customer(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableAccountCustomer()
    {
        String query = "CREATE TABLE IF NOT EXISTS accoutncustoer(" +
                "id SERIAL PRIMARY KEY," +
                "account_id INTEGER," +
                "customer_id INTEGER ," +
                "FOREIGN KEY (account_id) REFERENCES account(id)," +
                "FOREIGN KEY (customer_id) REFERENCES customer(id));";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
