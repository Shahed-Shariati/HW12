package service;

import database.SessionFactorySingleton;
import model.Account;
import model.CreditCard;
import repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository(SessionFactorySingleton.getSessionFactory());
   private CreditCardService creditCardService = new CreditCardService();


    public List<Account> showCustomerAccount(String id){
        int customerID = Integer.parseInt(id);
        return accountRepository.showCustomerAccount(customerID);


    }
    public int add(String accountNumber,String balance)
    {
            Account account = new Account(accountNumber,Double.parseDouble(balance));
            account = accountRepository.add(account);
            return account.getId();
       }
    public void upDate(Account account){
        accountRepository.upDate(account);
    }
    public void delete(Integer id){
        accountRepository.delete(id);
    }
    public Account findById(Integer id){
        return accountRepository.findById(id);
    }

    public void setCreditCardId(int creditCardId,int accountId){
        accountRepository.setCreditCardId(creditCardId,accountId);
    }

    public List<Account> findByCustomerIdList(int id){
        List<Account> accounts = accountRepository.findByCustomerIdList(id);
        List<Account> accountList = new ArrayList<>();
        for (Account account:accounts) {
            CreditCard creditCard = creditCardService.findByAccountId(account.getId());
            account.setCreditCard(creditCard);
            accountList.add(account);
        }
        if(accountList.isEmpty()){
            System.out.println(" ------------------List is empty------------------------");
        }else {
            return accountList;
        }
        return null;
    }
    public Account findByCustomerId(int id){

          Account account = accountRepository.findByCustomerId(id);
          if(account != null) {
              CreditCard creditCard = creditCardService.findByAccountId(account.getId());
              account.setCreditCard(creditCard);
              return account;
          }else {
              System.out.println("Account not found");
          }
          return null;
    }
    public Account findByCardNumber(String cardNumber){
        return accountRepository.findByCardNumber(cardNumber);
    }

}
