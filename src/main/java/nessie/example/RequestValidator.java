package nessie.example;

import nessie.example.api.FindNessieRequest;
import org.springframework.stereotype.Component;

import static nessie.example.NessieFinderService.NUMBER_OF_CHOICES;

@Component
public class RequestValidator {

    public boolean validateRequest(FindNessieRequest request) {
        return request.getChoice() >= 0
                && request.getChoice() < NUMBER_OF_CHOICES
                && request.getStrategy() != null;
    }
}
