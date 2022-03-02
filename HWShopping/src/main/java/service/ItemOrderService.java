package service;

import model.ItemOrder;
import model.Order;
import repository.ItemOrderRepository;

import java.sql.Connection;
import java.util.List;

public class ItemOrderService implements Service<Order> {
    private Connection connection;
    private ItemOrderRepository itemOrderRepository;

    public ItemOrderService(Connection connection) {
        this.connection = connection;
       this.itemOrderRepository = new ItemOrderRepository(this.connection);
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void upDate(Order order) {

    }

    @Override
    public void delete(int id) {

    }
    public int save(ItemOrder itemOrder){
        return itemOrderRepository.save(itemOrder);
    }

}
