package quotationCalculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * <h1> QuotationReader </h1>
 * <p> This class reprensent a csv file that contains the currency symbol and 
 * the purchase rate. </p>
 * @author Inatan
 * 
 */
public class QuotationReader {
    /**
    * This is the csv file name.
    */
    private String csvFile;  
    /**
     * <p> HashMap that contains the currency symbol as key and the purchase 
     * rate as value. <p>
     */
    private HashMap<String, Double> quotationRate;
    /**
     * Symbol that used to split data in the csv file.
     */
    private final String cvsSplitBy = ";";
    /**
     * <p> These constants are the index of currency symbol and purchase rate in
     * the base given in the csv file. </p>
     */
    private final int CURRENCY_SYMBOL_INDEX= 3;
    private final int PURCHASE_RATE_INDEX= 4;
    /**
     * <p> Create a quotation reader with the given csv file name. </p>
     * @param csvFile: csv file name.
     */
    public QuotationReader(String csvFile){
        this.csvFile=csvFile;
        this.quotationRate = new HashMap<String, Double>();
    }
    
    
    /***
     * <p> This method read a csv file that contains information about the 
     * currency of respective date and save a HashMap that the key is the 
     * currency and the value is this currency purchase rate. </p>
     */
    public void readFile(){        
        this.quotationRate = new HashMap<>();
        Double purchaseRate;
        String line="";
        try (BufferedReader br = new BufferedReader(
                new FileReader(this.csvFile))) {
                
            while ((line = br.readLine()) != null) {
                String[] quotationBase = line.split(cvsSplitBy);
                purchaseRate = Double.parseDouble(quotationBase[PURCHASE_RATE_INDEX].replace(',', '.'));
                // to change the portuguese pattern to a english pattern
                // replace ',' to '.'.
                quotationRate.put(quotationBase[CURRENCY_SYMBOL_INDEX],purchaseRate);
                // create new key and value to local hashmap.
            }
        } catch (IOException e) {
        
        }
    }
    
    /**
     * <p> This method take the currency and return the purchase rate of this 
     * currency. </p>
     * @param currency is symbol that will be key in the <code> quotationRate 
     * </code>
     * @return purchase rate of the input currency 
     */
    public double getPurchaseRate(String currency){
        return quotationRate.get(currency);
    }
    
    /**
     * <p> Test if the currency is available in the quotation readed. </p>
     * @param currency that will be test.
     * @return a boolean if the currency is available or not.
     */
    public boolean isCurrency(String currency){
        return quotationRate.containsKey(currency);
    }
}
