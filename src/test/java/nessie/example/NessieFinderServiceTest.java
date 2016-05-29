package nessie.example;

import nessie.example.api.FindNessieRequest;
import nessie.example.api.FindStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NessieFinderServiceTest {

    private static final int CORRECT_CHOICE = 1;
    private static final int INCORRECT_CHOICE = 2;
    private NessieFinderService finderService;
    private RandomGenerator randomGenerator;

    @Before
    public void setUp() {
        setUpMock();
        finderService = new NessieFinderService(randomGenerator);
    }

    @Test
    public void givenCorrectChoiceNeverSwitchReturnTrue() throws Exception {
        assertTrue(findNessie(CORRECT_CHOICE, FindStrategy.NEVER_SWITCH));
    }

    @Test
    public void givenInorrectChoiceNeverSwitchReturnFalse() throws Exception {
        assertFalse(findNessie(INCORRECT_CHOICE, FindStrategy.NEVER_SWITCH));
    }

    @Test
    public void givenCorrectChoiceAlwaysSwitchReturnFalse() throws Exception {
        assertFalse(findNessie(CORRECT_CHOICE, FindStrategy.ALWAYS_SWITCH));
    }

    @Test
    public void givenIncorrectChoiceAlwaysSwitchReturnFalse() throws Exception {
        assertFalse(findNessie(INCORRECT_CHOICE, FindStrategy.ALWAYS_SWITCH));
    }

    private boolean findNessie(int choice, FindStrategy strategy) {
        return finderService.findNessie(new FindNessieRequest(choice, strategy));
    }

    private void setUpMock() {
        randomGenerator = mock(RandomGenerator.class);
        when(randomGenerator.generateCorrectChoice()).thenReturn(CORRECT_CHOICE);
        when(randomGenerator.generateOption(eq(CORRECT_CHOICE), anyInt())).thenReturn(INCORRECT_CHOICE);
        when(randomGenerator.generateOption(eq(INCORRECT_CHOICE), anyInt())).thenReturn(CORRECT_CHOICE);
    }
}