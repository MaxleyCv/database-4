package iot.lviv.ua.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "points")
    private Integer points;
    @Column(name = "text")
    private String text;
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "user_id")
    private Integer userId;

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Review(Integer id, Integer points, String text, Integer filmId, Integer userId) {
        this.id = id;
        this.points = points;
        this.text = text;
        this.filmId = filmId;
        this.userId = userId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", points=" + points +
                ", text='" + text + '\'' +
                ", filmId=" + filmId +
                ", userId=" + userId +
                '}';
    }
}
