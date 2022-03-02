package utility;

public class UserNotFoundException extends RuntimeException {
   final private String message = "User not found";
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
