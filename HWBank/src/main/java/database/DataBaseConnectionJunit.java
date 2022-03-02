package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnectionJunit {
    private Connection connection;
    private static DataBaseConnectionJunit instance;
    private static final String url = "jdbc:postgresql://localhost:5432/Junit";
    private static final String userName = "postgres";
    private static final String passWord = "123qweR";
    private static PreparedStatement preparedStatement;
    private DataBaseConnectionJunit() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,userName,passWord);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public static DataBaseConnectionJunit getInstance()
    {
        try {
            if(instance == null){
                return  instance = new DataBaseConnectionJunit();
            }else if(instance.getConnection().isClosed()) {
                return instance = new DataBaseConnectionJunit();
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
