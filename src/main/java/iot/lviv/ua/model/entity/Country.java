package iot.lviv.ua.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "president")
    private String president;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "countryOfOrigin")
    private Set<Film> films;

    public Country(Integer id, String name, String president, Set<Film> films) {
        this.id = id;
        this.name = name;
        this.president = president;
        this.films = films;
    }

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String name, String president) {
        this.id = id;
        this.name = name;
        this.president = president;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
