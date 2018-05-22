package com.karthik;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class StringCalculatorNegativeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameter
    public String input;

    @Parameter(1)
    public String errorMsg;

    @Parameters(name = "Input String: {0} Expected output: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1,-2", "negatives not allowed - [-2]"},
                {"1,-2,-3", "negatives not allowed - [-2,-3]"},
        });
    }

    @Test
    public void testStringCalculatorNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(equalTo(errorMsg));
        StringCalculator.add(input);
    }

}
