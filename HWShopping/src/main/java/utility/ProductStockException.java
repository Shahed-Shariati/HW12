package utility;

public class ProductStockException extends RuntimeException{
    final private String message = "product stock not  ";
    public ProductStockException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
