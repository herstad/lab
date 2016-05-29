package nessie.example;

import nessie.example.api.FindNessieRequest;
import nessie.example.api.FindStrategy;
import org.junit.Before;
import org.junit.Test;

import static nessie.example.api.FindStrategy.*;
import static org.junit.Assert.*;

public class RequestValidatorTest {

    private static final int VALID_CHOICE = 2;
    private static final int TOO_LARGE_CHOICE = 3;
    private static final int TOO_SMALL_CHOICE = -1;
    private RequestValidator requestValidator;

    @Before
    public void setUp() {
        requestValidator = new RequestValidator();
    }

    @Test
    public void validChoiceAndStrategyShouldReturnTrue() throws Exception {
        assertTrue(validate(VALID_CHOICE, ALWAYS_SWITCH));
    }

    @Test
    public void toLargeChoiceShouldReturnFalse() throws Exception {
        assertFalse(validate(TOO_LARGE_CHOICE, ALWAYS_SWITCH));
    }

    @Test
    public void toSmallChoiceShouldReturnFalse() throws Exception {
        assertFalse(validate(TOO_SMALL_CHOICE, ALWAYS_SWITCH));
    }

    @Test
    public void noStrategyShouldReturnFalse() throws Exception {
        assertFalse(validate(VALID_CHOICE, null));
    }

    private boolean validate(int choice, FindStrategy strategy) {
        return requestValidator.validateRequest(new FindNessieRequest(choice, strategy));
    }
}