package service;

import model.Order;
import repository.OrderRepository;

import java.sql.Connection;
import java.util.List;

public class OrderService implements Service<Order> {
    private Connection connection;
    private OrderRepository orderRepository;

    public OrderService(Connection connection) {
        this.connection = connection;
        orderRepository = new OrderRepository(this.connection);
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
    public int save(Order order){
      return  orderRepository.save(order);

    }
}
