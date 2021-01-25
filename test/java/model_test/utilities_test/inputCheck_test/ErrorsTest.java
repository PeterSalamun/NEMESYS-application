package model_test.utilities_test.inputCheck_test;

import model.utilities.inputCheck.Errors;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorsTest {

    String column, type, taxon, row, value;
    Errors testErr;

    @Before
    public void setUp() {
        column = "a";
        type = "b";
        taxon = "c";
        row = "d";
        value = "e";

        testErr = new Errors(null,null,null,null,null);
    }

    @Test
    public void testGetters() {
        assertEquals(testErr.getSite(), null);
        assertEquals(testErr.getErrorType(), null);
        assertEquals(testErr.getTaxon(), null);
        assertEquals(testErr.getRowNo(), null);
        assertEquals(testErr.getErrorValue(), null);

        testErr = new Errors(column, type, taxon, row, value);

        assertEquals(testErr.getSite(), "a");
        assertEquals(testErr.getErrorType(), "b");
        assertEquals(testErr.getTaxon(), "c");
        assertEquals(testErr.getRowNo(), "d");
        assertEquals(testErr.getErrorValue(), "e");
    }
}
