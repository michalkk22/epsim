package mk.epsim.exception;

public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException(String message) {
        super(message);
    }
}
