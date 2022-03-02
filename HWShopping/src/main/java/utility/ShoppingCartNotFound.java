package utility;

public class ShoppingCartNotFound extends RuntimeException{
    final private String message = "Shopping cart not found";
    public ShoppingCartNotFound() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
