package utility;

public class ProductNotFoundException extends RuntimeException{
    final private String message = "Product not found";
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
