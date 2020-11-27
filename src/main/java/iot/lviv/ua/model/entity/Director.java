package iot.lviv.ua.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Director{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @OneToMany (mappedBy = "director", fetch =FetchType.EAGER)
    private Set<Film> directedFilms;

    public Director(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Director(Integer id, String name, String surname, Set<Film> directedFilms) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.directedFilms = directedFilms;
    }

    public Director() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Set<Film> getDirectedFilms() {
        return directedFilms;
    }

    public void setDirectedFilms(Set<Film> directedFilms) {
        this.directedFilms = directedFilms;
    }
}
