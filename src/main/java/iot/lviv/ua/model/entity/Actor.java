package iot.lviv.ua.model.entity;

public class Actor {
    private Integer id;
    private String appendix;
    private String name;
    private String surname;

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
}
