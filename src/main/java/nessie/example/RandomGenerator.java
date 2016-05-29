package nessie.example;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nessie.example.NessieFinderService.NUMBER_OF_CHOICES;

@Component
public class RandomGenerator {
    private Random random = new Random();

    public int generateCorrectChoice() {
        return random.nextInt(NUMBER_OF_CHOICES);
    }

    public int generateOption(int correctChoice, int choice) {
        if (correctChoice != choice) {
            return correctChoice;
        } else {
            return pickRandomIncorrectChoice(correctChoice);
        }
    }

    private int pickRandomIncorrectChoice(int correctChoice) {
        List<Integer> options = getIncorrectOptions(correctChoice);
        return pickRandomElement(options);
    }

    private List<Integer> getIncorrectOptions(int correctChoice) {
        return getOptions(i -> i != correctChoice);
    }

    private List<Integer> getOptions(IntPredicate excludedOption) {
        return IntStream.rangeClosed(0, NUMBER_OF_CHOICES)
                .filter(excludedOption)
                .boxed()
                .collect(Collectors.toList());
    }

    private Integer pickRandomElement(List<Integer> options) {
        return options.get(random.nextInt(options.size() -1));
    }
}
