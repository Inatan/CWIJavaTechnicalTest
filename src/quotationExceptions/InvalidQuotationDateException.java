package quotationExceptions;

/**
 * This exception detects if the quotation date is in the wrong format or 
 * if it doesn't exist.
 * @author Inatan
 */
public class InvalidQuotationDateException extends Exception {

    /**
     * Creates a new instance of <code>InvalidQuotantionDateException</code> without
     * detail message.
     */
    public InvalidQuotationDateException() {
    }

    /**
     * Constructs an instance of <code>InvalidQuotantionDateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidQuotationDateException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>InvalidQuotantionDateException</code> 
     * with the specified detail message and with the exception that is thrown 
     * before.
     *
     * @param msg the detail message.
     * @param ex exception that is thrown before 
     */
    public InvalidQuotationDateException(String msg, Exception ex) {
        super(msg, ex);
    }
}
