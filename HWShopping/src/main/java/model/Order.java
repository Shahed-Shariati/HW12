package model;

import java.util.ArrayList;

public class Order {
    private Integer id;
    private Customer customer;
    private Double sum;
    private ArrayList<ItemOrder> itemOrders;
    private String status;

    public Order(Integer id, Customer customer, Double sum, String status) {
        this.id = id;
        this.customer = customer;
        this.sum = sum;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public ArrayList<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(ArrayList<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
