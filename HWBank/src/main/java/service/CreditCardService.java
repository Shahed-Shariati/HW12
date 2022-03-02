package service;

import model.CreditCard;
import repository.CreditCardRepository;
import utility.ValidationPassword;

import java.util.List;

public class CreditCardService {
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    public int add(String numberCard,String expirDate,String cvv,String password,String passwordOnline)
    {

         CreditCard creditCard;
        try {
            int cvvInt = Integer.parseInt(cvv);
            creditCard = new CreditCard(numberCard,expirDate,cvvInt,password,passwordOnline);
            return creditCardRepository.add(creditCard);
        }catch (ValidationPassword e){
            System.out.println("password is contain string");
        }catch (NumberFormatException e){
            System.out.println("cvv contain string");
        }
         return 0;
    }
    public CreditCard findByCreditCardNumber(String cardNumber)
    {
        return creditCardRepository.findByCardNumber(cardNumber);
    }
    public CreditCard findByAccountId(int id){
        CreditCard creditCard = creditCardRepository.findByAccountId(id);
        if(creditCard != null){
            return creditCard;
        }else {
            System.out.println("-------------------------- Not Found---------------------------");
        }
        return null;

    }
    public List<CreditCard> findByCustomerId(int id){

        return creditCardRepository.findByCustomerId(id);
    }
  public void upDate(CreditCard creditCard)
  {
      creditCardRepository.upDate(creditCard);
  }
  public void failedPassword(CreditCard creditCard)
  {
      creditCardRepository.failedPassword(creditCard);
  }
}
