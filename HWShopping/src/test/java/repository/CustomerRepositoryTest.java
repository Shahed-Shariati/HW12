package repository;

import database.SessionFactoryJunit;
import model.Customer;
import model.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    private SessionFactory sessionFactory = SessionFactoryJunit.getSessionFactory();
    private CustomerRepository customerRepository;
 private Customer customer;
    @BeforeEach
    void initial(){

      customerRepository = new CustomerRepository();
      customer = new Customer();
      customer.setBalance(2600d);

    }
    @Test
    void connectionTest(){
        //Arrange
        //Act
        //Assert
        assertDoesNotThrow(() -> {
            SessionFactory sessionFactory1 = SessionFactoryJunit.getSessionFactory();
        });
    }

    @Test
    void add() {
        //Arrange



        //Act
        customerRepository.save(customer);

        //Assert
        assertNotNull(customerRepository.find(customer.getId()));
    }

    @Test
    void save() {

    }

    @Test
    void upDate() {
     //Arrange

        customerRepository.save(customer);
        customer.setBalance(62000d);
        //Act
        customerRepository.upDate(customer);

        //assert

        assertEquals(62000, customerRepository.find(customer.getId()).getBalance());
    }

    @Test
    void delete() {
        //Arrange

        customerRepository.save(customer);

        //Act
        customerRepository.delete(customer.getId());

        //Assert
        assertNull(customerRepository.find(customer.getId()));
    }

    @Test
    void find() {
        //Arrange

         customerRepository.save(customer);
        //Act
         Double balance = customerRepository.find(customer.getId()).getBalance() ;
        //Assert
        assertNotNull(customerRepository.find(customer.getId()));
        assertEquals(2600d,balance);
    }

    @Test
    void findAll() {
        //Arrange

        customerRepository.save(customer);
        customerRepository.save(customer);

        //Act
        List<Customer> customers = customerRepository.findAll();

        //Assert
        assertEquals(2,customers.size());

    }
    @AfterEach
    void truncate()
    {
        customerRepository.truncate();
      //  userRepository.truncate();
    }
}