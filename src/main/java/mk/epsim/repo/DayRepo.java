package mk.epsim.repo;

import mk.epsim.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayRepo extends JpaRepository<Day, Long> {
    Optional<List<Day>> findDaysBySimulationIdOrderByNumberOfDay(Long simulationId);

    Optional<Day> findDayBySimulationIdAndNumberOfDay(Long simulationId, Long numberOfDay);

    void deleteDayBySimulationId(Long simulationId);
}
