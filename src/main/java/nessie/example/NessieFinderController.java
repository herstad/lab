package nessie.example;

import nessie.example.api.FindNessieRequest;
import nessie.example.api.FindNessieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class NessieFinderController {

    private RequestValidator requestValidator;
    private NessieFinderService finderService;

    @Autowired
    public NessieFinderController(RequestValidator requestValidator, NessieFinderService finderService) {
        this.requestValidator = requestValidator;
        this.finderService = finderService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FindNessieResponse> findNessie(@RequestBody FindNessieRequest request) {
        if (!requestValidator.validateRequest(request)) {
            return createBadRequest();
        }
        return createResponse(finderService.findNessie(request));
    }

    private ResponseEntity<FindNessieResponse> createBadRequest() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<FindNessieResponse> createResponse(boolean isNessieFound) {
        return new ResponseEntity<>(new FindNessieResponse(isNessieFound), HttpStatus.OK);
    }

}
