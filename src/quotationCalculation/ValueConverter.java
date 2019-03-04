package quotationCalculation;

import quotationExceptions.InvalidQuotationDateException;
import quotationExceptions.SmallerThanZeroException;
import quotationExceptions.UnavailableQuotationException;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import quotationExceptions.InvalidParameterException;

/**
 * <h1>ValueConverter</h1>
 * <p> The main goal of this class is convert some value in a currency to another 
 * currency. </p>
 * @author Inatan
 */
public final class ValueConverter {
    /**
     * <p> DATE_FORMAT is the format that date must be delivered. </p>
     */
    private final String DATE_FORMAT = "dd/MM/yyyy";
    /**
     * <p> <code>CSV_DATE_FORMAT</code> is the format that csv file is named, 
     * because the base has the date as its name.</p>
     */
    private final String CSV_DATE_FORMAT = "yyyyMMdd";
    /**
     * <code> QUOTATION_PATH </code> is the path where the csv files are placed
     */
    private final String QUOTATION_PATH= "files\\";    
    /**
     *  <p> Saturday and Sunday are enumerate values that represent the id of 
     *  weekend days in the class <code>Date</code>. </p>
     */
    private final  int SATURDAY = 6;
    private final int SUNDAY = 0;
    /**
     * <p><code>SCALE_PRECISION</code> is the number decimal places that must 
     * be rounded. </p>
     */
    private final int SCALE_PRECISION = 2;
    /**
     * <p>These constants is the difference between the weekend day to the last
     * business day.</p>
     */
    private final int SUNDAY_TO_LAST_BUSINESS_DAY = -2;
    private final int SATURDAY_TO_LAST_BUSINESS_DAY = -1;
    /***
     * <p>This method take a value in a currency and convert to another currency. </p>  
     * @param from String with the currency name (example "USD") you want 
     * to convert.
     * @param to String with the currency name (example "EUR") you want to see 
     * the result.
     * @param value: The value that should be converted. The currency of this 
     * value will be expressed in the “from” parameter.
     * @param quotation: A date as String in the format “dd/MM/yyyy”.
     * @return value in the destination currency that is rounded in two 
     * decimal places.
     * @throws SmallerThanZeroException if the value is smaller than zero.
     * @throws InvalidQuotationDateException if the date is in the wrong format
     * or the date doesn't exist.
     * @throws UnavailableQuotationException if the quotation date is a weekend 
     * and the immediately preceding business day is unavailabel or if the 
     * quotation date is unavailable.
     * @throws InvalidParameterException if the parameter to or from are not 
     * valid values. 
     */
    
    public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) throws SmallerThanZeroException, UnavailableQuotationException, InvalidQuotationDateException, InvalidParameterException {
        BigDecimal currentQuotationValue;
        String fileName;
        if (value.doubleValue()< 0){ //value exception.
            throw new SmallerThanZeroException("Invalid Value - Smaller than zero");
        }
        fileName = validateDateQuotation(quotation); //get csv file name after. 
        // the validation of date.
        File f = new File(QUOTATION_PATH+fileName+".csv");
        if(!f.exists()){ //if the file doesn't exist it means the quotation date
            // is unavailable.
            throw new UnavailableQuotationException("The quotation of "+ quotation +
                    " is unavailable!");
        }
        else{
            QuotationReader qr = new QuotationReader(QUOTATION_PATH + fileName+".csv");
            qr.readFile();
            if(!qr.isCurrency(from)){
                // from invalid.
                throw new InvalidParameterException("Invalid from - from value is"
                        + "unavailable!");
            } else if(!qr.isCurrency(to)) {
                // to invalid.
                throw new InvalidParameterException("Invalid to - to value is"
                        + "unavailable!");
            } else {
                currentQuotationValue=calculateQuotation(qr.getPurchaseRate(from),
                        qr.getPurchaseRate(to),value); 
                // make the conversion the value in a currency to another. 
            }
            
        }
        return currentQuotationValue;
    }

    /***
     * <p> This method that test if the date is valid and return the csv file 
     * name that has all currency quotation in the given date. </p>
     * @param quotation date that will be validated.
     * @return the csv file name.
     * @throws InvalidQuotationDate if the date quotation is in invalid format 
     * or doesn't exist. 
     */
    private String validateDateQuotation(String quotation) throws InvalidQuotationDateException{
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT); 
        // format that date must be given.
        SimpleDateFormat csvsdf = new SimpleDateFormat(CSV_DATE_FORMAT);
        // format used to read the quotation csv file.
        Date businessDay;
        String csvQuotationDate; //csv name of the correspondent quotation date.
        sdf.setLenient(false);
        Date date; // date that receive the parsed quotation date.
        try {
            date = sdf.parse(quotation);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date); //set in calendar the quotation date
            // test if is a weekend day. If is a weekend day the calendar day
            // subtract days to take the quotation from the immediately 
            // preceding business day. Else take the input quotation date. 
            if (date.getDay() == SUNDAY ){
                cal.add(Calendar.DATE, SUNDAY_TO_LAST_BUSINESS_DAY);
                businessDay = cal.getTime();
                csvQuotationDate = csvsdf.format(businessDay);
            }
            else if( date.getDay() == SATURDAY){
                cal.add(Calendar.DATE, SATURDAY_TO_LAST_BUSINESS_DAY);
                businessDay = cal.getTime();
                csvQuotationDate = csvsdf.format(businessDay);
            } else {
                businessDay = cal.getTime();
                csvQuotationDate = csvsdf.format(businessDay);
            } 
            // name the csv file that has the correspondent quotation date.
            return csvQuotationDate;
        } catch (ParseException ex) { 
            // if the parser throw a parse exception it means that date is 
            // invalid.
            throw new InvalidQuotationDateException("Invalid quotation date - "
                    + "invalid format or date doesn't exist", ex);
        }
    }
    
    /***
     * <p> This method calculate the value in the currency expected given the 
     * purchase rate of the source currency and the expected currency and the 
     * value that will be convert. </p> 
     * @param fromRate purchase rate of source currency.
     * @param toRate purchase rate of expected currency.
     * @param value value in the source currency.
     * @return the expected value that is rounded to two decimal places.
     */
    private BigDecimal calculateQuotation(double fromRate, double toRate, Number value){
        BigDecimal currentValue;
        double newValue; //used to calculate the new value after the conversion.
        newValue = (value.doubleValue() * fromRate) /toRate;  
        currentValue = new BigDecimal(newValue);
        currentValue = currentValue.setScale(SCALE_PRECISION , BigDecimal.ROUND_HALF_UP);
        // use the nearestNeighbor to round the currentValue in the 
        // SCALE_PRECISION. 
        return currentValue;
    }
}