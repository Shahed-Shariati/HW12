package service;

import model.User;
import utility.ValidationDigitNationalCode;
import utility.ValidationDigitPhoneNumber;
import utility.ValidationLengthNationalCode;
import utility.ValidationLengthPhoneNumber;

public class ApplicationService {
    private UserService userService = new UserService();

    public User login(String userName, String password){
        User user;
        try {
            user = userService.login(userName, password);
            return user;
        }catch (ValidationLengthNationalCode e){
            System.out.println("Length is wrong national code");
        }catch (ValidationDigitNationalCode e)
        {
            System.out.println("National code contain String");
        }catch (ValidationLengthPhoneNumber e)
        {
            System.out.println("phone number length is wrong");
        }catch (ValidationDigitPhoneNumber e){
            System.out.println("phone number is contain string");
        }
        return null;
    }
}
