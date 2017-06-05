package demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city", catalog = "world")
public class City {

    private String city;
    private List<AtmosphericData> atmosphericData;
    private List<Prognosis> prognosis;

    public City() {}

    public City (String city) {
        this.city = city;
    }

    @Id
    @Column(name = "city", unique = true, nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    public List<AtmosphericData> getAtmosphericData() {
        return atmosphericData;
    }

    public void setAtmosphericData(List<AtmosphericData> atmosphericData) {
        this.atmosphericData = atmosphericData;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    public List<Prognosis> getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(List<Prognosis> prognosis) {
        this.prognosis = prognosis;
    }
}
