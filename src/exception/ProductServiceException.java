package exception;

public class ProductServiceException extends RuntimeException {
    public ProductServiceException(Throwable cause) {
        super(cause);
    }

    public ProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductServiceException(String message) {
        super(message);
    }
}
