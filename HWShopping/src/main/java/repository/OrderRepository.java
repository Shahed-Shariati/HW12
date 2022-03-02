package repository;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(Order order) {
        String query = " INSERT INTO orders (customer_id,total,status) VALUES (?,?,?) RETURNING id;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,order.getCustomer().getId());
            preparedStatement.setDouble(2,order.getSum());
            preparedStatement.setString(3,order.getStatus());
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
    public void upDate(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
