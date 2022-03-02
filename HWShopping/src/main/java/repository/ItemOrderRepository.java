package repository;

import model.ItemOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemOrderRepository implements Repository<ItemOrder>{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ItemOrderRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(ItemOrder itemOrder) {
        String query =
                        " INSERT INTO  itemorder (product_id,order_id,quantity,sum) VALUES (?,?,?,?) RETURNING id ";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,itemOrder.getProduct().getId());
            preparedStatement.setInt(2,itemOrder.getOrder().getId());
            preparedStatement.setInt(3,itemOrder.getQuantity());
            preparedStatement.setDouble(4,itemOrder.getSum());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void upDate(ItemOrder itemOrder) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ItemOrder find(int id) {
        return null;
    }

    @Override
    public List<ItemOrder> findAll() {
        return null;
    }
}
