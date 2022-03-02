package utility;

public class ItemCartListNotFoundException extends RuntimeException{
    final private String message = "Item cart list not found";

    public ItemCartListNotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
