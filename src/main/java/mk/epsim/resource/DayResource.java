package mk.epsim.resource;

import mk.epsim.domain.Day;
import mk.epsim.service.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day")
public class DayResource {
    private final DayService dayService;

    public DayResource(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/find/{simulationId}")
    public ResponseEntity<List<Day>> getDayBySimulationIdOrderByNumberOfDay(@PathVariable("simulationId") Long simulationId) {
        List<Day> days = dayService.findDaysBySimulationId(simulationId);
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Day> addDay(@RequestBody Day day) {
//        Day newDay = dayService.addDay(day);
//        return new ResponseEntity<>(newDay, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete/{simulationId}")
//    public ResponseEntity<?> deleteDayBySimulationId(@PathVariable("simulationId") Long simulationId) {
//        dayService.deleteDayBySimulationId(simulationId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
