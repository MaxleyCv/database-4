package iot.lviv.ua.model.entity;

public class Film {
    private Integer id;
    private String title;
    private String description;
    private Integer publishYear;
    private String countryOfOrigin;
    private Integer directorId;

    public Film(Integer id, String title, String description, Integer publishYear, String countryOfOrigin, Integer directorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishYear = publishYear;
        this.countryOfOrigin = countryOfOrigin;
        this.directorId = directorId;
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

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishYear=" + publishYear +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", directorId=" + directorId +
                '}';
    }
}
