package service;

import model.ShoppingCart;
import repository.ShoppingCartRepository;
import utility.ShoppingCartListNotFound;
import utility.ShoppingCartNotFound;

import java.sql.Connection;
import java.util.List;

public class ShoppingService implements Service<ShoppingCart>{
    private Connection connection;
    private ShoppingCartRepository shoppingCartRepository;
    private ItemCartService itemCartService;

    public ShoppingService(Connection connection) {
        this.connection = connection;
        this.shoppingCartRepository = new ShoppingCartRepository(connection);
        this.itemCartService = new ItemCartService(connection);
    }




    @Override
    public ShoppingCart find(int id) {
        ShoppingCart shoppingCart = shoppingCartRepository.find(id);
        if(shoppingCart == null || shoppingCart.getId() ==0){
            throw new ShoppingCartNotFound();
        }
        shoppingCart.setItemCarts(itemCartService.findAllByShoppingCartId(id));
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return null;
    }

    @Override
    public void upDate(ShoppingCart shoppingCart) {
      shoppingCartRepository.upDate(shoppingCart);
    }

    @Override
    public void delete(int id) {

    }

    public int save(ShoppingCart shoppingCart){
        return  shoppingCartRepository.save(shoppingCart);
    }
    public List<ShoppingCart> findByCustomerId(int id){
        List<ShoppingCart> shoppingCart = shoppingCartRepository.findByCustomerId(id);
        if(shoppingCart == null || shoppingCart.size() == 0)
        {
            throw new ShoppingCartListNotFound();
        }
        return shoppingCart;
    }

}
