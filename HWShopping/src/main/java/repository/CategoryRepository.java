package repository;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category>{
    private Connection connection;
    private PreparedStatement preparedStatement;
    public CategoryRepository(Connection connection){
        this.connection = connection;

    }


    @Override
    public int save(Category category) {
        String query = "INSERT INTO category (ctaegory_name)\n" +
                "    VALUES (?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,category.getCategoryName());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int save(Category category,int id) {
        String query ="INSERT INTO category (ctaegory_name,category_id)\n" +
                "                VALUES (?,?) RETURNING id;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,category.getCategoryName());
            preparedStatement.setInt(2,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public void upDate(Category category) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Category find(int id) {
        String query =" SELECT * FROM category\n" +
                "                WHERE id = ?;";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
             if(resultSet.next()){
                 return new Category(resultSet.getInt(1),resultSet.getString(2));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Category> findAll() {
        String query = " SELECT * FROM category\n" +
                "                WHERE category_id is  null ;";

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()){
                categories.add(new Category(resultSet.getInt(1),
                        resultSet.getString(2)));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Category> findSubCategory(){
        String query = "SELECT * FROM category\n" +
                "                WHERE category_id is NOT NULL ;";

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()){
                categories.add(new Category(resultSet.getInt(1),
                        resultSet.getString(2)));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
