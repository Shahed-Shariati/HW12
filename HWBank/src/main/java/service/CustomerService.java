package service;

import model.Account;
import model.Customer;
import repository.CustomerRepository;
import utility.ValidationLengthNationalCode;

import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();
    private AccountService accountService = new AccountService();
    public void showCustomerAccount(String id){
          List<Account> accounts = accountService.showCustomerAccount(id);
        if(accounts.size() != 0){
            for (Account item:accounts) {
                System.out.println(item);
            }
        }else {
            System.out.println("---------------------------List is Empty--------------------");

        }
    }
   public void showCustomers() {
       try {
           List<Customer> customers = customerRepository.showCustomer();
           if (customers.size() != 0) {
               for (Customer customer : customers) {
                   System.out.println(customer);
               }
           } else {
               System.out.println("List Empty");
           }
       }catch (ValidationLengthNationalCode e){
           System.out.println("National code length is not valid ");
       }
   }



    public Customer findById(int id)
    {
        Customer customer = customerRepository.findById(id);
        List<Account> accounts = accountService.findByCustomerIdList(customer.getId());
        customer.setAccounts(accounts);
        return customer;
       /* Customer customer = customerRepository.findById(id);
        Account account = accountService.findByCustomerId(customer.getId());
        customer.setAccount(account);
        return customer;*/
    }

    public int add(int userId)
    {
       return customerRepository.add(userId);
    }
}
