package model;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name = "customer")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private double balance;
    @Transient
    private int userId;


    @Transient
    private ArrayList<ShoppingCart> shoppingCarts;
    public Customer(){}
    public Customer(int id, int role, String firstName, String lastName, String phoneNumber, String address, String userName, String passWord,double balance,int customerid) {
        super(id, role, firstName, lastName, phoneNumber, address, userName, passWord);
        this.balance = balance;
        this.id = customerid;
        this.shoppingCarts = new ArrayList<>();
    }



    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return this.balance;
    }

    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(ArrayList<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
