package repository;

import database.DatabaseConnection;
import model.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClerkRepository implements Repository{
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement;

    public int add(int id){
        String query = "INSERT INTO clerks (user_id,boss_id) VALUES (?,?) RETURNING id;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,1);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 return resultSet.getInt(1);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM clerks WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Clerk> showClerks(){
        String query =
                "SELECT * FROM clerks c INNER JOIN users u on u.id = c.user_id";
        try {
            List<Clerk> clerks = new ArrayList<>();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()){
                clerks.add(new Clerk(resultSet.getInt(2),
                                    resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getInt(1)
                        ));
            }
            return clerks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
