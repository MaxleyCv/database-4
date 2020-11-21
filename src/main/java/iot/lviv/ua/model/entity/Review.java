package iot.lviv.ua.model.entity;

public class Review {
    private Integer id;
    private Integer points;
    private String text;
    private Integer filmId;
    private Integer userId;

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
