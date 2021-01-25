package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import model.utilities.inputCheck.Errors;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class IncompatibleTypesExceptionCallTest {

    private IncompatibleTypesExceptionCall testCall;
    private Errors e1, e2;
    private ArrayList<Errors> errors;

    @Before
    public void setUp() {
        e1 = new Errors("column1", "errorType1", "taxon1", "rowNo1", "errorValue1");
        e2 = new Errors("column2", "errorType2", "taxon2", "rowNo2", "errorValue2");
        errors = new ArrayList<>();
        errors.add(e1);
        errors.add(e2);
        testCall = new IncompatibleTypesExceptionCall(errors);
    }

    @Test
    public void testConstructor() {
        assertEquals(testCall.getErrorsArrayList().size(), 2);
    }

    @Test
    public void testGetErrorsArrayList() {
        assertEquals(testCall.getErrorsArrayList(), errors);
    }

    @Test
    public void testDisplayingErrors() {
        assertEquals(testCall.displayingErrors(), true);
    }
}
