package iot.lviv.ua.model.entity;

import org.checkerframework.checker.index.qual.IndexFor;
import org.hibernate.mapping.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="appendix")
    private String appendix;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;

    @ManyToMany
    @JoinTable(
            name = "film_has_actor",
            joinColumns = @JoinColumn(
                    name = "film_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "actor_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Film> films;

    public Actor(Integer id, String appendix, String name, String surname, Set<Film> films) {
        this.id = id;
        this.appendix = appendix;
        this.name = name;
        this.surname = surname;
        this.films = films;
    }

    public Actor() {
    }

    public Integer getId() {
        return id;
    }

    public Actor(Integer id, String name, String surname, String appendix) {
        this.id = id;
        this.appendix = appendix;
        this.name = name;
        this.surname = surname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
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
        return "Actor{" +
                "id=" + id +
                ", appendix='" + appendix + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }


    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return getId().equals(actor.getId()) &&
                Objects.equals(getAppendix(), actor.getAppendix()) &&
                Objects.equals(getName(), actor.getName()) &&
                Objects.equals(getSurname(), actor.getSurname()) &&
                Objects.equals(getFilms(), actor.getFilms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAppendix(), getName(), getSurname(), getFilms());
    }
}
