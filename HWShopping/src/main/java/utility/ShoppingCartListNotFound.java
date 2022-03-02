package utility;

public class ShoppingCartListNotFound extends RuntimeException{
    final private String message = "Shopping Cart not found";
    public ShoppingCartListNotFound() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
