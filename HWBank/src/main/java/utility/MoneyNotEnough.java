package utility;

public class MoneyNotEnough extends RuntimeException{
    public MoneyNotEnough(String message) {
        super(message);
    }
}
