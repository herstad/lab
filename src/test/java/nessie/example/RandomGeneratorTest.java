package nessie.example;

import org.junit.Test;

import static org.junit.Assert.*;


public class RandomGeneratorTest {

    private RandomGenerator randomGenerator = new RandomGenerator();

    @Test
    public void testGenerate() throws Exception {
        assertNotNull(randomGenerator.generateCorrectChoice());
    }

    @Test
    public void givenIncorrectChoiseWhenGettingOptionThenReturnCorrect() {
        int correct = 1;
        int choice = 2;
        int option = randomGenerator.generateOption(correct, choice);
        assertEquals(correct, option);
    }

    @Test
    public void givenCorrectChoiseWhenGettingOptionThenReturnOther() {
        int correct = 2;
        int choice = 2;
        int option = randomGenerator.generateOption(correct, choice);
        assertNotEquals(correct, option);
    }

}