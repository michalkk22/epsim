package mk.epsim.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Simulation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Long p;     //wielkość populacji
    private Long i;     //początkowa liczba osób zarażonych
    private Float r;    //wskaźnik określający ile osób zaraża jedna zarażona osoba
    private Float m;    //wskaźnik śmiertelności, określający ilu spośród zarażonych umiera
    private Long ti;    //ilość dni od momentu zarażenia do wyzdrowienia chorego
    private Long tm;    //ilość dni od momentu zarażenia do śmierci chorego
    private Long ts;    //ilość dni symulacji

    public Simulation() {}

    public Simulation(String name, Long p, Long i, Float r, Float m, Long ti, Long tm, Long ts) {
        this.name = name;
        this.p = p;
        this.i = i;
        this.r = r;
        this.m = m;
        this.ti = ti;
        this.tm = tm;
        this.ts = ts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getP() {
        return p;
    }

    public void setP(Long p) {
        this.p = p;
    }

    public Long getI() {
        return i;
    }

    public void setI(Long i) {
        this.i = i;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public Float getM() {
        return m;
    }

    public void setM(Float m) {
        this.m = m;
    }

    public Long getTi() {
        return ti;
    }

    public void setTi(Long ti) {
        this.ti = ti;
    }

    public Long getTm() {
        return tm;
    }

    public void setTm(Long tm) {
        this.tm = tm;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
