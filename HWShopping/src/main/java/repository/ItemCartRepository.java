package repository;

import model.ItemCart;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCartRepository implements Repository<ItemCart>{
  private Connection connection;
  private PreparedStatement preparedStatement;

    public ItemCartRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(ItemCart itemCart) {
        String query = "INSERT INTO itemcart (product_id,cart_id,quantity,sum) VALUES (?,?,?,?)RETURNING id; ";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,itemCart.getProduct().getId());
            preparedStatement.setInt(2,itemCart.getShoppingCart().getId());
            preparedStatement.setInt(3,itemCart.getQuantity());
            preparedStatement.setDouble(4,itemCart.getSum());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void upDate(ItemCart itemCart) {
      String query = " UPDATE itemcart SET quantity = ?,sum = ? WHERE product_id = ? AND cart_id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,itemCart.getQuantity());
            preparedStatement.setDouble(2,itemCart.getSum());
            preparedStatement.setInt(3,itemCart.getProduct().getId());
            preparedStatement.setInt(4,itemCart.getShoppingCart().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
     String query = " DELETE FROM itemcart WHERE id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemCart find(int id) {
        return null;
    }

    @Override
    public List<ItemCart> findAll() {
        return null;
    }

    public ArrayList<ItemCart> findAllByShoppingCartId(int id){
        String query = "SELECT * FROM itemcart i INNER JOIN product p on p.id = i.product_id\n" +
                "               WHERE i.cart_id = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
             ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<ItemCart> itemCarts = new ArrayList<>();
            while (resultSet.next()){
                itemCarts.add(new ItemCart(resultSet.getInt(1),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("sum"),
                          new Product(resultSet.getInt(6),
                                resultSet.getString("product_name"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("stock"))));

            }
            return itemCarts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


