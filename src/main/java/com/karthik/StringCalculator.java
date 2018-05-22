package com.karthik;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StringCalculator {

    private static final String DELIMITER_SYMBOL = "//";
    private static final String NEWLINE = "\n";
    private static final String DELIMITER_SEPARATOR = "\\|";
    private static final int MIN_ALLOWED_VALUE = 0;
    private static final int MAX_ALLOWED_VALUE = 1000;
    private static final String DEFAULT_DELIMITERS = ",|\n";

    static int add(String numbers) {
        if (StringUtils.isEmpty(numbers))
            return 0;
        String delimiters;
        if (!StringUtils.startsWith(numbers, DELIMITER_SYMBOL)) {
            delimiters = DEFAULT_DELIMITERS;
        } else {
            String[] splitInput = numbers.split(NEWLINE);
            numbers = splitInput[1];
            delimiters = getCustomDelimiters(splitInput[0]);
        }
        List<String> splitNumbers = Arrays.asList(StringUtils.split(numbers, delimiters));
        List<Integer> numbersList = splitNumbers.stream()
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> negativeNumbers = numbersList.stream()
                .filter(number -> number < MIN_ALLOWED_VALUE).collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed - ["
                    + StringUtils.join(negativeNumbers, ",") + "]");
        }
        return numbersList.stream()
                .filter(number -> number >= MIN_ALLOWED_VALUE && number <= MAX_ALLOWED_VALUE)
                .mapToInt(Integer::intValue).sum();
    }

    private static String getCustomDelimiters(String delimiterText) {
        String delimiters = StringUtils.substringAfter(delimiterText, DELIMITER_SYMBOL);
        List<String> delimitersList = Arrays.asList(delimiters.split(DELIMITER_SEPARATOR));
        return StringUtils.join(delimitersList, "|");
    }

}
