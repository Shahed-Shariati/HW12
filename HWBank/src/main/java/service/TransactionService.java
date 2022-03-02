package service;

import model.Account;
import model.Transaction;
import repository.TransactionRepository;
import utility.AccountNotFound;
import utility.MoneyNotEnough;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TransactionService {
    private TransactionRepository transactionRepository = new TransactionRepository();
    private AccountService accountService = new AccountService();
    LocalDate localDate = LocalDate.now();
    public void addTransaction(Transaction transaction){
        transactionRepository.addTransaction(transaction);
    }

      public void withdraw(double amount, String cardNumberSource,String cardNumberDestination) throws ParseException {
          Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          Account accountsource = accountService.findByCardNumber(cardNumberSource);
          Account accountDestination = accountService.findByCardNumber(cardNumberDestination);

          if(accountsource == null){
              throw new AccountNotFound("------------------Account not found-------------------");
          }else if (accountsource.getBalance() < amount){
               throw new MoneyNotEnough("------------------Money not enough--------------------");
          }else {
              Transaction transaction = new Transaction(accountsource,amount,"Pending","withdraw",today,accountDestination);
              accountsource.setBalance(accountsource.getBalance() - amount);
              accountService.upDate(accountsource);
              addTransaction(transaction);
          }
      }

      public void deposit(double amount, String cardNumberSource,String cardNumberDestination) throws ParseException {
          Date today = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          Account accountsource = accountService.findByCardNumber(cardNumberSource);
          Account accountDestination = accountService.findByCardNumber(cardNumberDestination);
          if(accountDestination == null){
              throw new AccountNotFound("------------------Account not found-------------------");
          }else {
              Transaction transaction = new Transaction(accountDestination,amount,"Pending","deposit",today,accountsource);
              accountDestination.setBalance(accountDestination.getBalance() + amount);
              accountService.upDate(accountDestination);
              addTransaction(transaction);
          }

      }

      public void findByAccountNumberAndDate(String accountNumber, String date)  {
          Date date1 = null;
          try {
              date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
          } catch (ParseException e) {
              System.out.println("Date is wrong");
          }
          java.sql.Date dateSql = new java.sql.Date(date1.getTime());
          List<Transaction> transactions =  transactionRepository.findByAccountNumberAndDate(accountNumber,dateSql);
        if (transactions == null){
            System.out.println("Transaction not found");
        }else {
            for (Transaction transaction:transactions) {
                System.out.println(transaction);

            }
        }

      }




}
