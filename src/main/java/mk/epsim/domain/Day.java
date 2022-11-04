package mk.epsim.domain;

import org.apache.juli.logging.Log;

import javax.persistence.*;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;
    @Column(nullable = false, updatable = false)
    Long simulationId;
    Long numberOfDay;
    Long pi;
    Long pv;
    Long pm;
    Long pr;

    public Day() {}

    public Day(Long simulationId, Long numberOfDay, Long pi, Long pv, Long pm, Long pr) {
        this.simulationId = simulationId;
        this.numberOfDay = numberOfDay;
        this.pi = pi;
        this.pm = pm;
        this.pv = pv;
        this.pr = pr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSimulationId(Long simulationId) {
        this.simulationId = simulationId;
    }

    public Long getSimulationId() {
        return simulationId;
    }

    public void setNumberOfDay(Long numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public Long getNumberOfDay() {
        return numberOfDay;
    }

    public void setPi(Long pi) {
        this.pi = pi;
    }

    public Long getPi() {
        return pi;
    }

    public void setPm(Long pm) {
        this.pm = pm;
    }

    public Long getPm() {
        return pm;
    }

    public void setPr(Long pr) {
        this.pr = pr;
    }

    public Long getPr() {
        return pr;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getPv() {
        return pv;
    }

}
