package com.example.nameapp;

import org.junit.Assert;
import org.junit.Test;

import static com.example.nameapp.HelperClass.containsNumber;
import static com.example.nameapp.HelperClass.isValidString;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidationTest {

    @Test
    /*Sjekkar at gyldige tekststrengar faktisk er gyldige*/
    public void checkInputValidationCorrectString() throws Exception {

        String testNorwegianLetters = "æÆøØåÅ";
        String testHyphen = "Anne-Marie";
        String testBlankSpace = "John Smith";
        String testNotNumber = "Anne";

        Assert.assertTrue(isValidString(testNorwegianLetters));
        Assert.assertTrue(isValidString(testHyphen));
        Assert.assertTrue(isValidString(testBlankSpace));
        Assert.assertFalse(containsNumber(testNotNumber));
    }

    @Test
    public void checkInputValidationIncorrectString() throws Exception {

        String testIllegalChars = "Hei!";
        String testNumbers = "H1nriette";

        Assert.assertFalse(isValidString(testIllegalChars));
        Assert.assertTrue(containsNumber(testNumbers));
    }


}