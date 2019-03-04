package quotationExceptions;

/**
 * This exception detects if the quotation date is a weekend day, the previous 
 * business day is unavailable or the current date quotation is unavailable.
 * @author Inatan
 */
public class UnavailableQuotationException extends Exception {

    /**
     * Creates a new instance of <code>UnavailableQuotantion</code> without
     * detail message.
     */
    public UnavailableQuotationException() {
    }

    /**
     * Constructs an instance of <code>UnavailableQuotantion</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnavailableQuotationException(String msg) {
        super(msg);
    }
}
