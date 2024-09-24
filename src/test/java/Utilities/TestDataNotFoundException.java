package Utilities;

public class TestDataNotFoundException extends RuntimeException {
    public TestDataNotFoundException(String message) {
        super(message);
    }

    public TestDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

