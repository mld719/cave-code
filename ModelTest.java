

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ModelTest.
 *
 * Used this to test a wide variety of numbers on th eSuper Model.
 */
public class ModelTest
{
    /**
     * Default constructor for test class ModelTest
     */
    public ModelTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test public void testDigits()
    {
        Model m0 = new Model("attempt.txt", "two");
        String out0 = m0.go();
        assertEquals("0 0 2", out0);
        
        Model m1 = new Model("attempt.txt", "six");
        String out1 = m1.go();
        assertEquals("0 0 6", out1);
        
        Model m2 = new Model("attempt.txt", "nine");
        String out2 = m2.go();
        assertEquals("0 0 9", out2);
    }
    
    @Test public void testTeens()
    {
        Model m0 = new Model("attempt.txt", "twelve");
        String out0 = m0.go();
        assertEquals("0 1 2", out0);
        
        Model m1 = new Model("attempt.txt", "fourteen");
        String out1 = m1.go();
        assertEquals("0 1 4", out1);
        
        Model m2 = new Model("attempt.txt", "seventeen");
        String out2 = m2.go();
        assertEquals("0 1 7", out2);
    }
    
    @Test public void testDecades()
    {
        Model m0 = new Model("attempt.txt", "twenty four");
        String out0 = m0.go();
        assertEquals("0 2 4", out0);
        
        Model m1 = new Model("attempt.txt", "eighty");
        String out1 = m1.go();
        assertEquals("0 8 0", out1);
        
        Model m2 = new Model("attempt.txt", "ten");
        String out2 = m2.go();
        assertEquals("0 1 0", out2);
    }
    
    @Test public void testHundred()
    {
        Model m0 = new Model("attempt.txt", "six hundred eleven");
        String out0 = m0.go();
        assertEquals("6 1 1", out0);
        
        Model m1 = new Model("attempt.txt", "nine hundred forty");
        String out1 = m1.go();
        assertEquals("9 4 0", out1);
        
        Model m2 = new Model("attempt.txt", "three hundred five");
        String out2 = m2.go();
        assertEquals("3 0 5", out2);
        
        Model m3 = new Model("attempt.txt", "one hundred");
        String out3 = m3.go();
        assertEquals("1 0 0", out3);
    }
    
    @Test public void testThousands()
    {
        Model m0 = new Model("attempt.txt", "five thousand four hundred thirty two");
        String out0 = m0.go();
        assertEquals("0 0 5   4 3 2", out0);
        
        Model m1 = new Model("attempt.txt", "nine thousand six hundred ten");
        String out1 = m1.go();
        assertEquals("0 0 9   6 1 0", out1);
        
        Model m2 = new Model("attempt.txt", "eight thousand seven hundred six");
        String out2 = m2.go();
        assertEquals("0 0 8   7 0 6", out2);
        
        Model m3 = new Model("attempt.txt", "two thousand forty seven");
        String out3 = m3.go();
        assertEquals("0 0 2   0 4 7", out3);
        
        Model m4 = new Model("attempt.txt", "one thousand two hundred");
        String out4 = m4.go();
        assertEquals("0 0 1   2 0 0", out4);
        
        Model m5 = new Model("attempt.txt", "four thousand eighty");
        String out5 = m5.go();
        assertEquals("0 0 4   0 8 0", out5);
        
        Model m6 = new Model("attempt.txt", "seven thousand six");
        String out6 = m6.go();
        assertEquals("0 0 7   0 0 6", out6);
        
        Model m7 = new Model("attempt.txt", "three thousand");
        String out7 = m7.go();
        assertEquals("0 0 3   0 0 0", out7);
    }
    
    
    
    @Test public void testTenThousands()
    {
        Model m0 = new Model("attempt.txt", "twelve thousand four hundred thirty two");
        String out0 = m0.go();
        assertEquals("0 1 2   4 3 2", out0);
        
        Model m1 = new Model("attempt.txt", "forty thousand six hundred ten");
        String out1 = m1.go();
        assertEquals("0 4 0   6 1 0", out1);
    }
   
    
    @Test public void testHundredThousands()
    {
        Model m0 = new Model("attempt.txt", "five hundred eighty two thousand six hundred eleven");
        String out0 = m0.go();
        assertEquals("5 8 2   6 1 1", out0);
        
        Model m1 = new Model("attempt.txt", "nine hundred seventy thousand four hundred eighty two");
        String out1 = m1.go();
        assertEquals("9 7 0   4 8 2", out1);
        
        Model m2 = new Model("attempt.txt", "one hundred six thousand five hundred twenty seven");
        String out2 = m2.go();
        assertEquals("1 0 6   5 2 7", out2);
        
        Model m3 = new Model("attempt.txt", "four hundred thousand nine hundred fifteen");
        String out3 = m3.go();
        assertEquals("4 0 0   9 1 5", out3);
    }
    
