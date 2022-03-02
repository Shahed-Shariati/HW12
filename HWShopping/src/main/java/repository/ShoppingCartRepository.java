package repository;

import model.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository implements Repository<ShoppingCart>{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ShoppingCartRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping (customer_id,total,status) VALUES (?,?,?) RETURNING id;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,shoppingCart.getCustomer().getId());
            preparedStatement.setDouble(2,shoppingCart.getSum());
            preparedStatement.setInt(3,shoppingCart.getStatus());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void upDate(ShoppingCart shoppingCart) {
     String query = "UPDATE shopping SET  total = ?,status = ? WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1,shoppingCart.getSum());
            preparedStatement.setInt(2,shoppingCart.getStatus());
            preparedStatement.setInt(3,shoppingCart.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ShoppingCart find(int id) {
        String query = "SELECT * FROM shopping WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new ShoppingCart(resultSet.getInt("id"),
                           resultSet.getDouble("total"),
                        resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return null;
    }

    public List<ShoppingCart>  findByCustomerId(int id){
        String query = "SELECT * FROM shopping WHERE customer_id = ? AND status = 1 ;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            while (resultSet.next()){
                shoppingCarts.add(new ShoppingCart(resultSet.getInt("id"),
                                  resultSet.getDouble("total"),
                                  resultSet.getInt("status")));
            }
            return shoppingCarts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
