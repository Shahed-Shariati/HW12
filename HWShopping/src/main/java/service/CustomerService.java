package service;

import model.Customer;
import repository.CustomerRepository;
import utility.UserNotFoundException;

import java.sql.Connection;
import java.util.List;

public class CustomerService implements Service<Customer> {
    private Connection connection;
   private CustomerRepository customerRepository;


    public CustomerService(Connection connection) {
        this.connection = connection;
        customerRepository = new CustomerRepository(connection);
    }

    public int add(int userId,String balance)

    {

        double balance1 = Double.parseDouble(balance);
        return customerRepository.add(userId,balance1).getId();



    }
    public Customer findByUserId(int id){
            Customer customer =   customerRepository.findByUserId(id);
            if(customer == null){
                throw new UserNotFoundException();
            }
        return customer;
    }

    @Override
    public Customer find(int id) {

        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void upDate(Customer customer) {
       customerRepository.upDate(customer);
    }

    @Override
    public void delete(int id) {

    }
}
