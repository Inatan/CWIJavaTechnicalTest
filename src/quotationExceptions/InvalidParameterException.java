package quotationExceptions;

/**
 * This exception detects if the parameters from and to are in wrong format.
 * @author Inatan
 */
public class InvalidParameterException extends Exception {

    /**
     * Creates a new instance of <code>InvalidParameterException</code> without detail
     * message.
     */
    public InvalidParameterException() {
    }

    /**
     * Constructs an instance of <code>InvalidParameterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }
}
