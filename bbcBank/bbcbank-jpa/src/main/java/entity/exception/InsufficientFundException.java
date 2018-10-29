package entity.exception;

public class InsufficientFundException extends Exception {
    private static final long serialVersionUID = 5990046571261191945L;

    public InsufficientFundException(String description) {
        super(description);
    }
}
