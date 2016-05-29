package nessie.example.api;

public class FindNessieRequest {
    private int choice;
    private FindStrategy strategy;

    public FindNessieRequest() {
    }

    public FindNessieRequest(int choice, FindStrategy strategy) {
        this.choice = choice;
        this.strategy = strategy;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public FindStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(FindStrategy strategy) {
        this.strategy = strategy;
    }
}
