package nessie.example;

import nessie.example.api.FindNessieRequest;
import nessie.example.api.FindNessieResponse;
import nessie.example.api.FindStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NessieFinderApplicationTests {

    private static final int TEST_ITERATIONS = 1000;
    private TestRestTemplate restTemplate;
    @Value("${local.server.port}")
    private int port;
    private String serverUrl;
    private Random random = new Random();

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();
        serverUrl = "http://localhost:" + port + "/";
    }

    @Test
    public void alwaysSwitch() {
        int correctGuesses = 0;
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            FindNessieResponse success = findNessie(createAlwaysSwitchRequest());
            if (success.isFound()) {
                correctGuesses++;
            }
        }
        System.out.println("########ALWAYS_SWITCH#########");
        System.out.println(formatPercent(correctGuesses));
        System.out.println("##############################");
    }

    @Test
    public void neverSwitch() {
        int correctGuesses = 0;
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            FindNessieResponse success = findNessie(createNeverSwitchRequest());
            if (success.isFound()) {
                correctGuesses++;
            }
        }
        System.out.println("########ALWAYS_SWITCH#########");
        System.out.println(formatPercent(correctGuesses));
        System.out.println("##############################");
    }

    private FindNessieRequest createAlwaysSwitchRequest() {
        return new FindNessieRequest(random.nextInt(3), FindStrategy.ALWAYS_SWITCH);
    }

    private FindNessieRequest createNeverSwitchRequest() {
        return new FindNessieRequest(random.nextInt(3), FindStrategy.NEVER_SWITCH);
    }

    private FindNessieResponse findNessie(FindNessieRequest request) {
        return this.restTemplate.postForObject(serverUrl, request, FindNessieResponse.class);
    }

    private String formatPercent(int correctGuesses) {
        return (correctGuesses * 100.0) / TEST_ITERATIONS + "%";
    }
}
