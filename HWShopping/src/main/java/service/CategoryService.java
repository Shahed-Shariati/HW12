package service;

import model.Category;
import repository.CategoryRepository;
import utility.CategoryListNotFound;
import utility.CategoryNotFound;
import utility.CategoryParentNotFound;

import java.sql.Connection;
import java.util.List;

public class CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryService(Connection connection){
        categoryRepository = new CategoryRepository(connection);

    }

    public int save(String id,String categoryName)
    {
        int idInt = Integer.parseInt(id);
        Category category = new Category(idInt,categoryName);

        return  categoryRepository.save(category);
    }
    public int save(String id,String categoryName,String parentId)
    {
        int parentIdInt = Integer.parseInt(parentId);
        Category parentCategory = categoryRepository.find(parentIdInt);
        if(parentCategory == null){
           throw new CategoryParentNotFound("Category parent not found");
        }
        Category category = new Category(parentIdInt,categoryName);
        return categoryRepository.save(category,parentIdInt);

    }
    public List<Category> findAll(){
       List<Category> categories =  categoryRepository.findAll();
       if(categories == null){
           throw new CategoryListNotFound("Category List not found");
       }
       return categories;
    }
    public List<Category> findSubCategory(){
        List<Category> categories =  categoryRepository.findSubCategory();
        if(categories == null){
            throw new CategoryListNotFound("Category List not found");
        }
        return categories;
    }
    public Category find(String id){

        int idInt = Integer.parseInt(id);
        Category category =   categoryRepository.find(idInt);
        if(category == null){
            throw new CategoryNotFound("not found");
        }
        return category;
    }
}
