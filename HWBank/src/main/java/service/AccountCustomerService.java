package service;

import repository.AccountCustomerRepository;

public class AccountCustomerService {
    private AccountCustomerRepository accountCustomerRepository = new AccountCustomerRepository();

    public void add(int accountId,int customerId)
    {
        accountCustomerRepository.add(accountId,customerId);
    }
}
