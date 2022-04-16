public class IllegalStatementException extends RuntimeException {

    public IllegalStatementException(Throwable t) {
        super(t);
    }

    public IllegalStatementException(String message) {
        super(message);
    }

    public IllegalStatementException() {
       super("illegal expression order! Valid sequences are:\n" +
                "\t [NOT] String/Numeric\n" +
                "\t [NOT] String/Numeric AND/OR [NOT] String/Numeric AND/OR [NOT] ..."
       );
    }
}
