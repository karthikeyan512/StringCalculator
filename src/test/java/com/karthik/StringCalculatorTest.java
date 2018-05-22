package com.karthik;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringCalculatorTest {

    @Parameter
    public String input;

    @Parameter(1)
    public int output;

    @Parameters(name = "Input String: {0} Expected output: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", 0}, //Empty String
                {"1", 1}, //One number
                {"1,2", 3}, //Two number separated by ,
                {"1,2,3", 6}, //Three number separated by ,
                {"1\n2,3", 6}, //Three number separated by , and \n
                {"//;\n1;2", 3}, //Two number separated by ;
                {"2,1001", 2}, //Two number separated by , but one number above max limit
                {"//***\n1***2***3", 6}, //Three numbers separated by ***
                {"//*|%\n1*2%3", 6}, //Three numbers separated by * and %
                {"//**|%%%\n1**2%%%3", 6}, //Three numbers separated by ** and %%%
                {"1,", 1}, //One number separated by ,
                {"//\t\n1\t2", 3}, //Two numbers separated by \t
                {"//.\n1.2.3.4.5", 15}, //Five numbers separated by .
                {"0,2,1000", 1002}, //Values with allowed min and max
        });
    }

    @Test
    public void testStringCalculator() {
        assertEquals(output, StringCalculator.add(input));
    }

}
