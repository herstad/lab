package nessie.example;

import nessie.example.api.FindNessieRequest;
import nessie.example.api.FindStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NessieFinderService {

    public static final int NUMBER_OF_CHOICES = 3;

    private RandomGenerator randomGenerator;

    @Autowired
    public NessieFinderService(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public boolean findNessie(FindNessieRequest request) {
        int correctChoice = randomGenerator.generateCorrectChoice();
        int choice = request.getChoice();
        if (shouldSwitch(request)) {
            choice = switchChoice(correctChoice, choice);
        }
        return isNessieFound(correctChoice, choice);
    }

    private boolean shouldSwitch(FindNessieRequest request) {
        return FindStrategy.ALWAYS_SWITCH.equals(request.getStrategy());
    }

    private int switchChoice(int correctChoice, int choice) {
        return randomGenerator.generateOption(correctChoice, choice);
    }

    private boolean isNessieFound(int correctChoice, int choice) {
        return choice == correctChoice;
    }
}
