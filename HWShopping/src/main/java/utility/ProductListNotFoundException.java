package utility;

public class ProductListNotFoundException extends RuntimeException{
    final private String message = "List product not found";
    public ProductListNotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
