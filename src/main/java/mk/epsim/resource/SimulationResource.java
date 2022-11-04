package mk.epsim.resource;

import mk.epsim.domain.Simulation;
import mk.epsim.service.SimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationResource {
    private final SimulationService simulationService;

    public SimulationResource(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Simulation>> getAllSimulations() {
        List<Simulation> simulations = simulationService.findAllSimulations();
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Simulation> getSimulationById(@PathVariable("id") Long id) {
        Simulation simulation = simulationService.findSimulationById(id);
        return new ResponseEntity<>(simulation, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Simulation> addSimulation(@RequestBody Simulation simulation) {
        Simulation newSimulation = simulationService.addSimulation(simulation);
        return new ResponseEntity<>(newSimulation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Simulation> updateSimulation(@RequestBody Simulation simulation) {
        Simulation updateSimulation = simulationService.updateSimulation(simulation);
        return new ResponseEntity<>(updateSimulation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSimulation(@PathVariable("id") Long id) {
        simulationService.deleteSimulation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
