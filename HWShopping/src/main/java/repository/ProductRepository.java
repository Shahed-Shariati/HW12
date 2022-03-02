package repository;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product>{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(Product product) {
        String query = "INSERT INTO product (product_name,price,stock,category_id)\n" +
                "                VALUES (?,?,?,?) RETURNING id;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getStock());
            preparedStatement.setInt(4,product.getCategory().getId());
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
    public void upDate(Product product) {
        String query = " UPDATE product set product_name = ?,\n" +
                "                price = ?,\n" +
                "                stock = ?\n" +
                "                WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getStock());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product p INNER JOIN category c on c.id = p.category_id\n" +
                "                WHERE p.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        new Category(resultSet.getInt(6),
                                resultSet.getString(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM product p INNER JOIN category c on c.id = p.category_id ;";

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                products.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        new Category(resultSet.getInt(6),
                                resultSet.getString(7))));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Product> findAllByCategoryId(int id){
        String query = " SELECT * FROM product p INNER JOIN category c on c.id = p.category_id WHERE  c.category_id = ? ; ";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                products.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        new Category(resultSet.getInt(6),
                                resultSet.getString(7))));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
