package quotationCalculation;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quotationExceptions.InvalidParameterException;
import quotationExceptions.InvalidQuotationDateException;
import quotationExceptions.SmallerThanZeroException;
import quotationExceptions.UnavailableQuotationException;

/**
 * This test class has a group of tests in the <code> CurrencyQuotation </code>
 * @author Inatan
 */
public class ValueConverterTest {
    
    public ValueConverterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * The goal of this test if the method works returning the expected value
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test
    public void testCurrencyQuotation() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "16/12/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal expResult = new BigDecimal(95.92);
        expResult = expResult.setScale(2 , BigDecimal.ROUND_HALF_UP); 
        // is necessary to setScale of the expected result, because the 
        // BigDecimal has a underflow problem
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>SmallerThanZeroException 
     * </code>, when the value is smaller than zero. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=SmallerThanZeroException.class)
    public void testCurrencyQuotation1() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = -100.00;
        String quotation = "16/12/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>InvalidParameterException 
     * </code>, when is given a wrong parameter <code>from</code>. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=InvalidParameterException.class)
    public void testCurrencyQuotation2() throws Exception {
        System.out.println("currencyQuotation");
        String from = "foo";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "16/12/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>InvalidParameterException 
     * </code>, when is given a wrong parameter <code>to</code>. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=InvalidParameterException.class)
    public void testCurrencyQuotation3() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "foo";
        Number value = 100.00;
        String quotation = "16/12/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>InvalidQuotationDateException 
     * </code>, when the <code>quotation</code> date is in wrong format. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=InvalidQuotationDateException.class)
    public void testCurrencyQuotation4() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "16-12-2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>InvalidQuotationDateException 
     * </code>, when the <code>quotation</code> date doesn't exist. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=InvalidQuotationDateException.class)
    public void testCurrencyQuotation5() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "31/02/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>UnavailableQuotationException 
     * </code>, when the <code>quotation</code> date is on a Sunday and the
     * immediately preceding business day is unavailable. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=UnavailableQuotationException.class)
    public void testCurrencyQuotation6() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "11/12/2016"; // Sunday
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>UnavailableQuotationException 
     * </code>, when the <code>quotation</code> date is on a Saturday and the
     * immediately preceding business day is unavailable. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=UnavailableQuotationException.class)
    public void testCurrencyQuotation7() throws Exception {
        System.out.println("currencyQuotation");
        String from = "USD";
        String to = "EUR";
        Number value = 100.00;
        String quotation = "10/12/2016"; // Saturday
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is try replace the parameters <code>from</code> and
     * <code>to</code> to get a different expected result. </p>
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test
    public void testCurrencyQuotation8() throws Exception{
        System.out.println("currencyQuotation");
        String from = "EUR";
        String to = "USD";
        Number value = 100.00;
        String quotation = "16/12/2016";
        ValueConverter instance = new ValueConverter();
        BigDecimal expResult = new BigDecimal(104.25);
        expResult = expResult.setScale(2 , BigDecimal.ROUND_HALF_UP);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * The goal of this test is use <code> quotation </code> date that is on a
     * Sunday and the immediately preceding business day is available.
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test
    public void testCurrencyQuotation9() throws Exception{
        System.out.println("currencyQuotation");
        String from = "EUR";
        String to = "USD";
        Number value = 100.00;
        String quotation = "18/12/2016"; // Sunday
        ValueConverter instance = new ValueConverter();
        BigDecimal expResult = new BigDecimal(104.25);
        expResult = expResult.setScale(2 , BigDecimal.ROUND_HALF_UP);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * The goal of this test is use <code> quotation </code> date that is on a
     * Saturday and the immediately preceding business day is available.
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test
    public void testCurrencyQuotation10() throws Exception{
        System.out.println("currencyQuotation");
        String from = "EUR";
        String to = "USD";
        Number value = 100.00;
        String quotation = "17/12/2016"; // Saturday
        ValueConverter instance = new ValueConverter();
        BigDecimal expResult = new BigDecimal(104.25);
        expResult = expResult.setScale(2 , BigDecimal.ROUND_HALF_UP);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is thrown the <code>UnavailableQuotationException 
     * </code>, when the <code>quotation</code> date is unavailable. </p> 
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=UnavailableQuotationException.class)
    public void testCurrencyQuotation11() throws Exception {
        System.out.println("currencyQuotation");
        String from = "EUR";
        String to = "USD";
        Number value = 100.00;
        String quotation = "15/12/1999"; // date that quotation is unavailable
        ValueConverter instance = new ValueConverter();
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
    }
    
    /**
     * Test of currencyQuotation method, of class ValueConverter.
     * <p> The goal of this test is try to use differents parameters than the 
     * previous tests and get the expected value.</p> 
     * @throws Exception because the method has exceptions that can be thrown.
     */
    @Test(expected=UnavailableQuotationException.class)
    public void testCurrencyQuotation12() throws Exception {
        System.out.println("currencyQuotation");
        String from = "AFN";
        String to = "MVR";
        Number value = 25.50;
        String quotation = "20/12/1999";
        ValueConverter instance = new ValueConverter();
        BigDecimal expResult = new BigDecimal(6.08);
        expResult = expResult.setScale(2 , BigDecimal.ROUND_HALF_UP);
        BigDecimal result = instance.currencyQuotation(from, to, value, quotation);
        assertEquals(expResult, result);
    }
}