    @Test public void testMillions()
    {
        Model m0 = new Model("attempt.txt", "eight million four hundred thirty thousand six hundred eight");
        String out0 = m0.go();
        assertEquals("0 0 8   4 3 0   6 0 8", out0);      
    }
    
    @Test public void testTenMillions()
    {
        Model m0 = new Model("attempt.txt", "forty three million nine hundred twelve thousand six hundred eighty one");
        String out0 = m0.go();
        assertEquals("0 4 3   9 1 2   6 8 1", out0);
        
        Model m1 = new Model("attempt.txt", "thirty million six thousand four hundred twenty one");
        String out1 = m1.go();
        assertEquals("0 3 0   0 0 6   4 2 1", out1);
    }
    
    @Test public void testHundredMillions()
    {
        Model m0 = new Model("attempt.txt", "four hundred million two hundred forty five thousand six hundred twelve");
        String out0 = m0.go();
        assertEquals("4 0 0   2 4 5   6 1 2", out0);
        
        Model m1 = new Model("attempt.txt", "six hundred eighty million");
        String out1 = m1.go();
        assertEquals("6 8 0   0 0 0   0 0 0", out1);
        
        Model m2 = new Model("attempt.txt", "five hundred fifty five million eight hundred sixteen thousand nine hundred thirty seven");
        String out2 = m2.go();
        assertEquals("5 5 5   8 1 6   9 3 7", out2);
        
        Model m3 = new Model("attempt.txt", "six hundred two million forty five thousand eight");
        String out3 = m3.go();
        assertEquals("6 0 2   0 4 5   0 0 8", out3);
    }
    
    @Test public void testBillions()
    {
        Model m0 = new Model("attempt.txt", "six billion four hundred million two hundred forty five thousand six hundred twelve");
        String out0 = m0.go();
        assertEquals("0 0 6   4 0 0   2 4 5   6 1 2", out0);
        
        Model m1 = new Model("attempt.txt", "two billion six hundred eighty million");
        String out1 = m1.go();
        assertEquals("0 0 2   6 8 0   0 0 0   0 0 0", out1);
        
    }
    
    @Test public void testTenBillions()
    {
        Model m0 = new Model("attempt.txt", "twelve billion four hundred million two hundred forty five thousand six hundred twelve");
        String out0 = m0.go();
        assertEquals("0 1 2   4 0 0   2 4 5   6 1 2", out0);
        
        Model m1 = new Model("attempt.txt", "forty six billion six hundred eighty million");
        String out1 = m1.go();
        assertEquals("0 4 6   6 8 0   0 0 0   0 0 0", out1);
        
        Model m2 = new Model("attempt.txt", "ten billion five hundred fifty five million eight hundred sixteen thousand nine hundred thirty seven");
        String out2 = m2.go();
        assertEquals("0 1 0   5 5 5   8 1 6   9 3 7", out2);
        
        Model m3 = new Model("attempt.txt", "eighty billion six hundred two million forty five thousand eight");
        String out3 = m3.go();
        assertEquals("0 8 0   6 0 2   0 4 5   0 0 8", out3);
    }
    
    @Test public void testHundredBillions()
    {
        Model m0 = new Model("attempt.txt", "eight hundred six billion four hundred million two hundred forty five thousand six hundred twelve");
        String out0 = m0.go();
        assertEquals("8 0 6   4 0 0   2 4 5   6 1 2", out0);
        
        Model m1 = new Model("attempt.txt", "six hundred eighty billion");
        String out1 = m1.go();
        assertEquals("6 8 0   0 0 0   0 0 0   0 0 0", out1);
        
        Model m2 = new Model("attempt.txt", "nine hundred twelve billion five hundred fifty five million eight hundred sixteen thousand nine hundred thirty seven");
        String out2 = m2.go();
        assertEquals("9 1 2   5 5 5   8 1 6   9 3 7", out2);
        
        Model m3 = new Model("attempt.txt", "five hundred billion six hundred two million forty five thousand eight");
        String out3 = m3.go();
        assertEquals("5 0 0   6 0 2   0 4 5   0 0 8", out3);
    }
}
