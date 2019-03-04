package quotationExceptions;

/**
 * This exception detects if the value is smaller than zero.
 * @author Inatan
 */
public class SmallerThanZeroException extends Exception {

    /**
     * Creates a new instance of <code>SmallerThanZeroException</code> without 
     * detail message.
     */
    public SmallerThanZeroException() {
    }

    /**
     * Constructs an instance of <code>SmallerThanZeroException</code> with the 
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SmallerThanZeroException(String msg) {
        super(msg);
    }
}
