package utility;

import model.CreditCard;
import model.Customer;
import model.User;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RunApplication {
    private UserService userService = new UserService();
    private TransactionService transactionService = new TransactionService();
    private  ApplicationService applicationService = new ApplicationService();
    private AccountService accountService = new AccountService();
    private AccountCustomerService accountCustomerService = new AccountCustomerService();
    private CreditCardService creditCardService = new CreditCardService();
    private CustomerService customerService = new CustomerService();
    private ClerkService clerkService = new ClerkService();
    private Scanner input = new Scanner(System.in);

  public void runApplication() throws ParseException {

    while (true){
       Menu.loginMenu();
       System.out.println("Choice");
       String input = getUserInput();
       if(input.equals("1")){
         login();
       }else if(input.equals("2"))
       {

       }else if(input.equals("3")){
           break;
       }else {
           System.out.println();
       }
    }
}

private void singUp(){
    System.out.println("_________________________Sing up---------------------------");
    System.out.println("1:Boss");
    System.out.println("2:Customer");
    System.out.println("3:Clerk");
    System.out.println("Choice your Role");
    String role = getUserInput();
    System.out.println("Enter your name");
    String firstName = getUserInput();
    System.out.println("Enter your Last Name");
    String LastName = getUserInput();
    System.out.println("enter your national code");
    String nationalCode = getUserInput();
    System.out.println("enter your phone number");
    String phoneNumber = getUserInput();
    System.out.println("enter your address");
    String address = getUserInput();
    System.out.println("Enter your username: ");
    String username = getUserInput();
    System.out.println("Enter your password: ");
    String password = getUserInput();


}

private void login() throws ParseException {
    System.out.println("Enter user name and password (admin admin)");
    String[] input = getUserInput().trim().split(" +");
    if(input[0].equalsIgnoreCase("back")){
        System.out.println();
    }else if(input.length == 2){
        User user = applicationService.login(input[0], input[1]);
        if(user == null){
            System.out.println("Your user name or password is wrong");
        }else if(user.getRole() == 1){
            bossMenu();
        }else if(user.getRole() == 2){
           Customer customer = findCustomer(user.getId());
           customerMenu(customer);
        }else if(user.getRole() == 3){
           clerkMenu();
        }

    }else {
        System.out.println();
    }
}

    private void customerMenu(Customer customer) throws ParseException {

        while (true){
            Menu.customerMenu();
            String input = getUserInput();
            if(input.equals("1")){
                customerService.showCustomerAccount(String.valueOf (customer.getId()));
            }else if(input.equals("2")) {

                    showCustomerCreditCard(customer);
                    transaction(customer.getId(), customer);

            }else if(input.equals("3")){
                showCustomerCreditCard(customer);
                changePassword();
            }else if(input.equals("4")) {
                customerService.showCustomerAccount(String.valueOf(customer.getId()));
                findTransaction();
            }else if(input.equals("5")){
                break;
            }else {
                System.out.println("Wrong choice");
            }
        }

    }

    private void clerkMenu(){
    while (true) {
        Menu.clerkMenu();
        System.out.println("Choice ");
        String input = getUserInput();
        if(input.equals("1")){
          int customerId = addUser("2");
         //   System.out.println("------------------------create Account----------------------------------");
          int accountId = addAccount();

              addAccountCustomer(accountId, customerId);
              int creditCardId = addCreditCard();
              setCreditCardId(creditCardId,accountId);
              System.out.println("--------------------------create credit card------------------------------");
              System.out.println("------------------------------success--------------------------------------");

        }else if(input.equals("2")){
            showCustomer();
            System.out.println("Choice customer id\nEnter Back to return menu:");

            String customerId = getUserInput();
            if(customerId.equalsIgnoreCase("back")){
                System.out.println();
            }else {
                try {
                    int accountId = addAccount();
                    int customerID = Integer.parseInt(customerId);
                    if (accountId != 0 && accountId != 0) {
                        addAccountCustomer(accountId, customerID);
                        int creditCardId = addCreditCard();
                        setCreditCardId(creditCardId, accountId);
                    }
                }catch (NumberFormatException e){
                    System.out.println("Customer id is wrong");
                }

            }
        }else if(input.equals("3")){
            showCustomerAccount();
            findTransaction();

        }else if(input.equals("4")){
             showCustomer();
        }else if(input.equals("5")){
            break;
        }else  {
            System.out.println("Wrong choice");
        }
    }
    }
    private void bossMenu(){
    while (true){
        Menu.bossMenu();
        System.out.println("Choice ");
        String input = getUserInput();
        if(input.equals("1")){
             int id = addUser("3");
        }else if (input.equals("2")){
             showClerks();
            System.out.println("Choice id to delete clerk \n Enter back to return menu");
            String idClerk = getUserInput();
            if(idClerk.equalsIgnoreCase("back")){
                System.out.println();
            }else {
                deleteClerk(idClerk);
            }
        }else if(input.equals("3")){
            break;
        }else {
            System.out.println();
        }
    }
    }

    private void findTransaction(){
        System.out.println("Enter account number:");
        String accountNumber = getUserInput();
        System.out.println("Enter date:");
        String date = getUserInput();
        transactionService.findByAccountNumberAndDate(accountNumber,date);
    }
   private void showCustomerCreditCard(Customer customer){
       List<CreditCard> creditCards = creditCardService.findByCustomerId(customer.getId());
       for (CreditCard credit:creditCards  ) {
           System.out.println(credit);
       }
   }
   private void showClerks(){
    clerkService.showClerks();
   }
   private void changePassword()
   {

       CreditCard creditCard;
       System.out.println("Choice Credit Card\n Example(1111-1111-1111-1111)");
       String creditCardNumber = getUserInput();
       if(Validation.cardNumber(creditCardNumber)){
           creditCardNumber = Validation.cardNumberReplace(creditCardNumber);
           creditCard = creditCardService.findByCreditCardNumber(creditCardNumber);
           if(creditCard.getFailed() < 3) {
               System.out.println("1:Change Password");
               System.out.println("2:Change Password Online");
               String input = getUserInput();
               if (input.equals("1")) {
                   System.out.println("Enter your old password");
                   String oldPassword = getUserInput();
                   System.out.println("Enter your new password:(max 4 )");
                   String newPassword = getUserInput();
                   if (creditCard.getPassword().equals(oldPassword)) {
                       creditCard.setPassword(newPassword);
                       setNewPassword(creditCard);
                   } else {
                       creditCardService.failedPassword(creditCard);
                       System.out.println("--------------------Your old password is Wrong-----------------------");
                   }
               } else if (input.equals("2")) {
                   System.out.println("Enter your  password");
                   String oldPassword = getUserInput();
                   System.out.println("Enter your new password online:");
                   String newPassword = getUserInput();
                   if (creditCard.getPassword().equals(oldPassword)) {
                       creditCard.setPasswordOnline(newPassword);
                       setNewPassword(creditCard);
                   } else {
                       creditCardService.failedPassword(creditCard);
                       System.out.println("--------------------Your password is Wrong-----------------------");
                   }
               } else {
                   System.out.println("your Choice is wrong");
               }
           }else {
               System.out.println("-----Your card is not active----------------");
           }

       }else {
           System.out.println("your card number is wrong");
       }
   //    System.out.println("your card number is wrong");
   }

    private void setNewPassword(CreditCard creditCard){
       creditCardService.upDate(creditCard);
    }
    private int addCreditCard()
    {
        while (true) {
        System.out.println("Enter card number: Example(1111-2222-3333-4444)");
        String numberCard = getUserInput();
        System.out.println("Enter expire date:");
        String expireDate = getUserInput();
        System.out.println("Enter cvv");
        String cvv = getUserInput();
        System.out.println("Enter password card");
        String password = getUserInput();
        System.out.println("Enter online Password");
        String passwordOnline = getUserInput();
        if (Validation.cardNumber(numberCard)) {
            numberCard = Validation.cardNumberReplace(numberCard);
            return creditCardService.add(numberCard, expireDate, cvv, password, passwordOnline);
        }else {
            System.out.println("cardNumber is not valid ");
        }
      }
    }


    private int addAccount() {
        System.out.println("Enter Account number:");
        String accountNumber = getUserInput();
        System.out.println("Enter  amount ");
        String balance = getUserInput();
        balance = Validation.amount(balance);
        try {
            return accountService.add(accountNumber, balance);
        } catch (NumberFormatException e) {
            System.out.println("amount is wrong");
        } catch (ValidationDigitAccountNumber e) {
            System.out.println("Account number is wrong");
        }
      return 0;
    }
    private void setCreditCardId(int creditCardId,int accountId)
    {
        accountService.setCreditCardId(creditCardId,accountId);
    }
    private void showCustomerAccount(){
        showCustomer();
        System.out.println("Choice customer id");
        String id = getUserInput();
        customerService.showCustomerAccount(id);
    }
    private void showCustomer(){
       customerService.showCustomers();
    }
    private void addAccountCustomer(int accountId,int customerId)
     {
         accountCustomerService.add(accountId,customerId);
     }

    private void deleteClerk(String id){
     clerkService.delete(id);
    }
    private int addUser(String roleId)
    {
        System.out.println("Enter  name");
        String firstName = getUserInput();
        System.out.println("Enter  Last Name");
        String lastName = getUserInput();
        System.out.println("Enter  national code");
        String nationalCode = getUserInput();
        System.out.println("Enter  phone number");
        String phoneNumber = getUserInput();
        System.out.println("enter  address");
        String address = getUserInput();
        System.out.println("Enter  username: ");
        String username = getUserInput();
        System.out.println("Enter  password: ");
        String password = getUserInput();
        return userService.add(firstName,lastName,nationalCode,phoneNumber,address,username,password,roleId);

    }


    public void transaction(int id, Customer customer) throws ParseException {

        CreditCard creditCardSource;
        CreditCard creditCardDestination;
        while (true)
        {
            System.out.println("Enter back to return menu");
            System.out.println("Choice your card number");

            String cardNumberSource = getUserInput();
            if(cardNumberSource.equalsIgnoreCase("back"))
            {
                break;
            }
            if(Validation.cardNumber(cardNumberSource)) {
                cardNumberSource = Validation.cardNumberReplace(cardNumberSource);
                creditCardSource = creditCardService.findByCreditCardNumber(cardNumberSource);
            if (creditCardSource.getFailed() < 3) {
                System.out.println("enter your cvv");
                String cvv = getUserInput();
                System.out.println("enter your expire date");
                String expireDate = getUserInput();
                System.out.println("Enter amount:");
                String amount = getUserInput();
                System.out.println("Enter your password online");
                String passwordOnline = getUserInput();
                System.out.println("Enter Destinaction card number");
                String cardNumberDestination = getUserInput();
                try {

                CreditCard cardCheck = new CreditCard(cardNumberSource, expireDate, Integer.parseInt(cvv), "1223", passwordOnline);
                if (Validation.cardNumber(cardNumberDestination)) {
                    cardNumberSource = Validation.cardNumberReplace(cardNumberSource);
                    creditCardSource = creditCardService.findByCreditCardNumber(cardNumberSource);
                    creditCardDestination = creditCardService.findByCreditCardNumber(cardNumberDestination);
                    if (creditCardSource != null && creditCardDestination != null) {
                        if (checkCards(creditCardSource, cardCheck)) {
                            try {


                                transactionService.deposit(Double.parseDouble(amount), cardNumberSource, cardNumberDestination);
                                transactionService.withdraw(Double.parseDouble(amount), cardNumberSource, cardNumberDestination);
                                System.out.println("-------------------------------------------------------------------------");
                                System.out.println("------------------------Transaction success-------------------------------");
                                System.out.println("-------------------------------------------------------------------------");
                            } catch (MoneyNotEnough e) {
                                System.out.println(" Money not enough");
                            } catch (AccountNotFound e) {
                                System.out.println("----------------------Account not found-------------------------ccqqq12");
                            } catch (NumberFormatException e) {
                                System.out.println("amount is not valid");
                            } catch (ValidationPassword e){
                                System.out.println("Password is wrong");
                            }
                        }else {
                            System.out.println("Error");
                        }
                    }
                } else {
                    System.out.println("---Card Not validate----");
                }
            }catch (NumberFormatException e){
                    System.out.println("cvv not valid");
                }
            }else {
                System.out.println("--- Your card is not active------");
            }
            }else{

                System.out.println("----Card Not validate------");
            }


        }



    }

    private Boolean checkCards(CreditCard creditCard, CreditCard cardCheck){
        LocalDate localDate = LocalDate.now();
        try {
            Date today = new SimpleDateFormat("yyyy-MM").parse(localDate.toString());
            Date date1=new SimpleDateFormat("yyyy-MM").parse(creditCard.getExpireDate());
            if(creditCard.getPasswordOnline().equals(cardCheck.getPasswordOnline())) {
                if (creditCard.getCvv() == cardCheck.getCvv() && today.before(date1) && creditCard.getExpireDate().equals(cardCheck.getExpireDate())) {
                    return true;
                }
            }else {
                creditCardService.failedPassword(creditCard);
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    private String getUserInput()
    {
        return input.nextLine().trim();
    }
    private Customer findCustomer(int id)
    {
        return customerService.findById(id);

    }
}
