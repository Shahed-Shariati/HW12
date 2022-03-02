package model;

import java.util.ArrayList;

public class ShoppingCart  {
    private Integer id;
    private Customer customer;
    private Double sum;
    private ArrayList<ItemCart> itemCarts;
    private Integer status;

    public ShoppingCart(Integer id, Customer customer, double sum,Integer status) {
        this.id = id;
        this.customer = customer;
        this.sum = sum;
        this.itemCarts = new ArrayList<>();
        this.status = status;
    }

    public ShoppingCart(Integer id, Double sum,Integer status) {
        this.id = id;
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

    public ArrayList<ItemCart> getItemCarts() {
        return itemCarts;
    }

    public void setItemCarts(ArrayList<ItemCart> itemCarts) {
        this.itemCarts = itemCarts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", sum=" + sum +
                '}';
    }
}
