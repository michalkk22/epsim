package mk.epsim.repo;

import mk.epsim.domain.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimulationRepo extends JpaRepository<Simulation, Long> {

    void deleteSimulationById(Long id);

    Optional<Simulation> findSimulationById(Long id);
}
