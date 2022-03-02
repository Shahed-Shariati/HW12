package utility;

public class Validation {
    public static boolean cardNumber(String cardNumber)
    {
        cardNumber = cardNumber.replace("-","");
        if(cardNumber.length() == 16){
            char[] chars = cardNumber.toCharArray();
            for (char ch:chars) {
                if(Character.isDigit(ch)){
                   }else {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public static String cardNumberReplace(String cardNumber){
        return cardNumber = cardNumber.replace("-","");
    }

    public static String amount(String str){
      return   str = str.replace(",","");
    }
}
