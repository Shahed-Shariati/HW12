package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseJunit {
    private Connection connection;
    private static DataBaseJunit instance;
    private static final String url = "jdbc:postgresql://localhost:5432/shopjunit";
    private static final String userName = "postgres";
    private static final String passWord = "123qweR";
    private static PreparedStatement preparedStatement;
    private DataBaseJunit() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,userName,passWord);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public static DataBaseJunit getInstance()
    {
        try {
            if(instance == null){
                return  instance = new DataBaseJunit();
            }else if(instance.getConnection().isClosed()) {
                return instance = new DataBaseJunit();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return instance;
    }

    public  Connection getConnection() {
        return connection;
    }
}
