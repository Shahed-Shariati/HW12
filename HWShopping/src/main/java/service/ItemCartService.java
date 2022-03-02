package service;

import model.ItemCart;
import repository.ItemCartRepository;
import utility.ItemCartListNotFoundException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemCartService implements Service<ItemCart>{

    private ItemCartRepository itemCartRepository;

    public ItemCartService(Connection connection) {
        this.itemCartRepository = new ItemCartRepository(connection);
    }

    public int save(ItemCart itemCart){
        return itemCartRepository.save(itemCart);
    }
    @Override
    public ItemCart find(int id) {
        return null;
    }

    @Override
    public List<ItemCart> findAll() {
        return null;
    }

    @Override
    public void upDate(ItemCart itemCart) {
      itemCartRepository.upDate(itemCart);
    }

    @Override
    public void delete(int id) {
        itemCartRepository.delete(id);
    }

    public ArrayList<ItemCart> findAllByShoppingCartId(int id){
        ArrayList<ItemCart> itemCarts = itemCartRepository.findAllByShoppingCartId(id);
        if(itemCarts == null || itemCarts.size() == 0){
            throw new ItemCartListNotFoundException();
        }
        return itemCarts;
    }

}
