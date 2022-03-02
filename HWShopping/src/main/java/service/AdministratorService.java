package service;

import model.Administrator;

import java.sql.Connection;
import java.util.List;

public class AdministratorService implements Service<Administrator>{
    private CategoryService categoryService;

    public AdministratorService(Connection connection)
    {
        this.categoryService = new CategoryService(connection);

    }

    public int saveCategoryParent(String id, String categoryName){
        return categoryService.save(id,categoryName);
    }

    public int saveSubCategory(String id,String categoryName,String parentId){
        return categoryService.save(id,categoryName,parentId);

    }

    @Override
    public Administrator find(int id) {
        return null;
    }

    @Override
    public List<Administrator> findAll() {
        return null;
    }

    @Override
    public void upDate(Administrator administrator) {

    }

    @Override
    public void delete(int id) {

    }
}
