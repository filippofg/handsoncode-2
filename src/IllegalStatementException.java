public class IllegalStatementException extends RuntimeException {

    public IllegalStatementException(Throwable t) {
        super(t);
    }

    public IllegalStatementException(String message) {
        super(message);
    }
}
