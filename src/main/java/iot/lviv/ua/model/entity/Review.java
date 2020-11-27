package iot.lviv.ua.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "points")
    private Integer points;
    @Column(name = "text_of_review")
    private String textOfReview;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id", nullable = false)
    private Film film;

    @Column(name = "user_id")
    private Integer userId;

    public Review() {
    }

    public Review(Integer id, Integer points, String textOfReview, Film film, Integer userId) {
        this.id = id;
        this.points = points;
        this.textOfReview = textOfReview;
        this.film = film;
        this.userId = userId;
    }

    public Review(Integer id, Integer points, String textOfReview, Integer userId) {
        this.id = id;
        this.points = points;
        this.textOfReview = textOfReview;
        this.userId = userId;
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

    public String getTextOfReview() {
        return textOfReview;
    }

    public void setTextOfReview(String text) {
        this.textOfReview = text;
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
                ", text='" + textOfReview + '\'' +
                ", filmId=" + film.getId() +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return getId().equals(review.getId()) &&
                Objects.equals(getPoints(), review.getPoints()) &&
                Objects.equals(getTextOfReview(), review.getTextOfReview()) &&
                Objects.equals(getFilm(), review.getFilm()) &&
                Objects.equals(getUserId(), review.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPoints(), getTextOfReview(), getFilm(), getUserId());
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
