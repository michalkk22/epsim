package mk.epsim.service;


import mk.epsim.domain.Simulation;
import mk.epsim.exception.SimulationNotFoundException;
import mk.epsim.repo.SimulationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SimulationService {
    private final SimulationRepo simulationRepo;
    private final DayService dayService;

    @Autowired
    public SimulationService(SimulationRepo simulationRepo, DayService dayService) {
        this.simulationRepo = simulationRepo;
        this.dayService = dayService;
    }

    public Simulation addSimulation(Simulation simulation) {
        dayService.generateSimulationDays(simulation);
        return simulationRepo.save(simulation);
    }

    public List<Simulation> findAllSimulations() {
        return simulationRepo.findAll();
    }

    public Simulation findSimulationById(Long id) {
        return simulationRepo.findSimulationById(id)
                .orElseThrow(() -> new SimulationNotFoundException("Simulation by id " + id + " was not found"));
    }

    public Simulation updateSimulation(Simulation simulation) {
        dayService.deleteDayBySimulationId(simulation.getId());
        dayService.generateSimulationDays(simulation);
        return simulationRepo.save(simulation);
    }

    public void deleteSimulation(Long id) {
        dayService.deleteDayBySimulationId(id);
        simulationRepo.deleteSimulationById(id);
    }
}
