package nessie.example.api;

public class FindNessieResponse {
    boolean found;

    public FindNessieResponse() {
    }

    public FindNessieResponse(boolean found) {

        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
