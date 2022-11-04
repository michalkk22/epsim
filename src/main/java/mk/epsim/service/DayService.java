package mk.epsim.service;

import mk.epsim.domain.Day;
import mk.epsim.domain.Simulation;
import mk.epsim.exception.DayNotFoundException;
import mk.epsim.repo.DayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DayService {
    private final DayRepo dayRepo;

    @Autowired
    public DayService(DayRepo dayRepo) {
        this.dayRepo = dayRepo;
    }

    public Day addDay(Day day) {
        return dayRepo.save(day);
    }

    public List<Day> findDaysBySimulationId(Long simulationId) {
        return dayRepo.findDaysBySimulationIdOrderByNumberOfDay(simulationId)
                .orElseThrow(() -> new DayNotFoundException("Day by simulationId " + simulationId + " was not found"));
    }

    public void deleteDayBySimulationId(Long simulationId) {
        dayRepo.deleteDayBySimulationId(simulationId);
    }

    public void generateSimulationDays(Simulation simulation){
        Day day = new Day();
        Long yesterdayPi;
        Long yesterdayPv;
        Long yesterdayPm;
        Long yesterdayPr;
        Long dayInfectedCounter[] = new Long[Math.toIntExact(simulation.getTs())];
        Long pm, pi;
        int counterAddress;

        //first day now
        day.setSimulationId(simulation.getId());
        day.setNumberOfDay(1L);

        day.setPi(simulation.getI());
        dayInfectedCounter[0] = day.getPi();
        day.setPv(simulation.getP() - simulation.getI());

        day.setPm(0L);
        day.setPr(0L);

        addDay(day);

        for (int i = 1; i < simulation.getTs(); i++) {
            yesterdayPi = day.getPi();
            yesterdayPv = day.getPv();
            yesterdayPm = day.getPm();
            yesterdayPr = day.getPr();
            day = new Day();

            day.setSimulationId(simulation.getId());
            day.setNumberOfDay((long) i+1);

            if (i >= simulation.getTm()) {
                counterAddress = (int) (i - simulation.getTm());
                pm = (long) Math.round(dayInfectedCounter[counterAddress] * simulation.getM());
                day.setPm(yesterdayPm + pm);
                dayInfectedCounter[counterAddress] -= pm;
                yesterdayPi -= pm;
            } else day.setPm(0L);

            if (i >= simulation.getTi()) {
                counterAddress = (int) (i - simulation.getTi());
                day.setPr(dayInfectedCounter[counterAddress] + yesterdayPr);
                yesterdayPi -= dayInfectedCounter[counterAddress];
                dayInfectedCounter[counterAddress] = 0L;
            } else day.setPr(0L);

            dayInfectedCounter[i] = (long) Math.floor(
                    yesterdayPi * simulation.getR()
            );

            if (dayInfectedCounter[i] < 1)
                dayInfectedCounter[i] = 1L;

            if (dayInfectedCounter[i] > yesterdayPv)
                dayInfectedCounter[i] = yesterdayPv;

            pi = yesterdayPi + dayInfectedCounter[i];
            day.setPi(pi);

            day.setPv(
                    simulation.getP()
                            -day.getPi()
                            -day.getPm()
                            -day.getPr()
            );

            addDay(day);
        }
    }
}
