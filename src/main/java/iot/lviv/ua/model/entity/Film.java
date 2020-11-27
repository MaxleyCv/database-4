package iot.lviv.ua.model.entity;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Film{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "publish_year")
    private Integer publishYear;

    @ManyToOne
    @JoinColumn(name = "origin_country", referencedColumnName = "name", nullable = false)
    private Country countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id", nullable = false)
    private Director director;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "films")
    private Set<Actor> actors;

    public Film() {
    }

    public Film(Integer id, String title, String description, Integer publishYear, Country countryOfOrigin, Director director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishYear = publishYear;
        this.countryOfOrigin = countryOfOrigin;
        this.director = director;
    }

    public Film(Integer id, String title, String description, Integer publishYear, Country countryOfOrigin, Director director, Set<Actor> actors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishYear = publishYear;
        this.countryOfOrigin = countryOfOrigin;
        this.director = director;
        this.actors = actors;
    }


    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishYear=" + publishYear +
                ", countryOfOrigin='" + countryOfOrigin.getName() + '\'' +
                ", director=" + director.getSurname() +
                '}';
    }

    public void setCountryOfOrigin(Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Country getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
